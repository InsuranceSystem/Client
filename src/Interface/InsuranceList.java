package Interface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public interface InsuranceList{

	boolean createInsurance(Insurance insurance) throws FileNotFoundException, IOException, Exception;

	boolean updateinsurance(Insurance updateInsurance) throws Exception;

	ArrayList<Insurance> retrieve();

	boolean deleteInsurance(String insuranceId) throws Exception;

	Insurance getInsurancebyId(String insuranceID);

	Insurance retrieveInsuranceDetail(String insuranceID);

	ArrayList<Insurance> getUnregisteredInsuranceList();

	String requestAuthorization(String insuranceID);

	boolean updateAuthorization(String insuranceID, boolean b) throws Exception;

	ArrayList<Insurance> getOnSaleInsuranceList();

	ArrayList<Insurance> retrieveInsurance(String string);

	boolean isExistInsuranceDesign(String insuranceID);

	ArrayList<String> getInsuranceNameTypeInfoById(String string);

	Collection<? extends String> getInsuranceNameById(String insurance);

	String getInsuranceIdbyName(String string);




}