package Interface;

import java.util.ArrayList;

public interface InsuranceApplicationList {

	boolean createInsuranceApplication(InsuranceApplication insuranceApplication) throws Exception ;

	boolean delete(String applicationId) throws Exception ;

	ArrayList<InsuranceApplication> retrieve();

	boolean update();

	InsuranceApplication getApplicationbyId(String input);

	void updateInsuranceApplication(InsuranceApplication insuranceApplication);

}