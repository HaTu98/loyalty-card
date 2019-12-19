package vn.sapo.loyaltycard.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loyaltyCardId;
    private Float pointAdjust;
    private Float spentAdjust;
    @CreationTimestamp
    private LocalDate createdOn;
}
