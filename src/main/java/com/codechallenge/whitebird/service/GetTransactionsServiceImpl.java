package com.codechallenge.whitebird.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.codechallenge.whitebird.data.DataStore;
import com.codechallenge.whitebird.entity.Transaction;
import com.codechallenge.whitebird.entity.TransactionStatKeys;
import com.codechallenge.whitebird.utils.Buffer;

public class GetTransactionsServiceImpl implements GetTransactionsService {

	/**
	 * The getFilteredTransaction method will return a list of transactions filtered
	 * by from date, to date and merchant name
	 * 
	 * @param from: from date to filter
	 * @param to: to date to filter
	 * @param merchant: name of merchant to filter by
	 * @return list of transactions filtered accordingly
	 * @author arseniychernykh
	 */
	@Override
	public List<Transaction> getFilteredTransactions(Date from, Date to, String merchant) {
		List<String> reversed = DataStore.TRANSACTIONS.stream()
				.filter(transaction -> transaction.getType().equals("REVERSAL"))
				.map(transaction -> transaction.getRelatedTransaction()).collect(Collectors.toList());

		return DataStore.TRANSACTIONS.stream().filter(transaction -> transaction.getMerchant().equals(merchant))
				.filter(transaction -> (transaction.getDate().before(to) && transaction.getDate().after(from)))
				.filter(transaction -> !transaction.getType().equals("REVERSAL"))
				.filter(transaction -> !reversed.contains(transaction.getId())).collect(Collectors.toList());
	}

	/**
	 * The method will use the GetTransactionsServiceImpl.getFilterTransactions
	 * method to filter according to passed parameters and and will then count up
	 * amount of given transactions and average value of the transactions and put
	 * them into a HashMap which it will then return
	 * 
	 * @param from: from date to filter
	 * @param to: to date to filter
	 * @param merchant: name of merchant to filter by
	 * @return hashmap with amount of transactions and average value of the
	 *         transactions
	 * @author arseniychernykh
	 */
	@SuppressWarnings("serial")
	@Override
	public Map<String, Double> getTransactionStatistics(Date from, Date to, String merchant) {
		Buffer<Integer> amountOfTransactions = new Buffer<Integer>(0);
		Buffer<Double> averageValue = new Buffer<Double>(0.0);

		List<Transaction> filteredTransactions = getFilteredTransactions(from, to, merchant);

		System.out.println("Full list of filtered transactions: " + filteredTransactions);

		filteredTransactions.forEach(transaction -> {
			amountOfTransactions.value++;
			averageValue.value = (averageValue.value + transaction.getAmount()) / amountOfTransactions.value;
		});

		Map<String, Double> toReturn = new HashMap<String, Double>() {
			{
				put(TransactionStatKeys.NUMBER_OF_TRANSACTIONS.toString(), amountOfTransactions.value.doubleValue());
				put(TransactionStatKeys.AVERAGE_TRANSACTION_VALUE.toString(), averageValue.value);
			}
		};
		return toReturn;
	}

}
