package Interface;

import java.util.ArrayList;
import java.util.List;





public interface CounselList {

	
	public boolean add(Counsel counsel)throws Exception;

	public boolean delete(String counselID) throws Exception;

	public ArrayList<Counsel> retrieve();

	public void update(Counsel updateCounsel)throws Exception;

	public Counsel getCounselbyId(String customerID);

	public List<Counsel> getCounselList(String customerID);
}
