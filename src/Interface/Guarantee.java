package Interface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Guarantee implements Serializable {
    private static final long serialVersionUID = 1L;

	private String insuranceID;
	private String termsID;

	public Guarantee(){
	}
	
	  public String toString() {
	        String stringReturn = this.insuranceID + " " + this.termsID;
	        return stringReturn;
	    }

	public boolean matchInsuranceId(String insuranceID) {
		return this.insuranceID.equals(insuranceID);
	}

	
	public String getInsuranceID() {
		return insuranceID;
	}


	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}


	public String getTermsID() {
		return termsID;
	}


	public void setTermsID(String termsID) {
		this.termsID = termsID;
	}

	

    public boolean matchID(String insuranceID) {
		return (this.insuranceID.equals(insuranceID));
    }
}
