package vn.sapo.loyaltycard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loyalty_cards")
@Getter
@Setter
@AllArgsConstructor
public class LoyaltyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String phone;
    private String loyaltyCartTypeId;
    private Float point;
    private Float totalSpent;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createdOn;
    private LocalDate modifiedOn;

}
