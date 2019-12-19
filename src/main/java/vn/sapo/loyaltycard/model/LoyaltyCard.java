package vn.sapo.loyaltycard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loyalty_cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoyaltyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String phone;
    private Long loyaltyCartTypeId;
    private Float point;
    private Float totalSpent;
    @CreationTimestamp
    private LocalDate startDate;
    @CreationTimestamp
    private LocalDate endDate;
    @CreationTimestamp
    private LocalDate createdOn;
    @CreationTimestamp
    private LocalDate modifiedOn;

}
