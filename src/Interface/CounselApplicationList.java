package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;




public interface CounselApplicationList extends Remote{

	public boolean add(CounselApplication counselApplication)throws Exception,RemoteException;
	public boolean delete(String counselID) throws RemoteException;
	public boolean update(CounselApplication counselApplication) throws RemoteException;
	public ArrayList<CounselApplication> retrieve()throws RemoteException;
	public CounselApplication getCounselApplicationById(String id) throws RemoteException;
	public CounselApplication getCounselApplicationByCustomerId(String id) throws RemoteException;
}
