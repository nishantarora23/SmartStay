import java.time.LocalDate;

public class Lease {

	private Tenant tenant;
	private Property property;
	private LocalDate startDate;
	private LocalDate endDate;
	private double rentAmount;
	
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

	public Lease(Tenant tenant, Property property, LocalDate startDate, LocalDate endDate, double rentAmount) {
		this.tenant = tenant;
		this.property = property;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentAmount = rentAmount;
	}

	public boolean isExpired() {
		LocalDate currentDate = LocalDate.now();
		return currentDate.isAfter(endDate);
	}

	@Override
	public String toString() {
		return "\nTenant :"+this.getTenant()+"\n"
		+ "Property :"+this.getProperty()+"\n"
		+ "Start Date : "+this.getStartDate()+"\n"
		+ "End Date : "+this.getEndDate()+"\n"
		+ "Rent Amount : "+this.getRentAmount();
	}
}