package Interface;

import java.util.ArrayList;


public interface SurveyList {

	boolean createSurvey(Survey survey) throws Exception;

    public ArrayList<Survey> retrieve();

    boolean update(Survey updateSurvey) throws Exception;

}