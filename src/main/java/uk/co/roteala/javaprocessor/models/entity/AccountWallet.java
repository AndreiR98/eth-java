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

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "account_wallets")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AccountWallet extends AuditedDateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "wallet_id")
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "mid")
    private String mid;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "fees_id")
    private Integer feesId;

    @Column(name = "date_added")
    private Instant dateAdded;
}
