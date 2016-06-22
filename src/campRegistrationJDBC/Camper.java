package campRegistrationJDBC;

import java.util.Date;

public class Camper {
	private int 	id;
	private String 	fname;
	private String 	lname;
	private int 	lunchesOrdered;
	private Date 	registrationDate;
	private double 	amountPaid;
	private String 	notes;
	
	
	
	public java.sql.Date getSqlRegDate()
	{
		//returns the date in a format compatible with MySQL DB
		return new java.sql.Date(this.getRegistrationDate().getTime());
	}
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @return the lunchesOrdered
	 */
	public int getLunchesOrdered() {
		return lunchesOrdered;
	}
	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}
	/**
	 * @return the amountPaid
	 */
	public Double getAmountPaid() {
		return amountPaid;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @param lunchesOrdered the lunchesOrdered to set
	 */
	public void setLunchesOrdered(int lunchesOrdered) {
		this.lunchesOrdered = lunchesOrdered;
	}
	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
}
