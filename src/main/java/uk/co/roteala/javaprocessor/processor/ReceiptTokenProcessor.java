package uk.co.roteala.javaprocessor.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Numeric;
import uk.co.roteala.javaprocessor.contracts.PaymentSplitter;
import uk.co.roteala.javaprocessor.models.Items;
import uk.co.roteala.javaprocessor.models.Receipt;
import uk.co.roteala.javaprocessor.models.SplitDetails;
import uk.co.roteala.javaprocessor.models.collection.TransactionCollection;
import uk.co.roteala.javaprocessor.models.collection.TransactionInCollection;
import uk.co.roteala.javaprocessor.models.entity.AccountWallet;
import uk.co.roteala.javaprocessor.models.repository.AccountWalletRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ReceiptTokenProcessor {
    private final Credentials credentials;
    private final Web3j web3j;
    private final PaymentSplitter contract;
    private final ContractGasProvider gasProvider;
    private final MongoTemplate mongoTemplate;
    private final AccountWalletRepository accountWalletRepository;

    public void forward(Receipt receipt) {
        ObjectMapper mapper = new ObjectMapper();

        if(!receipt.getSplitDetails().isEmpty()) {
            for(SplitDetails splitDetails : receipt.getSplitDetails()) {
                Optional<AccountWallet> accountWalletOptional = this.accountWalletRepository
                        .findByUniqueMerchantCode(splitDetails.getMerchantCode());

                if(accountWalletOptional.isPresent()) {
                    final AccountWallet accountWallet = accountWalletOptional.get();

                    BigInteger amount = BigInteger.ZERO;

                    for(Map<String, Items> itemsMap : splitDetails.getItems()) {
                        for(Items item : itemsMap.values()) {
                            amount = amount.add(new BigInteger(String.valueOf(item.getAmount())));
                        }
                    }

                    try {
                        final String dataJSON = mapper.writeValueAsString(splitDetails);

                        TransactionReceipt transactionReceipt = contract.processSplit(accountWallet.getAddress(), receipt.getFrom(),
                                Numeric.hexStringToByteArray(receipt.getSplitHash()), dataJSON, Convert.toWei(String.valueOf(amount), Convert.Unit.ETHER).toBigInteger(), new DefaultGasProvider().getGasPrice()).send();

                        TransactionCollection transactionCollection = new TransactionCollection();
                        transactionCollection.setTransactionHash(transactionReceipt.getTransactionHash());
                        transactionCollection.setSplitHash(receipt.getSplitHash());
                        transactionCollection.setSplitDetails(splitDetails);
                        transactionCollection.setAmount(Convert.toWei(String.valueOf(amount), Convert.Unit.ETHER).toBigInteger());
                        transactionCollection.setReceiptSplitDetails(receipt.getSplitDetails());
                        transactionCollection.setBlockNumber(transactionReceipt.getBlockNumber());
                        transactionCollection.setFrom(transactionReceipt.getFrom());
                        transactionCollection.setContractAddress(transactionReceipt.getContractAddress());
                        transactionCollection.setLogs(transactionReceipt.getLogs());

                        mongoTemplate.save(transactionCollection);

                    }catch (Exception e) {
                        log.error("Error: {}", e);
                    }
                }
            }
        }
    }
}
