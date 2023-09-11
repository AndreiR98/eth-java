package uk.co.roteala.javaprocessor.models.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import uk.co.roteala.javaprocessor.models.AuditedDateEntity;
import uk.co.roteala.javaprocessor.models.SplitDetails;

import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "split_transaction_details")
public class SplitDetailsCollections extends AuditedDateEntity {
    @Id
    private String id;

    @Field(name = "transaction_hash")
    private String transactionHash;

    @Field(name = "transaction_date")
    private String transactionDate;

    @Field(name = "split_details")
    private List<SplitDetails> splitDetails;

    @Field(name = "transaction_initiator")
    private String transactionInitiator;

    @Field(name = "contract_address")
    private String contractAddress;

    @Field(name = "gas_price")
    private String gasPrice;

    @Field(name = "total_amount")
    private String totalAmount;
}
