package com.codechallenge.whitebird.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.codechallenge.whitebird.entity.Transaction;

public interface GetTransactionsService {
	
	public List<Transaction> getFilteredTransactions(Date from, Date to, String merchant);
	
	public Map<String, Double> getTransactionStatistics(Date from, Date to, String merchant);

}
