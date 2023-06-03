package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Contract_ServerIF extends Remote{
	public PaymentList getPaymentList() throws RemoteException;
	public ContractList getContractList() throws RemoteException;
}