package com.codechallenge.whitebird.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.bean.CsvBindByName;

public class Transaction {
	@CsvBindByName
	private String id;

	@CsvBindByName
	private double amount;

	@CsvBindByName
	private String date;

	@CsvBindByName
	private String merchant;

	@CsvBindByName
	private String type;

	@CsvBindByName
	private String relatedTransaction;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		try {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(this.date);
		} catch (ParseException e) {
			System.err.println(
					"There was a problem with parsing the date, the data is most likely corrupt. The date that we failed to parse was: "
							+ this.date + ", and the expected format was dd/MM/yyyy HH:mm:ss");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", date=" + date + ", merchant=" + merchant + ", type="
				+ type + ", relatedTransaction=" + relatedTransaction + "]";
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRelatedTransaction() {
		return relatedTransaction;
	}

	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}

}
