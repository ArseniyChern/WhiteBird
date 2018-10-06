package com.codechallenge.whitebird.data;

import java.util.List;

import com.codechallenge.whitebird.entity.Transaction;
import com.codechallenge.whitebird.utils.csv.CSVUtils;

public class DataStore {
	
	public static List<Transaction> TRANSACTIONS = CSVUtils.readCSV("csv/main/input.csv");

}
