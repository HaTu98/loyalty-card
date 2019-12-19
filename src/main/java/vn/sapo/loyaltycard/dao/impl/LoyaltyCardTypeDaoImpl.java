package vn.sapo.loyaltycard.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vn.sapo.loyaltycard.dao.LoyaltyCardTypeDao;
import vn.sapo.loyaltycard.model.LoyaltyCard;
import vn.sapo.loyaltycard.model.LoyaltyCardType;
import vn.sapo.loyaltycard.repository.LoyaltyCardTypeRepository;

import java.util.Collections;
import java.util.List;

@Service
public class LoyaltyCardTypeDaoImpl implements LoyaltyCardTypeDao {
    @Autowired
    private LoyaltyCardTypeRepository loyaltyCardTypeRepository;

    @Override
    public LoyaltyCardType save(LoyaltyCardType loyaltyCardType) {
        return loyaltyCardTypeRepository.save(loyaltyCardType);
    }

    @Override
    public void save(List<LoyaltyCardType> loyaltyCardTypes) {
        loyaltyCardTypeRepository.saveAll(loyaltyCardTypes);
    }

    @Override
    public List<LoyaltyCardType> getAll() {
        return loyaltyCardTypeRepository.findAll();
    }

    @Override
    public LoyaltyCardType getByTotalSpent(Float totalSpent) {
        List<LoyaltyCardType> loyaltyCardTypes = loyaltyCardTypeRepository.findBySpentThresholdBeforeOrderBySpentThresholdDesc(totalSpent);
        if (CollectionUtils.isEmpty(loyaltyCardTypes))
            return null;
        return loyaltyCardTypes.get(0);
    }
}
