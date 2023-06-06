package Interface;

import java.io.Serializable;
import java.time.LocalDate;

public class Counsel implements Serializable {
    private static final long serialVersionUID = 1L;
	private String content; // 
	private String managerName;
	private LocalDate dateOfCounsel;

	private String counselID; // 
	private String customerID;
	private Customer customer;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public LocalDate getDateOfCounsel() {
		return dateOfCounsel;
	}

	public void setDateOfCounsel(LocalDate dateOfCounsel) {
		this.dateOfCounsel = dateOfCounsel;
	}

	public String getCounselID() {
		return counselID;
	}

	public void setCounselID(String counselID) {
		this.counselID = counselID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public boolean matchId(String customerID) {
		return this.customerID.equals(customerID);
	}
}
