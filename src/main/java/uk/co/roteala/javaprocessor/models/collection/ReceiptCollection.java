package uk.co.roteala.javaprocessor.models.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import uk.co.roteala.javaprocessor.models.SplitDetails;

import javax.persistence.Id;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "receipt_details_token")
@CompoundIndex(
        name = "receipt_unique_index",
        def = "{'receipt_hash': 1}",
        unique = true
)
public class ReceiptCollection {
    @Id
    private String id;

    @Field(name = "token_id")
    private BigInteger tokenId;

    @Field(name = "receipt_hash")
    private String receiptHash;

    @Field(name = "auth_code")
    private String authCode;

    @Field(name = "receiving_address")
    private String receivingAddress;

    @Field(name = "sender_address")
    private String senderAddress;

    @Field(name = "total_amount")
    private BigInteger totalAmount;

    @Field(name = "gas")
    private BigInteger gas;

    @Field(name = "gas_price")
    private BigInteger gasPrice;

    @Field(name = "time_stamp")
    private Instant timeStamp;

    @Field(name = "split_details")
    private List<SplitDetails> splitDetails;
}
