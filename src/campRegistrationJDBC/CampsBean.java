/**
 * AUTHOR: 			Matt Smith (with help from tutorial cited below)
 * DESIGNED FOR:	CKEC
 * DATE:			2016-06-06
 * PURPOSE:			a JDBC to connect to MySQL Camps Database
 */

package campRegistrationJDBC;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.JOptionPane;

import com.sun.rowset.JdbcRowSetImpl;

public class CampsBean {

	/* It should be noted that the following class was
	 * completed using a tutorial online as a template.
	 * The tutorial can be found at the following URL:
	 * 
	 * http://www.developer.com/java/creating-a-jdbc-gui-application.html
	 */

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL =
			"jdbc:mysql://localhost:3306/camp_registration";
	static final String DB_USER = "matt";
	static final String DB_PASS = "Creative2016";
	private JdbcRowSet rowSet = null;
	public CampsBean() 
	{
		try 
		{
			Class.forName(JDBC_DRIVER);
			rowSet = new JdbcRowSetImpl();
			rowSet.setUrl(DB_URL);
			rowSet.setUsername(DB_USER);
			rowSet.setPassword(DB_PASS);
			rowSet.setCommand("SELECT * FROM Camps");
			rowSet.execute();   
		}
		catch (SQLException | ClassNotFoundException ex) 
		{
			ex.printStackTrace();
		}
	}

	public Camps create (Camps c) 
	{
		// interacts with DB to create a new record under camps table
		try 
		{
			
			rowSet.moveToInsertRow();
			rowSet.updateInt("id", c.getId());
			rowSet.updateString("name", c.getName());
			rowSet.updateDouble("priceWeekly", c.getPriceWeekly());
			rowSet.updateDouble("priceDaily", c.getPriceDaily());
			rowSet.updateDouble("priceHalfDay", c.getPriceHalfDay());
			rowSet.updateDate("startDate", c.getSqlStartDate());
			rowSet.updateDate("endDate", c.getSqlEndDate());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();  
		}
		catch (SQLException ex) 
		{
			try {
				rowSet.rollback();
				c = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		return c;

	}

	public Camps update (Camps c, boolean printMessage)
	{
		//updates a record
		try {
			rowSet.moveToCurrentRow();
			if (delete(false) == false){
				JOptionPane.showMessageDialog(null, "Cannot update last camp.\nUse the \"New\" button to create a new camp before updating this one.");
				printMessage = false;
			} else {
				rowSet.moveToInsertRow();
				create(c);
			}
			
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		if (printMessage == true) JOptionPane.showMessageDialog(null, "Camp has been updated successfully.");
		return c;
	}

	public boolean delete(boolean printMessage){
		//deletes a record
		try {
			rowSet.moveToCurrentRow();
			if (rowSet.isFirst() == true && rowSet.isLast() == true){
				 if (printMessage == true) JOptionPane.showMessageDialog(null, "Error 3b: Cannot delete the only camp in the database!\nAdd a new camp before deleting any more.");
				 Thread.dumpStack();
				 return false;
			}
			
			rowSet.deleteRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) { }
			ex.printStackTrace();
		}
		
		if (printMessage == true) JOptionPane.showMessageDialog(null, "Camp has been deleted successfully.");
		return true;
	}

	public Camps moveFirst(){
		Camps c = new Camps();

		try {
			rowSet.first();
			c.setId(rowSet.getInt("id"));
			c.setName(rowSet.getString("name"));
			c.setPriceWeekly(rowSet.getDouble("priceWeekly"));
			c.setPriceDaily(rowSet.getDouble("priceDaily"));
			c.setPriceHalfDay(rowSet.getDouble("priceHalfDay"));
			c.setStartDate(rowSet.getDate("startDate"));
			c.setEndDate(rowSet.getDate("endDate"));
		

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	public Camps moveLast() {
		Camps c = new Camps();
		try {
			rowSet.last();
			c.setId(rowSet.getInt("id"));
			c.setName(rowSet.getString("name"));
			c.setPriceWeekly(rowSet.getDouble("priceWeekly"));
			c.setPriceDaily(rowSet.getDouble("priceDaily"));
			c.setPriceHalfDay(rowSet.getDouble("priceHalfDay"));
			c.setStartDate(rowSet.getDate("startDate"));
			c.setEndDate(rowSet.getDate("endDate"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	public Camps moveNext() {
		Camps c= new Camps();
		try {
			if (rowSet.next() == false)
				rowSet.previous();
			c.setId(rowSet.getInt("id"));
			c.setName(rowSet.getString("name"));
			c.setPriceWeekly(rowSet.getDouble("priceWeekly"));
			c.setPriceDaily(rowSet.getDouble("priceDaily"));
			c.setPriceHalfDay(rowSet.getDouble("priceHalfDay"));
			c.setStartDate(rowSet.getDate("startDate"));
			c.setEndDate(rowSet.getDate("endDate"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	public Camps movePrevious() {
		Camps c = new Camps();
		try {
			if (rowSet.previous() == false)
				rowSet.next();
			c.setId(rowSet.getInt("id"));
			c.setName(rowSet.getString("name"));
			c.setPriceWeekly(rowSet.getDouble("priceWeekly"));
			c.setPriceDaily(rowSet.getDouble("priceDaily"));
			c.setPriceHalfDay(rowSet.getDouble("priceHalfDay"));
			c.setStartDate(rowSet.getDate("startDate"));
			c.setEndDate(rowSet.getDate("endDate"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	public Camps getCurrent() {
		Camps c = new Camps();
		try {
			rowSet.moveToCurrentRow();
			c.setId(rowSet.getInt("id"));
			c.setName(rowSet.getString("name"));
			c.setPriceWeekly(rowSet.getDouble("priceWeekly"));
			c.setPriceDaily(rowSet.getDouble("priceDaily"));
			c.setPriceHalfDay(rowSet.getDouble("priceHalfDay"));
			c.setStartDate(rowSet.getDate("startDate"));
			c.setEndDate(rowSet.getDate("endDate"));
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}
}
