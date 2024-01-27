package com.octest.dao;

import java.util.List;

import com.octest.beans.Transaction;

public interface TransactionDao {
    void addTransaction(Transaction transaction);
    List<Transaction>  getAllTransactions(int year);
    boolean deleteTransaction(Transaction transaction);
    public List<String> getAllYears();
    public boolean updateTransactionStateToPaid(String transactionId);
    public Transaction getTransactionById(String transactionId) ;

}
