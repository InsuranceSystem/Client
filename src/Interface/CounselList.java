package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;



public interface CounselList extends Remote{

	
	public boolean add(Counsel counsel)throws Exception,RemoteException;

	public boolean delete(String counselID) throws Exception ,RemoteException;

	public ArrayList<Counsel> retrieve() throws RemoteException;

	public void update(Counsel updateCounsel)throws Exception ,RemoteException;
	public Counsel getCounselbyId(String customerID)throws RemoteException;
	
	public Counsel getCounselbyCounselId(String counselID) throws RemoteException;
	public List<Counsel> getCounselList(String customerID) throws RemoteException;
}
