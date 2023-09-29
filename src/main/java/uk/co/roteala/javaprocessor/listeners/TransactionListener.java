package uk.co.roteala.javaprocessor.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.tx.gas.ContractGasProvider;
import uk.co.roteala.javaprocessor.blockchain.DynamicGasProvider;
import uk.co.roteala.javaprocessor.contracts.PaymentSplitter;
import uk.co.roteala.javaprocessor.models.Receipt;
import uk.co.roteala.javaprocessor.models.collection.ReceiptCollection;
import uk.co.roteala.javaprocessor.models.collection.TransactionInCollection;
import uk.co.roteala.javaprocessor.models.repository.AccountWalletRepository;
import uk.co.roteala.javaprocessor.processor.ReceiptTokenProcessor;

import java.math.BigInteger;
import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TransactionListener {
    @Autowired
    private Web3j web3j;

    @Autowired
    private Credentials credentials;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AccountWalletRepository accountWalletRepository;

    //@Bean
    public void addressListener() {
        String targetAddress = "0x95206620CA29f01E5BBc7e42d781A3f383415B73";

        DynamicGasProvider gasProvider = new DynamicGasProvider(BigInteger.valueOf(4_300_000), this.web3j);

        PaymentSplitter paymentSplitterContract = PaymentSplitter
                .load(targetAddress, web3j, credentials, gasProvider);

        ReceiptTokenProcessor processor = new ReceiptTokenProcessor(credentials, web3j, paymentSplitterContract, null,
                mongoTemplate, accountWalletRepository);

        paymentSplitterContract.mintedEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
                        .subscribe(mintedEventResponse -> {
                            log.info("Data:{}", mintedEventResponse.metadataJSON);
                            Receipt receipt = Receipt.fromJsonString(mintedEventResponse.metadataJSON);

                            ReceiptCollection receiptCollection = new ReceiptCollection();
                            receiptCollection.setTokenId(mintedEventResponse.tokenId);
                            receiptCollection.setReceiptHash(receipt.getSplitHash());
                            receiptCollection.setAuthCode(receipt.getAuthCode());
                            receiptCollection.setReceivingAddress(receipt.getTo());
                            receiptCollection.setSenderAddress(receipt.getFrom());
                            receiptCollection.setTotalAmount(receipt.getAmount());
                            receiptCollection.setGas(receipt.getGas());
                            receiptCollection.setGasPrice(receipt.getGasPrice());
                            receiptCollection.setTimeStamp(Instant.ofEpochMilli(receipt.getTimeStamp()));
                            receiptCollection.setSplitDetails(receipt.getSplitDetails());

                            mongoTemplate.save(receiptCollection);

                            processor.forward(receipt);
                        });

//        web3j.transactionFlowable().subscribe(tx -> {
//            if (tx.getTo() != null && tx.getTo().equalsIgnoreCase(targetAddress)) {
//                log.info("Transaction added to mongo");
//
//                TransactionInCollection transaction = new TransactionInCollection();
//                transaction.setHash(tx.getHash());
//                transaction.setNonce(tx.getNonce());
//                transaction.setBlockHash(tx.getBlockHash());
//                transaction.setBlockNumber(tx.getBlockNumber());
//                transaction.setChainId(tx.getChainId());
//                transaction.setTransactionIndex(tx.getTransactionIndex());
//                transaction.setFrom(tx.getFrom());
//                transaction.setTo(tx.getTo());
//                transaction.setValue(tx.getValue());
//                transaction.setGasPrice(tx.getGasPrice());
//                transaction.setGas(tx.getGas());
//                transaction.setInput(tx.getInput());
//                transaction.setCreates(tx.getCreates());
//                transaction.setPublicKey(tx.getPublicKey());
//                transaction.setRaw(tx.getRaw());
//                transaction.setR(tx.getR());
//                transaction.setS(tx.getS());
//                transaction.setV(transaction.getV());
//                transaction.setType(transaction.getType());
//                transaction.setMaxFeePerGas(tx.getMaxFeePerGas());
//                //transaction.setMaxPriorityFeePerGas(tx.getMaxPriorityFeePerGas().t);
//                transaction.setAccessList(tx.getAccessList());
//
//                mongoTemplate.save(transaction);
//            }
        //});
    }

    private static void handler(Transaction transaction) {
        log.info("Transaciton hash:{}", transaction.getHash());
        log.info("Block:{}", transaction.getBlockHash());
        log.info("From:{}",transaction.getFrom());
        log.info("To:{}", transaction.getTo());
        log.info("Value:{}", transaction.getValue());
        log.info("Signature: r:{}, s:{}", transaction.getR(), transaction.getS());
    }
}
