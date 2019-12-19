package vn.sapo.loyaltycard.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sapo.loyaltycard.dao.TransactionDao;
import vn.sapo.loyaltycard.model.Transaction;
import vn.sapo.loyaltycard.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionDaoImpl implements TransactionDao {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void save(List<Transaction> transactions) {
        transactionRepository.saveAll(transactions);
    }
}
