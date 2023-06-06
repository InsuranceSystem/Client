package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface GuaranteeList extends Remote{
	boolean create(Guarantee newguarantee) throws Exception, RemoteException;

	ArrayList<Guarantee> getAllGuranteeByID(String insuranceID)throws Exception, RemoteException;

	boolean deleteGuranteeById(String insuranceID) throws Exception,RemoteException;
}