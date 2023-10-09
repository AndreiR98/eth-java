package uk.co.roteala.javaprocessor.models.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.roteala.javaprocessor.models.AuditedDateEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "roteala_restaurant_receipt")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RestaurantBill extends AuditedDateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unique_ref_id")
    private String uniqueRefId;

    @Column(name = "table_id")
    private Integer tableId;

    @Column(name = "waiter_id")
    private Integer waiterId;

    @Column(name = "split_transaction")
    private boolean splitTransaction;

    @Column(name = "split_numbers")
    private Integer splitNumbers;

    @Column(name = "payed_status")
    private boolean payedStatus;

    @Column(name = "split_type")
    private String splitType;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "tip_amount")
    private BigDecimal tipAmount;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "details")
    private String details;

    @Column(name = "process_date")
    private LocalDate processDate;

    @Column(name = "time_stamp")
    private long timeStamp;
}
