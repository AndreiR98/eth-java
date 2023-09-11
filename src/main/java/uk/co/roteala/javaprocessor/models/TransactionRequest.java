package uk.co.roteala.javaprocessor.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TransactionRequest {
    private String uniqueIndex;
    private String transactionHash;
    private String providerCode;
    private String authCode;
    private long timeStamp;
    private BigDecimal totalAmount;
    private String from;
    private List<SplitDetails> splitDetails;
    private Signature signature;
}
