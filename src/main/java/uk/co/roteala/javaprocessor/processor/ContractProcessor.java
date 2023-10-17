package uk.co.roteala.javaprocessor.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;
import uk.co.roteala.javaprocessor.configs.AppConfigs;
import uk.co.roteala.javaprocessor.contracts.BillingPayment;
import uk.co.roteala.javaprocessor.models.Item;
import uk.co.roteala.javaprocessor.models.collection.BillCollection;



import java.time.format.DateTimeFormatter;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class ContractProcessor {
    private final BillingPayment contract;
    private final AppConfigs configs;

    public void forward(BillCollection bill) {
        try {
            String serializedMetadata = serializeDetails(bill.getBillItems());

            TransactionReceipt transactionReceipt = contract.createBill(bill.getBillUniqueRef(),
                            Convert.toWei(bill.getBillAmount(), Convert.Unit.ETHER).toBigInteger(),
                            Convert.toWei(bill.getTaxAmount(), Convert.Unit.ETHER).toBigInteger(),
                            Convert.toWei(bill.getTipAmount(), Convert.Unit.ETHER).toBigInteger(),
                            Convert.toWei(bill.getRevenueAmount(), Convert.Unit.ETHER).toBigInteger(),
                            bill.getProcessDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            String.valueOf(bill.getTimeStamp().toEpochMilli()),
                            bill.getMerchantAddress(),
                            configs.getMasterKey(),
                            serializedMetadata)
                    .send();

            log.info(transactionReceipt.getTransactionHash());
        } catch (Exception e) {
            log.error("Error:{}", e);
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
