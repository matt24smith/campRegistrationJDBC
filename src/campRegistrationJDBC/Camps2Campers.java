package campRegistrationJDBC;

public class Camps2Campers {

	private int campId;
	private int camperId;
	private double registrationDiscount;
	
	public int getCampId() {
		return campId;
	}
	public void setCampId(int campId) {
		this.campId = campId;
	}
	public int getCamperId() {
		return camperId;
	}
	public void setCamperId(int camperId) {
		this.camperId = camperId;
	}
	public double getRegistrationDiscount() {
		return registrationDiscount;
	}
	public void setRegistrationDiscount(double registrationDiscount) {
		this.registrationDiscount = registrationDiscount;
	}
}
