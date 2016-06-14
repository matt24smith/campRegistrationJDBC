/**
 * AUTHOR: 			Matt Smith (with help from tutorial cited below)
 * DESIGNED FOR:	CKEC
 * DATE:			2016-06-06
 * PURPOSE:			a JDBC to connect to MySQL Camps Database
 */

package campRegistrationJDBC;

import java.sql.Date;
import java.sql.SQLException;
import java.time.ZoneId;

import com.sun.rowset.*;

import javax.sql.rowset.*;

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
		try 
		{
			rowSet.moveToInsertRow();
			rowSet.updateInt("id", c.getId());
			rowSet.updateString("name", c.getName());
			rowSet.updateDouble("priceWeekly", c.getPriceWeekly());
			rowSet.updateDouble("priceDaily", c.getPriceDaily());
			rowSet.updateDouble("priceHalfDay", c.getPriceHalfDay());
			//rowSet.updateDate("startDate", (Date) c.getStartDate());
			rowSet.updateDate("endDate", (Date) c.getEndDate());
			//rowSet.updateInt("camper_id", c.getCamper_id());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();  
		}
		catch (SQLException ex) 
		{
			try {
				rowSet.rollback();
				c = null;
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return c;

	}

	public Camps update (Camps c)
	{
		try {
			rowSet.updateInt("id", c.getId());
			rowSet.updateString("name", c.getName());
			rowSet.updateDouble("priceWeekly", c.getPriceWeekly());
			rowSet.updateDouble("priceDaily", c.getPriceDaily());
			rowSet.updateDouble("priceHalfDay", c.getPriceHalfDay());
			rowSet.updateString("startDate", c.sdf.format(c.getStartDate()));
			rowSet.updateDate("endDate", (Date) c.getEndDate());
			//rowSet.updateInt("camper_id", c.getCamper_id());
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return c;
	}

	public void delete(){
		try {
			rowSet.moveToCurrentRow();
			rowSet.deleteRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) { }
			ex.printStackTrace();
		}
	}

	public Camps moveFirst(){
		Camps c = new Camps();

		try {
			rowSet.first();
			//c.setId(rowSet.getInt("id"));
			c.setName(rowSet.getString("name"));
			c.setPriceWeekly(rowSet.getDouble("priceWeekly"));
			c.setPriceDaily(rowSet.getDouble("priceDaily"));
			c.setPriceHalfDay(rowSet.getDouble("priceHalfDay"));
			
			Date date = (Date) Date.from(c.startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			//c.setStartDate(rowSet.getDate(date));
			c.setEndDate(rowSet.getDate("endDate"));
			//c.setCamper_id(rowSet.getInt("camper_id"));

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
			//c.setStartDate(rowSet.getDate("startDate"));
			c.setEndDate(rowSet.getDate("endDate"));
			//c.setCamper_id(rowSet.getInt("camper_id"));

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
			//c.setStartDate(rowSet.getDate("startDate"));
			c.setEndDate(rowSet.getDate("endDate"));
			//c.setCamper_id(rowSet.getInt("camper_id"));

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
			//c.setStartDate(rowSet.getDate("startDate"));
			c.setEndDate(rowSet.getDate("endDate"));
			//c.setCamper_id(rowSet.getInt("camper_id"));

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
			//c.setStartDate(rowSet.getDate("startDate"));
			c.setEndDate(rowSet.getDate("endDate"));
			//c.setCamper_id(rowSet.getInt("camper_id"));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}
}
