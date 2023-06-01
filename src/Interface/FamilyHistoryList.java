package Interface;

import java.util.ArrayList;
import java.util.Collection;


public interface FamilyHistoryList {

	public boolean add(FamilyHistory familyHistory);

	public boolean delete(String customerID);

	public ArrayList<FamilyHistory> retrieve();

	public boolean update(FamilyHistory familyHistory, String customerID) ;

	public ArrayList<FamilyHistory> getFamilyHistoryByCID(String customerID);

	public FamilyHistory getFamilyHistoryFromId(String customerID, FamilyHistoryList familyHistoryListImpl);

	public ArrayList<FamilyHistory> getAllFamilyHistoryFromId(String selectedCustomerId,
			FamilyHistoryList familyHistoryListImpl);

}
