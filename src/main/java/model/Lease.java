package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Lease {

	private static int leaseID = 0;
	private Tenant tenant;
	private Property property;
	private LocalDate startDate;
	private LocalDate endDate;
	private double rentAmount;
	private double rentDue;
	private ArrayList<Payment> allPayments;
	
	public Lease(Tenant tenant, Property property, LocalDate startDate, LocalDate endDate, double rentAmount) {
		Lease.leaseID++;
		this.tenant = tenant;
		this.property = property;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentAmount = rentAmount;
		this.rentDue = rentAmount / Period.between(startDate, endDate).toTotalMonths();
		this.allPayments = new ArrayList<Payment>();
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(double rentAmount) {
		this.rentAmount = rentAmount;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
	/**
	 * @return the leaseID
	 */
	public int getLeaseID() {
		return leaseID;
	}

	/**
	 * @return the rentDue
	 */
	public double getRentDue() {
		return rentDue;
	}

	/**
	 * @param rentDue the rentDue to set
	 */
	public void setRentDue(double rentDue) {
		this.rentDue = rentDue;
	}

	/**
	 * @return the allPayments
	 */
	public ArrayList<Payment> getAllPayments() {
		return allPayments;
	}

	/**
	 * @param add payment to all payments
	 */
	public void addPayment(Payment payment) {
		allPayments.add(payment);
	}

	public boolean isExpired() {
		LocalDate currentDate = LocalDate.now();
		return currentDate.isAfter(endDate);
	}

	@Override
	public String toString() {
		return "Lease ID: " + this.getLeaseID() + "\nTenant :"+this.getTenant()+"\n"
		+ "Property :"+this.getProperty()+"\n"
		+ "Start Date : "+this.getStartDate()+"\n"
		+ "End Date : "+this.getEndDate()+"\n"
		+ "Rent Amount : "+this.getRentAmount() + "\nRent Due : " + this.getRentDue();
	}
}
