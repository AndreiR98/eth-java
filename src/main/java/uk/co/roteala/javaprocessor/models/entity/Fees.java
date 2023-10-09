package uk.co.roteala.javaprocessor.models.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.roteala.javaprocessor.models.AuditedDateEntity;
import uk.co.roteala.javaprocessor.models.FeesType;
import uk.co.roteala.javaprocessor.models.FeesTypeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "par_fees")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Fees extends AuditedDateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "fees_id")
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feesId;

    @Column(name = "fees_type")
    @Convert(converter = FeesTypeConverter.class)
    private FeesType feesType;

    @Column(name = "base_charge")
    private BigDecimal baseCharge;

    @Column(name = "base_perc_charge")
    private BigDecimal basePercCharge;

    @Column(name = "perc_charge_item")
    private BigDecimal percChargeItem;

    @Column(name = "base_charge_item")
    private BigDecimal baseChargeItem;

    @Column(name = "date_added")
    private Instant dateAdded;
}
