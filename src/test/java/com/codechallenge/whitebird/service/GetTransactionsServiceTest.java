package com.codechallenge.whitebird.service;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.codechallenge.whitebird.data.DataStore;
import com.codechallenge.whitebird.entity.Transaction;
import com.codechallenge.whitebird.entity.TransactionStatKeys;
import com.codechallenge.whitebird.utils.csv.CSVUtils;

public class GetTransactionsServiceTest {

	GetTransactionsService service;

	@Before
	public void setup() {
		DataStore.TRANSACTIONS = CSVUtils.readCSV("csv/test/input.csv");
		service = new GetTransactionsServiceImpl();
	}

	@Test
	public void getFilteredTransactionsWorksCorrectlyTest() throws ParseException {
		List<Transaction> transactions = service.getFilteredTransactions(
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("20/08/2018 12:44:33"), new Date(), "Kwik-E-Mart");

		System.out.println(transactions);

		assertThat(transactions.size(), is(2));
	}

	@Test
	public void getTransactionStatisticsWorksCorrectlyTest() throws ParseException {
		Map<String, Double> data = service.getTransactionStatistics(
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("20/08/2018 12:44:33"), new Date(), "Kwik-E-Mart");

		System.out.println(data);

		assertThat(data.get(TransactionStatKeys.AVERAGE_TRANSACTION_VALUE.toString()), is(32.495000000000005));
		assertThat(data.get(TransactionStatKeys.NUMBER_OF_TRANSACTIONS.toString()), is(2.0));
	}

}
