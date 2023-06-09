package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface SurveyList extends Remote{

	boolean createSurvey(Survey survey) throws Exception,RemoteException;

    public ArrayList<Survey> retrieve() throws RemoteException;

    boolean updateSurvey(Survey updateSurvey) throws Exception ,RemoteException;

    Survey getSurveyByID(String ccid) throws RemoteException;

    boolean deleteById(String ccid) throws Exception, RemoteException;
}