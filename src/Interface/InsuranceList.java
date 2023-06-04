package Interface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public interface InsuranceList extends Remote{

	boolean createInsurance(Insurance insurance) throws FileNotFoundException, IOException, Exception, RemoteException;

	boolean updateinsurance(Insurance updateInsurance) throws Exception, RemoteException;

	ArrayList<Insurance> retrieve()throws RemoteException;

	boolean deleteInsurance(String insuranceId) throws Exception, RemoteException;

	Insurance getInsurancebyId(String insuranceID) throws RemoteException;

	Insurance retrieveInsuranceDetail(String insuranceID)throws RemoteException;

	ArrayList<Insurance> getUnregisteredInsuranceList()throws RemoteException;

	String requestAuthorization(String insuranceID)throws RemoteException;

	boolean updateAuthorization(String insuranceID, boolean b) throws Exception, RemoteException;

	ArrayList<Insurance> getOnSaleInsuranceList()throws RemoteException;

	ArrayList<Insurance> retrieveInsurance(String string)throws RemoteException;

	boolean isExistInsuranceDesign(String insuranceID)throws RemoteException;

	ArrayList<String> getInsuranceNameTypeInfoById(String string)throws RemoteException;

	Collection<? extends String> getInsuranceNameById(String insurance)throws RemoteException;

	String getInsuranceIdbyName(String string) throws RemoteException;


}