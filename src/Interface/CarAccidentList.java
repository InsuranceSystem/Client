package Interface;

import java.util.ArrayList;


public interface CarAccidentList {

	boolean add();

	boolean delete();

	ArrayList<CarAccident> retrieve();

	boolean update();

	boolean createCarAccident(CarAccident carAccident);

}