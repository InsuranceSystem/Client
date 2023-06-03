package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Customer_ServerIF extends Remote{

	public CustomerList getCustomerList() throws RemoteException;
	public FamilyHistoryList getFamilyHistoryList() throws RemoteException;
	public CounselList getCounselList() throws RemoteException;
	public CounselApplicationList getCounselApplicationList() throws RemoteException;
}
