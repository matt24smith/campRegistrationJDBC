package campRegistrationJDBC;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Camps {
	private int id;
	private String name;
	private double priceWeekly;
	private double priceDaily;
	private double priceHalfDay;
	private Date startDate;
	java.sql.Date sqlStartDate;
	private Date endDate;
	java.sql.Date sqlEndDate;
	private int camper_id;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public java.sql.Date getSqlStartDate() {
		java.sql.Date sqlDate = new java.sql.Date(this.getStartDate().getTime());
		return sqlDate;
	}

	public java.sql.Date getSqlEndDate() {
		java.sql.Date sqlDate = new java.sql.Date(this.getEndDate().getTime());
		return sqlDate;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the priceWeekly
	 */
	public double getPriceWeekly() {
		return priceWeekly;
	}

	/**
	 * @param priceWeekly
	 *            the priceWeekly to set
	 */
	public void setPriceWeekly(double priceWeekly) {
		this.priceWeekly = priceWeekly;
	}

	/**
	 * @return the priceDaily
	 */
	public double getPriceDaily() {
		return priceDaily;
	}

	/**
	 * @param priceDaily
	 *            the priceDaily to set
	 */
	public void setPriceDaily(double priceDaily) {
		this.priceDaily = priceDaily;
	}

	/**
	 * @return the priceHalfDay
	 */
	public double getPriceHalfDay() {
		return priceHalfDay;
	}

	/**
	 * @param priceHalfDay
	 *            the priceHalfDay to set
	 */
	public void setPriceHalfDay(double priceHalfDay) {
		this.priceHalfDay = priceHalfDay;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param dateFormat
	 *            the startDate to set
	 */
	public void setStartDate(Date selectedStartDate) {
		this.startDate = selectedStartDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the camper_id
	 */
	public int getCamper_id() {
		return camper_id;
	}

	/**
	 * @param camper_id
	 *            the camper_id to set
	 */
	public void setCamper_id(int camper_id) {
		this.camper_id = camper_id;
	}

}
