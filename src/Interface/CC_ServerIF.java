package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CC_ServerIF extends Remote{
	public CarAccidentList getCarAccidentList() throws RemoteException;
	public SurveyList getSurveyList() throws RemoteException;
	public CompensationClaimList getCompensationClaimList() throws RemoteException;
}
