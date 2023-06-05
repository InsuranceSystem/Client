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
	public ArrayList<Customer> getResurrectCandidates(boolean resurrection, List<Contract> contractList) throws RemoteException;
	public boolean deleteResurrectCandidatesCustomer(Customer customer) throws RemoteException;
	public ArrayList<Customer> getExpiredContracts(boolean maturity, List<Contract> contractList) throws RemoteException;
	public boolean deleteExpiredCustomer(Customer customer) throws RemoteException;
	public Customer retrieveCustomerFromUnpaid(String id)throws RemoteException;
	public Customer retrieveCustomerFromExpired(String id)throws RemoteException;
	public Customer retrieveCustomerFromResurrect(String id)throws RemoteException;
	public String getCustomerIdFromNameAndPH(String customerName, String customerPH)throws RemoteException;
//	public void deleteUnpaidCustomer(Customer retrieveCustomer);
//	public List<Contract> getContractFromCustomerId(String customerID, ContractList contractListImpl);
//	public Customer getCustomerFromCouncels(String customerId);
}
