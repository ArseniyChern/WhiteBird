package com.codechallenge.whitebird;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.codechallenge.whitebird.service.GetTransactionsService;
import com.codechallenge.whitebird.service.GetTransactionsServiceImpl;

/**
 * Project WhiteBird application entry point
 * 
 * @author arseniychernykh
 *
 */
public class WhiteBird {

	public static void main(String[] args) throws ParseException {
		GetTransactionsService getTransactionsService = new GetTransactionsServiceImpl();

		Date from = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("20/08/2018 12:44:33");

		Map<String, Double> result = getTransactionsService.getTransactionStatistics(from, new Date(), "Kwik-E-Mart");

		System.out.println(result);

	}

}
