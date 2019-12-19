package vn.sapo.loyaltycard.dao;

import vn.sapo.loyaltycard.model.LoyaltyCard;
import vn.sapo.loyaltycard.model.LoyaltyCardType;

import java.util.List;

public interface LoyaltyCardTypeDao {
    LoyaltyCardType save(LoyaltyCardType loyaltyCardType);
    void save(List<LoyaltyCardType> loyaltyCardTypes);
    List<LoyaltyCardType> getAll();
    LoyaltyCardType getByTotalSpent(Float totalSpent);
}
