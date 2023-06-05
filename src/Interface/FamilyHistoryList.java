package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;


public interface FamilyHistoryList extends Remote{

	public boolean add(FamilyHistory familyHistory)throws RemoteException;

	public boolean delete(String customerID)throws RemoteException;

	public ArrayList<FamilyHistory> retrieve()throws RemoteException;

	public boolean update(FamilyHistory familyHistory, String customerID) throws RemoteException;

	public ArrayList<FamilyHistory> getFamilyHistoryByCID(String customerID)throws RemoteException;

	public FamilyHistory getFamilyHistoryFromId(String customerID)throws RemoteException;

	public ArrayList<FamilyHistory> getAllFamilyHistoryFromId(String selectedCustomerId) throws RemoteException;

}