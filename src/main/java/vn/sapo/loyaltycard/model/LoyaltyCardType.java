package vn.sapo.loyaltycard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loyalty_card_types")
public class LoyaltyCardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float spentThreshold;
    private Integer duration;
    private Float discountPercent;
    @CreationTimestamp
    private LocalDate createdOn;
    @CreationTimestamp
    private LocalDate modifiedOn;

}
