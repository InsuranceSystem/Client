package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface SurveyList extends Remote{

	boolean createSurvey(Survey survey) throws Exception,RemoteException;

    public ArrayList<Survey> retrieve() throws RemoteException;

    boolean update(Survey updateSurvey) throws Exception ,RemoteException;

}