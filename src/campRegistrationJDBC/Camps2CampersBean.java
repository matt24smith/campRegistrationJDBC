package campRegistrationJDBC;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.JOptionPane;

import com.sun.rowset.JdbcRowSetImpl;

public class Camps2CampersBean {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/camp_registration";
	static final String DB_USER = "matt";
	static final String DB_PASS = "Creative2016";
	private JdbcRowSet rowSet = null;

	public Camps2CampersBean() {
		try {
			Class.forName(JDBC_DRIVER);
			rowSet = new JdbcRowSetImpl();
			rowSet.setUrl(DB_URL);
			rowSet.setUsername(DB_USER);
			rowSet.setPassword(DB_PASS);
			rowSet.setCommand("SELECT * FROM camps2campers");
			rowSet.execute();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public Camps2Campers create(Camps2Campers c) {
		// creates a new camp to camper relation
		try {
			rowSet.moveToInsertRow();
			rowSet.updateInt("campId", c.getCampId());
			rowSet.updateInt("camperId", c.getCamperId());
			rowSet.updateDouble("registrationDiscount", c.getRegistrationDiscount());
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

	public boolean delete(boolean printMessage) {
		// deletes the relation from the DB.
		// If printmessage == true, it will display a status message about the
		// delete operation
		try {
			rowSet.moveToCurrentRow();
			if (rowSet.isFirst() == true && rowSet.isLast() == true) {

				if (printMessage == true)
					JOptionPane.showMessageDialog(null,
							"Error: Cannot delete the only registration in the database!\nRegister some more campers before deleting any more.");
				Thread.dumpStack();
				return false;
			}
			rowSet.deleteRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
		}
		if (printMessage == true)
			JOptionPane.showMessageDialog(null, "Camper has been removed from registration.");
		return true;
	}

	public String[] getCamperNames() {	
		ArrayList<String> camperNames = new ArrayList<String>();

		try {
			rowSet.setCommand("SELECT * FROM campers");
			rowSet.execute();
			rowSet.first();
			//System.out.println(rowSet.getString(3) + ", " + rowSet.getString(2));
			camperNames.add(rowSet.getString(3) + ", " + rowSet.getString(2));
			while (rowSet.next()) {
				//System.out.println(rowSet.getString(3) + ", " + rowSet.getString(2));
				camperNames.add(rowSet.getString(3) + ", " + rowSet.getString(2));
			}

			rowSet.setCommand("SELECT * FROM camps2campers");
			rowSet.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return camperNames.toArray(new String[camperNames.size()]);
	}
	
	public String[] getCampNames() {	
		ArrayList<String> campNames = new ArrayList<String>();

		try {
			rowSet.setCommand("SELECT * FROM camps");
			rowSet.execute();
			rowSet.first();
			//System.out.println(rowSet.getString(2));
			campNames.add(rowSet.getString(2));
			while (rowSet.next()) {
				//System.out.println(rowSet.getString(2));
				campNames.add(rowSet.getString(2));
			}

			rowSet.setCommand("SELECT * FROM camps2campers");
			rowSet.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return campNames.toArray(new String[campNames.size()]);
	}
}
