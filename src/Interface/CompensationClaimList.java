package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;




public interface CompensationClaimList extends Remote{

	boolean createCompensationClaim(CompensationClaim compensationClaim) throws Exception, RemoteException;

	ArrayList<CompensationClaim> retrieve() throws RemoteException;

	boolean update() throws RemoteException;
	boolean delete(String CCID) throws Exception, RemoteException;
	CompensationClaim getCompensationClaimbyID(String ccid) throws RemoteException;

}