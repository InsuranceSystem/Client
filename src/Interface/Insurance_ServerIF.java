package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Insurance_ServerIF extends Remote{

	public InsuranceList getInsuranceList() throws RemoteException;

	public GuaranteeList getGuaranteeList() throws RemoteException;

	public InsuranceApplicationList getInsuranceApplicationList() throws RemoteException;

	public TermsList getTermsList() throws RemoteException;
}

