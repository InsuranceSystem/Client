package Interface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface CounselApplicationList {

	public boolean add(CounselApplication counselApplication);
	public boolean delete(String counselID);
	public boolean update(CounselApplication counselApplication, String counselID);
	public ArrayList<CounselApplication> retrieve();
	//고쳐주세요..!
	public CounselApplication getConsuleInfo(String id, LocalDate date,
			CounselApplicationList counselApplicationListImpl);
	public List<CounselApplication> getCounselList(Customer customer,
			CounselApplicationList counselApplicationListImpl);

}
