package uk.co.roteala.javaprocessor.models.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import uk.co.roteala.javaprocessor.models.Item;
import uk.co.roteala.javaprocessor.models.Payment;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "restaurant_bills")
@CompoundIndex(
        name = "bill_unique_index",
        def = "{'bill_unique_ref': 1}",
        unique = true
)
public class BillCollection {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Field(name = "bill_unique_ref")
    private String billUniqueRef;

    @Field(name = "bill_amount")
    private BigDecimal billAmount;

    @Field(name = "tax_amount")
    private BigDecimal taxAmount;

    @Field(name = "tip_amount")
    private BigDecimal tipAmount;

    @Field(name = "revenue_amount")
    private BigDecimal revenueAmount;

    @Field(name = "process_date")
    private LocalDate processDate;

    @Field(name = "merchant_address")
    private String merchantAddress;

    @Field(name = "merchant_mid")
    private String mid;

    @Field(name = "processed")
    private boolean processed;

    @Field(name = "payments")
    private List<Payment> payments;

    @Field(name = "payed_amount")
    private BigDecimal payedAmount;

    @Field(name = "bill_details")
    private List<Item> billItems;

    @Field(name = "time_stamp")
    private Instant timeStamp;
}
