package com.codechallenge.whitebird.utils.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.codechallenge.whitebird.entity.Transaction;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVUtils {

	public static List<Transaction> readCSV(String location) {
		CsvToBean<Transaction> reader = null;
		
		try {
			reader = new CsvToBeanBuilder<Transaction>(new FileReader(location))
					.withType(Transaction.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
		} catch (IllegalStateException | FileNotFoundException e) {
			e.printStackTrace();
		}

		return reader.parse();
	}

}
