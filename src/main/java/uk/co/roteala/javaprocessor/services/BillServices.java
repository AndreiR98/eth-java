package uk.co.roteala.javaprocessor.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import uk.co.roteala.javaprocessor.blockchain.DynamicGasProvider;
import uk.co.roteala.javaprocessor.configs.AppConfigs;
import uk.co.roteala.javaprocessor.contracts.BillingPayment;
import uk.co.roteala.javaprocessor.contracts.PaymentSplitter;
import uk.co.roteala.javaprocessor.models.*;
import uk.co.roteala.javaprocessor.models.collection.BillCollection;
import uk.co.roteala.javaprocessor.models.entity.AccountWallet;
import uk.co.roteala.javaprocessor.models.entity.Fees;
import uk.co.roteala.javaprocessor.models.repository.AccountWalletRepository;
import uk.co.roteala.javaprocessor.models.repository.FeesRepository;
import uk.co.roteala.javaprocessor.models.repository.mongo.BillCollectionRepository;
import uk.co.roteala.javaprocessor.processor.ContractProcessor;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class BillServices {
    private final AccountWalletRepository accountWalletRepository;
    private final FeesRepository feesRepository;
    private final BillCollectionRepository billCollectionRepository;
    private final AppConfigs configs;

    @Autowired
    private Web3j web3j;

    @Autowired
    private Credentials credentials;

    public BillResponse processBill(@Valid BillRequest billRequest) {
        try {

        DynamicGasProvider gasProvider = new DynamicGasProvider(BigInteger.valueOf(4_300_000), this.web3j);

        BillingPayment contract = BillingPayment
                .load(configs.getContractAddress(), web3j, credentials, gasProvider);

            ContractProcessor processor = new ContractProcessor(credentials, web3j, contract, gasProvider, configs);


            Optional<AccountWallet> accountWalletOptional = this.accountWalletRepository
                    .findByMid(billRequest.getMid());

            if(accountWalletOptional.isEmpty()) {
                log.error("Mid not found!");
            }

            final AccountWallet accountWallet = accountWalletOptional.get();

            Optional<Fees> feesOptional = this.feesRepository
                    .findByFeesId(accountWallet.getFeesId());

            if(feesOptional.isEmpty()) {
                log.error("Fees not found!");
            }

            final Fees fees = feesOptional.get();

            final String billUniqueRef = bytesToHexString(computeSHA256(generateRandomBytes(32)));

            BigDecimal tax = BigDecimal.ZERO;
            BigDecimal revenue = BigDecimal.ZERO;
            LocalDate processDate = LocalDate.now();

            //Process the fees based on the total billing
            if(fees.getFeesType() == FeesType.BILLING) {
                tax = billRequest.getTotal()
                        .multiply(fees.getBasePercCharge())
                        .add(fees.getBaseCharge());

                revenue = billRequest.getTotal()
                        .subtract(tax);
            }

            try {
                //Call Contract

                BillCollection billCollection = new BillCollection();
                billCollection.setBillAmount(billRequest.getTotal());
                billCollection.setBillUniqueRef(billUniqueRef);
                billCollection.setMid(accountWallet.getMid());
                billCollection.setMerchantAddress(accountWallet.getAddress());
                billCollection.setProcessed(false);
                billCollection.setProcessDate(processDate);
                billCollection.setPayedAmount(BigDecimal.ZERO);
                billCollection.setTaxAmount(tax);
                billCollection.setPayments(new ArrayList<>());
                billCollection.setTipAmount(billRequest.getTip());
                billCollection.setRevenueAmount(revenue);
                billCollection.setBillItems(billRequest.getMetaData());
                billCollection.setTimeStamp(Instant.ofEpochMilli(billRequest.getTimeStamp()));

                processor.forward(billCollection);

                billCollectionRepository.save(billCollection);
            } catch (Exception e) {
                log.error("ETH error: {}", e);
            }

            return BillResponse.builder()
                    .billUniqueReference(billUniqueRef)
                    .status(Status.SUCCESS)
                    .build();
        } catch (Exception e) {
            log.error("Error: {}", e);

            return BillResponse.builder()
                    .status(Status.ERROR)
                    .build();
        }
    }

    public byte[] computeSHA256(byte[] input) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest crypt = MessageDigest.getInstance("SHA-256");

            crypt.update(input);

            return crypt.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String bytesToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = String.format("%02x", b);
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public byte[] generateRandomBytes(int n) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[n];
        secureRandom.nextBytes(bytes);
        return bytes;
    }
}
