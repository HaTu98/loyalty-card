package vn.sapo.loyaltycard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.loyaltycard.dao.ConfigDao;
import vn.sapo.loyaltycard.dao.LoyaltyCardDao;
import vn.sapo.loyaltycard.dao.LoyaltyCardTypeDao;
import vn.sapo.loyaltycard.dao.TransactionDao;
import vn.sapo.loyaltycard.dto.transaction.TransactionRequest;
import vn.sapo.loyaltycard.model.Config;
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

    @Autowired
    private ConfigDao configDao;

    public void importAccumulate(List<Transaction> transactions){
        List<LoyaltyCardType> loyaltyCardTypes = loyaltyCardTypeDao.getAll();
        for (Transaction transaction : transactions) {
            transactionDao.save(transaction);
            this.accumulate(transaction);
        }
    }

    public LoyaltyCard payment(TransactionRequest request) {
        Config config = configDao.getConfig(1L);
        Transaction transaction = new Transaction();

        Float spent = request.getSpentAdjust();
        Integer rate = 100000;
        if (config != null) {
            rate = config.getValue();
        }
        Float point = spent / rate;


        transaction.setLoyaltyCardId(request.getLoyaltyCardId());
        transaction.setSpentAdjust(spent);
        transaction.setPointAdjust(point);
        transactionDao.save(transaction);
        this.accumulate(transaction);
        return loyaltyCardDao.getLoyaltyById(request.getLoyaltyCardId());
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
