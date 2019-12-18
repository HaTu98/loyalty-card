package vn.sapo.loyaltycard.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Data
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loyaltyCardId;
    private Float pointAdjust;
    private Float spentAdjust;
    private LocalDate createdOn;
}
