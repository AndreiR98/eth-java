package uk.co.roteala.javaprocessor.models.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.web3j.protocol.core.methods.response.Log;
import uk.co.roteala.javaprocessor.models.AuditedDateEntity;

import javax.persistence.Id;
import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "split_confirmation_transaction")
public class TransactionCollection extends AuditedDateEntity {
    @Id
    private String id;

    @Field(name = "transaction_hash")
    private String transactionHash;

    @Field(name = "split_unique_hash")
    private String splitHash;

    @Field(name = "amount")
    private BigInteger amount;

    @Field(name = "block_number")
    private BigInteger blockNumber;

    @Field(name = "from_address")
    private String from;

    @Field(name = "contract_address")
    private String contractAddress;

    @Field(name = "logs")
    private List<Log> logs;
}
