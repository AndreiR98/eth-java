package uk.co.roteala.javaprocessor.models.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.web3j.protocol.core.methods.response.AccessListObject;
import uk.co.roteala.javaprocessor.models.AuditedDateEntity;

import javax.persistence.Id;
import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contract_transactions_in")
public class TransactionInCollection extends AuditedDateEntity {
    @Id
    private String id;

    @Field(name = "transaction_hash")
    private String hash;

    @Field(name = "nonce")
    private BigInteger nonce;

    @Field(name = "block_hash")
    private String blockHash;

    @Field(name = "block_number")
    private BigInteger blockNumber;

    @Field(name = "chain_id")
    private Long chainId;

    @Field(name = "transaction_index")
    private BigInteger transactionIndex;

    @Field(name = "from")
    private String from;

    @Field(name = "to")
    private String to;

    @Field(name = "value")
    private BigInteger value;

    @Field(name = "gas_price")
    private BigInteger gasPrice;

    @Field(name = "gas")
    private BigInteger gas;

    @Field(name = "input")
    private String input;

    @Field(name = "creates")
    private String creates;

    @Field(name = "public_key")
    private String publicKey;

    @Field(name = "transaction_raw")
    private String raw;

    @Field(name = "signature_r")
    private String r;

    @Field(name = "signature_s")
    private String s;

    @Field(name = "signature_v")
    private long v;

    @Field(name = "type")
    private String type;

    @Field(name = "max_fee_per_gas")
    private BigInteger maxFeePerGas;

    @Field(name = "max_priority_fee_per_gas")
    private BigInteger maxPriorityFeePerGas;

    @Field(name = "access_list")
    private List<AccessListObject> accessList;
}
