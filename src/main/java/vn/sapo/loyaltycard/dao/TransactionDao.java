package vn.sapo.loyaltycard.dao;

import vn.sapo.loyaltycard.model.Transaction;

import java.util.List;

public interface TransactionDao {
    Transaction save(Transaction transaction);
    void save(List<Transaction> transactions);
}
