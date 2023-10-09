package uk.co.roteala.javaprocessor.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Numeric;
import uk.co.roteala.javaprocessor.contracts.BillingPayment;
import uk.co.roteala.javaprocessor.contracts.PaymentSplitter;
import uk.co.roteala.javaprocessor.models.Item;
import uk.co.roteala.javaprocessor.models.collection.BillCollection;
import uk.co.roteala.javaprocessor.models.collection.TransactionCollection;
import uk.co.roteala.javaprocessor.models.entity.AccountWallet;
import uk.co.roteala.javaprocessor.models.repository.AccountWalletRepository;

import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ContractProcessor {
    private final Credentials credentials;
    private final Web3j web3j;
    private final BillingPayment contract;
    private final ContractGasProvider gasProvider;

    public void forward(BillCollection bill) {
        try {
            String serializedMetadata = serializeDetails(bill.getBillItems());


            contract.createBill(bill.getBillUniqueRef(),
                            Convert.toWei(bill.getBillAmount(), Convert.Unit.ETHER).toBigInteger(),
                            Convert.toWei(bill.getTaxAmount(), Convert.Unit.ETHER).toBigInteger(),
                            Convert.toWei(bill.getTipAmount(), Convert.Unit.ETHER).toBigInteger(),
                            Convert.toWei(bill.getRevenueAmount(), Convert.Unit.ETHER).toBigInteger(),
                            bill.getProcessDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            String.valueOf(bill.getTimeStamp().toEpochMilli()),
                            bill.getMerchantAddress(),
                            serializedMetadata)
                    .send();
        } catch (Exception e) {
            //
        }
    }

    private String serializeDetails(List<Item> details) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.writeValueAsString(details);
        } catch (Exception e) {
            return "";
        }
    }
}
