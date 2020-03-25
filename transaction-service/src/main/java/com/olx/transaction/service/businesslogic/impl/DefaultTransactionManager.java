package com.olx.transaction.service.businesslogic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.olx.transaction.service.businesslogic.TransactionManager;
import com.olx.transaction.service.models.Transaction;
import com.olx.transaction.service.models.User;
import com.olx.transaction.service.repositories.TransactionRepository;

@Component
public class DefaultTransactionManager implements TransactionManager {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction getTransactionById(Long id) {
		return transactionRepository.findById(id).orElse(null);
	}

	@Override
	public Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction closeTransaction(Transaction transaction) {
		transaction.setClosed(true);
		return transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getClosedTransactions(User user) {
		return transactionRepository.findByOwnerAndClosed(user, Boolean.TRUE);
	}

	@Override
	public List<Transaction> getActiveTransactions(User user) {
		return transactionRepository.findByOwnerAndClosed(user, Boolean.FALSE);
	}

	@Override
	public void delete(Long id) {
		transactionRepository.deleteById(id);
	}
}
