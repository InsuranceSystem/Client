package Interface;

import java.util.ArrayList;

public interface TermsList {
	boolean createTerms(Terms terms) throws Exception;
	ArrayList<Terms> retrieveAllTerms();
	Terms getTermsByID(String termsID);
	boolean isExistTermsID(String string);
}