package uk.co.roteala.javaprocessor.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BillRequest {
    @Schema(description = "Bill details", type = "List<Item>", example = "{\n" +
            "         \"name\":\"PIZZA\",\n" +
            "         \"price\":1,\n" +
            "         \"quantity\":1\n" +
            "      }", required = true)
    @NotNull(message = "The field is mandatory")
    private List<Item> metaData;

    @Schema(description = "Merchant mid", type = "String", example = "1025485", required = true)
    @NotNull(message = "The field is mandatory")
    private String mid;

    @Schema(description = "Bill total sub amount", type = "BigDecimal", example = "10.0000", required = true)
    @NotNull(message = "The field is mandatory")
    private BigDecimal subTotal;

    @Schema(description = "Bill total tax", type = "BigDecimal", example = "10.0000", required = true)
    @NotNull(message = "The field is mandatory")
    private BigDecimal tax;

    @Schema(description = "Timestamp", type = "long", example = "166254785421", required = true)
    @NotNull(message = "The field is mandatory")
    private long timeStamp;

    @Schema(description = "Bill total tip", type = "BigDecimal", example = "10.0000", required = true)
    @NotNull(message = "The field is mandatory")
    private BigDecimal tip;

    @Schema(description = "Bill total amount", type = "BigDecimal", example = "10.0000", required = true)
    @NotNull(message = "The field is mandatory")
    private BigDecimal total;
}
