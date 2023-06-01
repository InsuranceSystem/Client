package Interface;

import java.util.ArrayList;


public interface CompensationClaimList {

	boolean createCompensationClaim(CompensationClaim compensationClaim) throws Exception;

	ArrayList<CompensationClaim> retrieve();

	boolean update();

	CompensationClaim getCompensationClaimbyID(String ccid);

}