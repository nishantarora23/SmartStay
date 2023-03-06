package model;

import java.time.LocalDate;

/**
 * This class will create rent payments
 */
public class Payment {
	
	private double amountPaid;
	private LocalDate date;
	
	/**
	 * @param amountPaid
	 * @param date
	 */
	public Payment(double amountPaid, LocalDate date) {
		this.amountPaid = amountPaid;
		this.date = date;
	}

	/**
	 * @return the amountPaid
	 */
	public double getAmountPaid() {
		return amountPaid;
	}

	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
