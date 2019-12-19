package vn.sapo.loyaltycard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.loyaltycard.dao.LoyaltyCardDao;
import vn.sapo.loyaltycard.dao.LoyaltyCardTypeDao;
import vn.sapo.loyaltycard.dao.TransactionDao;
import vn.sapo.loyaltycard.model.LoyaltyCard;
import vn.sapo.loyaltycard.model.LoyaltyCardType;
import vn.sapo.loyaltycard.model.Transaction;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private LoyaltyCardDao loyaltyCardDao;

    @Autowired
    private LoyaltyCardTypeDao loyaltyCardTypeDao;

    public void importAccumulate(List<Transaction> transactions){
        List<LoyaltyCardType> loyaltyCardTypes = loyaltyCardTypeDao.getAll();
        for (Transaction transaction : transactions) {
            transactionDao.save(transaction);
            this.accumulate(transaction);
        }
    }

    @Transactional
    void  accumulate (Transaction transaction) {
        LoyaltyCard loyaltyCard = loyaltyCardDao.getLoyaltyById(transaction.getLoyaltyCardId());
        Float point = loyaltyCard.getPoint() + transaction.getPointAdjust();
        Float totalSpent = loyaltyCard.getTotalSpent() + transaction.getSpentAdjust();
        loyaltyCard.setPoint(point);
        loyaltyCard.setTotalSpent(totalSpent);
        LoyaltyCardType loyaltyCardType = loyaltyCardTypeDao.getByTotalSpent(totalSpent);

        loyaltyCard.setLoyaltyCartTypeId(loyaltyCardType.getId());
        loyaltyCardDao.save(loyaltyCard);
    }

}
