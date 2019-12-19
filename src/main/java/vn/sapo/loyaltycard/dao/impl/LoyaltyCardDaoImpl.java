package vn.sapo.loyaltycard.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sapo.loyaltycard.dao.LoyaltyCardDao;
import vn.sapo.loyaltycard.model.LoyaltyCard;
import vn.sapo.loyaltycard.repository.LoyaltyCardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoyaltyCardDaoImpl implements LoyaltyCardDao {
    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;

    @Override
    public LoyaltyCard save(LoyaltyCard loyaltyCard) {
        return loyaltyCardRepository.save(loyaltyCard);
    }

    @Override
    public void save(List<LoyaltyCard> loyaltyCards) {
        loyaltyCardRepository.saveAll(loyaltyCards);
    }

    @Override
    public LoyaltyCard getLoyaltyById(Long id) {
        Optional card = loyaltyCardRepository.findById(id);
        if (card.isPresent())
            return (LoyaltyCard) card.get();

        return null;
    }
}
