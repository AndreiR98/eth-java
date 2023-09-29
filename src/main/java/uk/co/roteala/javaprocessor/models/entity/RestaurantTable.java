package uk.co.roteala.javaprocessor.models.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.roteala.javaprocessor.models.AuditedDateEntity;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "restaurant_tables")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RestaurantTable extends AuditedDateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "table_id")
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tableId;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "details")
    private String details;

    @Column(name = "is_locked")
    private boolean isLocked;

}
