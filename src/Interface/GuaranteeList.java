package Interface;

import java.util.ArrayList;


public interface GuaranteeList {
	boolean create(Guarantee newguarantee) throws Exception;

	ArrayList<Guarantee> getAllGuranteeByID(String insuranceID);

	boolean deleteGuranteeById(String insuranceID) throws Exception;
}