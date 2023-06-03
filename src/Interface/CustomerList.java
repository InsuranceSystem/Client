package Interface;

import java.util.ArrayList;
import java.util.List;


public interface CustomerList {

	public boolean add(Customer customer);
	public boolean delete(String customerID);
	public boolean update(Customer customer, String customerID);
	public ArrayList<Customer> retrieve();
	public Customer getCustomerByID(String customerID);
	public Customer retrieveCustomer(String input);
//	public ArrayList<Customer> getResurrectCandidates(ContractList contractListImpl);
	public void deleteResurrectCandidatesCustomer(Customer customer);
//	public ArrayList<Customer> getExpiredContracts(ContractList contractListImpl);
	public void deleteExpiredCustomer(Customer customer);
	public Customer retrieveCustomerFromUnpaid(String id);
	public Customer retrieveCustomerFromExpired(String id);
	public Customer retrieveCustomerFromResurrect(String id);
	public String getCustomerIdFromNameAndPH(String customerName, String customerPH);
	public void deleteUnpaidCustomer(Customer retrieveCustomer);
//	public List<Contract> getContractFromCustomerId(String customerID, ContractList contractListImpl);
	public Customer getCustomerFromCouncels(String customerId);

}
