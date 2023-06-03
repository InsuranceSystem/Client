package Interface;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public interface PaymentList {

	boolean add(String paymentInfo) throws ParseException, IOException ;

	ArrayList<Payment> retrieve() throws Exception ;

	boolean updateCancellation(String customerId, String insuranceId) throws IOException ;

	ArrayList<String> retreiveUnpaidCustomerId();

	ArrayList<Payment> retreiveCustomerPayment(String selectedCustomerId);

	ArrayList<String> retreiveDateStatusById(String selectedCustomerId, String selectedInsuranceId);

	void update();

	ArrayList<Payment> retreiveCustomerInsurancePayment(String customerId, String selectedInsuranceId);

	boolean updateWhetherPayment(String selectedCustomerId, String selectedInsuranceId);

	void update(Payment payment);
	


}