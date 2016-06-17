package campRegistrationJDBC;

import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import com.sun.rowset.JdbcRowSetImpl;

public class CamperBean {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/camp_registration";
	static final String DB_USER = "matt";
	static final String DB_PASS = "Creative2016";
	private JdbcRowSet rowSet = null;

	public CamperBean() {
		try {
			Class.forName(JDBC_DRIVER);
			rowSet = new JdbcRowSetImpl();
			rowSet.setUrl(DB_URL);
			rowSet.setUsername(DB_USER);
			rowSet.setPassword(DB_PASS);
			rowSet.setCommand("SELECT * FROM campers");
			rowSet.execute();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public Camper create(Camper c) {
		// interacts with DB to create a new record under campers table
		try {

			rowSet.moveToInsertRow();
			rowSet.updateInt("id", c.getId());
			rowSet.updateString("fname", c.getFname());
			rowSet.updateString("lname", c.getLname());
			rowSet.updateInt("lunchesOrdered", c.getLunchesOrdered());

			// create sql object from java Date
			//java.sql.Date sqlRegDate = new java.sql.Date(c.getRegistrationDate().getTime());
			rowSet.updateDate("registrationDate", c.getSqlRegDate());
			

			rowSet.updateString("notes", c.getNotes());

			rowSet.insertRow();
			rowSet.moveToCurrentRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
				c = null;
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return c;

	}

	public Camper update(Camper c) {
		// updates a record
		try {
			
			/*
			//rowSet.moveToInsertRow();
			rowSet.updateInt("id", c.getId());
			rowSet.updateString("fname", c.getFname());
			rowSet.updateString("lname", c.getLname());
			rowSet.updateInt("lunchesOrdered", c.getLunchesOrdered());
			rowSet.updateDate("registrationDate", c.getSqlRegDate());
			rowSet.updateDate("registrationDate", c.getSqlRegDate());

			rowSet.updateString("notes", c.getNotes());
			
			//rowSet.moveToCurrentRow();
			 * 
			 * 
			 */
			
			
			rowSet.moveToCurrentRow();
			delete();
			rowSet.moveToInsertRow();
			create(c);

		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return c;
	}

	public void delete() {
		// deletes a record
		try {
			rowSet.moveToCurrentRow();
			rowSet.deleteRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
		}

	}

	public Camper moveFirst() {
		Camper c = new Camper();

		try {
			rowSet.first();
			c.setId(rowSet.getInt("id"));
			c.setFname(rowSet.getString("fname"));
			c.setLname(rowSet.getString("lname"));
			c.setLunchesOrdered(rowSet.getInt("lunchesOrdered"));
			c.setRegistrationDate(rowSet.getDate("registrationDate"));
			c.setAmountPaid(rowSet.getDouble("amountPaid"));
			c.setNotes(rowSet.getString("notes"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	public Camper moveLast() {
		Camper c = new Camper();

		try {
			rowSet.last();
			c.setId(rowSet.getInt("id"));
			c.setFname(rowSet.getString("fname"));
			c.setLname(rowSet.getString("lname"));
			c.setLunchesOrdered(rowSet.getInt("lunchesOrdered"));
			c.setRegistrationDate(rowSet.getDate("registrationDate"));
			c.setAmountPaid(rowSet.getDouble("amountPaid"));
			c.setNotes(rowSet.getString("notes"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	public Camper moveNext() {
		Camper c = new Camper();

		try {
			if (rowSet.next() == false)
				rowSet.previous();
			c.setId(rowSet.getInt("id"));
			c.setFname(rowSet.getString("fname"));
			c.setLname(rowSet.getString("lname"));
			c.setLunchesOrdered(rowSet.getInt("lunchesOrdered"));
			c.setRegistrationDate(rowSet.getDate("registrationDate"));
			c.setAmountPaid(rowSet.getDouble("amountPaid"));
			c.setNotes(rowSet.getString("notes"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	public Camper movePrevious() {
		Camper c = new Camper();

		try {
			if (rowSet.previous() == false)
				rowSet.next();
			c.setId(rowSet.getInt("id"));
			c.setFname(rowSet.getString("fname"));
			c.setLname(rowSet.getString("lname"));
			c.setLunchesOrdered(rowSet.getInt("lunchesOrdered"));
			c.setRegistrationDate(rowSet.getDate("registrationDate"));
			c.setAmountPaid(rowSet.getDouble("amountPaid"));
			c.setNotes(rowSet.getString("notes"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	public Camper getCurrent() {
		Camper c = new Camper();
		try {
			rowSet.moveToCurrentRow();
			c.setId(rowSet.getInt("id"));
			c.setFname(rowSet.getString("fname"));
			c.setLname(rowSet.getString("lname"));
			c.setLunchesOrdered(rowSet.getInt("lunchesOrdered"));
			c.setRegistrationDate(rowSet.getDate("registrationDate"));
			c.setAmountPaid(rowSet.getDouble("amountPaid"));
			c.setNotes(rowSet.getString("notes"));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}
}
