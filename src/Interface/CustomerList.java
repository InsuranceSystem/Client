package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;



public interface CustomerList extends Remote{

	public boolean add(Customer customer) throws RemoteException;
	public boolean delete(String customerID) throws RemoteException;
	public boolean update(Customer customer, String customerID)throws RemoteException;
	public ArrayList<Customer> retrieve()throws RemoteException;
	public Customer getCustomerByID(String customerID)throws RemoteException;
	public Customer retrieveCustomer(String input)throws RemoteException;
//	public ArrayList<Customer> getResurrectCandidates(ContractList contractListImpl);
//	public void deleteResurrectCandidatesCustomer(Customer customer);
//	public ArrayList<Customer> getExpiredContracts(ContractList contractListImpl);
//	public void deleteExpiredCustomer(Customer customer);
	public Customer retrieveCustomerFromUnpaid(String id)throws RemoteException;
	public Customer retrieveCustomerFromExpired(String id)throws RemoteException;
	public Customer retrieveCustomerFromResurrect(String id)throws RemoteException;
	public String getCustomerIdFromNameAndPH(String customerName, String customerPH)throws RemoteException;
//	public void deleteUnpaidCustomer(Customer retrieveCustomer);
//	public List<Contract> getContractFromCustomerId(String customerID, ContractList contractListImpl);
	
}
