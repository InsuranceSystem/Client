package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InsuranceApplicationList extends Remote{

	boolean createInsuranceApplication(InsuranceApplication insuranceApplication) throws Exception, RemoteException;

	boolean delete(String applicationId) throws Exception,RemoteException;

	ArrayList<InsuranceApplication> retrieve()throws RemoteException;

	boolean update()throws RemoteException;

	InsuranceApplication getApplicationbyId(String trim) throws RemoteException;

	boolean updateInsuranceApplication(InsuranceApplication insuranceApplication) throws Exception, RemoteException;

}