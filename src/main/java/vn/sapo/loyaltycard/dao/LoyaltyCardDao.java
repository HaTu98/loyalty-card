package vn.sapo.loyaltycard.dao;

import vn.sapo.loyaltycard.model.LoyaltyCard;

import java.util.List;

public interface LoyaltyCardDao {
    LoyaltyCard save(LoyaltyCard loyaltyCard);
    void save(List<LoyaltyCard> loyaltyCards);
    LoyaltyCard getLoyaltyById(Long id);
}
