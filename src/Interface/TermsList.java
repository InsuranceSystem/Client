package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface TermsList extends Remote{
	boolean createTerms(Terms terms) throws Exception, RemoteException;
	ArrayList<Terms> retrieveAllTerms() throws RemoteException;
	Terms getTermsByID(String termsID) throws RemoteException;
	boolean isExistTermsID(String string) throws Exception, RemoteException;
}