package campRegistrationJDBC;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.JOptionPane;

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
			rowSet.updateDouble("amountPaid", c.getAmountPaid());

			try {
				rowSet.updateDate("registrationDate", c.getSqlRegDate());
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}

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

	public Camper update(Camper c, boolean printMessage) {
		// updates a record
		try {
			rowSet.moveToCurrentRow();

			if (delete(false) == false) {
				rowSet.moveToInsertRow();
				JOptionPane.showMessageDialog(null,
						"Cannot update last camper.\nUse the \"New\" button to create a new camper before updating this one.");
				create(c);
				printMessage = false;
			} else {
				rowSet.moveToInsertRow();
				create(c);
			}

		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		if (printMessage == true)
			JOptionPane.showMessageDialog(null, "Camper has been updated successfully.");
		return c;
	}

	public boolean delete(boolean printMessage) {
		try {
			rowSet.moveToCurrentRow();
			if (rowSet.isFirst() == true && rowSet.isLast() == true) {

				if (printMessage == true)
					JOptionPane.showMessageDialog(null,
							"Error 3: Cannot delete the only camper in the database!\nAdd a new camper before deleting any more.");
				Thread.dumpStack();
				return false;
			}

			// rowSet.moveToCurrentRow(); //moved above
			rowSet.deleteRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
		}

		if (printMessage == true)
			JOptionPane.showMessageDialog(null, "Camper has been deleted successfully.");
		return true;
	}

	public Camper moveFirst() {
		Camper c = new Camper();

		try {
			/*
			 * if (rowSet.first() == false){
			 * 
			 * rowSet.moveToInsertRow(); create(c);
			 * c.setId(rowSet.getInt("id"));
			 * c.setFname(rowSet.getString("fname"));
			 * c.setLname(rowSet.getString("lname"));
			 * c.setLunchesOrdered(rowSet.getInt("lunchesOrdered"));
			 * c.setRegistrationDate(rowSet.getDate("registrationDate"));
			 * c.setAmountPaid(rowSet.getDouble("amountPaid"));
			 * c.setNotes(rowSet.getString("notes")); update(c);
			 */
			// } else {
			rowSet.first();
			c.setId(rowSet.getInt("id"));
			c.setFname(rowSet.getString("fname"));
			c.setLname(rowSet.getString("lname"));
			c.setLunchesOrdered(rowSet.getInt("lunchesOrdered"));
			c.setRegistrationDate(rowSet.getDate("registrationDate"));
			c.setAmountPaid(rowSet.getDouble("amountPaid"));
			c.setNotes(rowSet.getString("notes"));

			// }

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
