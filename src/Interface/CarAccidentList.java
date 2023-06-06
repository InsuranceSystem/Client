package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface CarAccidentList extends Remote{

	CarAccident getCarAccidentByID(String ccid) throws Exception, RemoteException;

	boolean add() throws RemoteException;

	boolean delete(String ccid) throws Exception, RemoteException;

	ArrayList<CarAccident> retrieve() throws RemoteException;

	boolean update() throws RemoteException;

	boolean createCarAccident(CarAccident carAccident) throws Exception, RemoteException;

}