package vn.sapo.loyaltycard.dto.transaction;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TransactionRequest {
    private Long loyaltyCardId;
    private Float spentAdjust;
}
