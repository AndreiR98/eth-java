package uk.co.roteala.javaprocessor.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Receipt {
    private String splitHash;
    private String authCode;
    private String to;
    private String from;
    private BigInteger amount;
    private BigInteger gas;
    private BigInteger gasPrice;
    private long timeStamp;
    @JsonProperty("split_details")
    private List<SplitDetails> splitDetails;

    public static Receipt fromJsonString(String jsonData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonData, Receipt.class);
        } catch (Exception e) {
            log.error("Error:{}", e);
            return null; // or throw a custom exception
        }
    }
}
