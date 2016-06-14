/**
 * AUTHOR: 			Matt Smith (with help from tutorial cited below)
 * DESIGNED FOR:	CKEC
 * DATE:			2016-06-06
 * PURPOSE:			a JDBC to connect to MySQL Camps Database
 */


package campRegistrationJDBC;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.*;

import net.miginfocom.swing.MigLayout;

public class CampsUI extends JPanel {

	/* It should be noted that the following class was
	 * completed using a tutorial online as a template.
	 * The tutorial can be found at the following URL:
	 * 
	 * http://www.developer.com/java/creating-a-jdbc-gui-application.html
	 */

	//private AbstractFormatter format = new JFormattedTextField();
	private JFormattedTextField.AbstractFormatter format =  new JFormattedTextField.AbstractFormatter() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2L;

		@Override
		public String valueToString(Object value) throws ParseException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object stringToValue(String text) throws ParseException {
			// TODO Auto-generated method stub
			return null;
		}
	};

	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;

	private static final long serialVersionUID = 1L;
	private JTextField idField = new JTextField(11);
	private JTextField nameField = new JTextField(30);
	private JTextField priceWeekly = new JTextField(10);
	private JTextField priceDaily = new JTextField(10);
	private JTextField priceHalfDay = new JTextField(10);

	//Add start and end date 
	//private Date startDate = new Date(1, 1, 1);
	//private JFormattedTextField endDate = new JFormattedTextField(format);
	//private JTextField camper_id = new JTextField(11);

	private JButton createButton = new JButton ("New");
	private JButton updateButton = new JButton ("Update");
	private JButton deleteButton = new JButton ("Delete");
	private JButton firstButton = new JButton ("<-- First");
	private JButton prevButton = new JButton ("<- Previous");
	private JButton nextButton = new JButton ("Next -> ");
	private JButton lastButton = new JButton ("Last --> ");

	private CampsBean bean = new CampsBean();

	public CampsUI()
	{
		setBorder(new TitledBorder
				(new EtchedBorder(),"Camp Details"));
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
		setFieldData(bean.moveFirst());
	}

	private JPanel initButtons()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));


		panel.add(createButton);
		createButton.addActionListener(new ButtonHandler());
		panel.add(updateButton);
		updateButton.addActionListener(new ButtonHandler());
		panel.add(deleteButton);
		deleteButton.addActionListener(new ButtonHandler());
		panel.add(firstButton);
		firstButton.addActionListener(new ButtonHandler());
		panel.add(prevButton);
		prevButton.addActionListener(new ButtonHandler());
		panel.add(nextButton);
		nextButton.addActionListener(new ButtonHandler());
		panel.add(lastButton);
		lastButton.addActionListener(new ButtonHandler());

		return panel;
	}

	private JPanel initFields()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.add(new JLabel("ID"), "align label");
		panel.add(idField, "wrap");
		idField.setEnabled(false);
		panel.add(new JLabel("Name"), "align label");
		panel.add(nameField, "wrap");

		panel.add(new JLabel("Price Weekly"), "align label");
		panel.add(priceWeekly, "wrap");
		panel.add(new JLabel("Price Daily"), "align label");
		panel.add(priceDaily, "wrap");
		panel.add(new JLabel("Price Half Day"), "align label");
		panel.add(priceHalfDay, "wrap");


		UtilDateModel model=new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		startDatePicker = new JDatePickerImpl(datePanel, new DateFormatter());
		startDatePicker.setBounds(220,350,120,30);

		panel.add(new JLabel("Start Date"), "align label");
		panel.add(startDatePicker);

		endDatePicker = new JDatePickerImpl(datePanel, new DateFormatter());
		endDatePicker.setBounds(220,350,120,30);

		// this is a workaround, if something breaks in the UI its probably because of this
		panel.add(new JLabel(""), "wrap");  
		panel.add(new JLabel("End Date"), "align label");
		panel.add(endDatePicker);

		return panel;
	}

	private Camps getFieldData() {
		Camps c = new Camps();
		c.setId(Integer.parseInt(idField.getText()));
		c.setName(nameField.getText());
		c.setPriceWeekly(Double.parseDouble(priceWeekly.getText()));
		c.setPriceDaily(Double.parseDouble(priceDaily.getText()));
		c.setPriceHalfDay(Double.parseDouble(priceHalfDay.getText()));
		
		//TODO add start and end dates
		//c.setStartDate(startDate);
		//c.setEndDate(fhkssdh);

		//c.setCamper_id(Integer.parseInt(camper_id.getText()));

		return c;
	}

	private void setFieldData(Camps c) {
		idField.setText(String.valueOf(	String.valueOf(c.getId())	));
		nameField.setText(c.getName());
		priceWeekly.setText(String.valueOf(c.getPriceWeekly()));
		priceDaily.setText(String.valueOf(c.getPriceDaily()));
		priceHalfDay.setText(String.valueOf(c.getPriceHalfDay()));
		//startDate.setDateFormat((Date) c.getStartDate());
		//start date
		//end date
		//camper_id.setText(String.valueOf(c.getCamper_id()));
	}

	private boolean isEmptyFieldData() {
		return (nameField.getText().trim().isEmpty()
				&& priceWeekly.getText().trim().isEmpty()
				&& priceDaily.getText().trim().isEmpty()
				&& priceHalfDay.getText().trim().isEmpty()
				// start date
				// end date
				//&& camper_id.getText().trim().isEmpty())
				);
	}

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Camps c = getFieldData();
			switch (e.getActionCommand()) {
			case "Save":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null,
							"Cannot create an empty record");
					return;
				}
				if (bean.create(c) != null)
					JOptionPane.showMessageDialog(null,
							"New person created successfully.");
				createButton.setText("New...");
				break;
			case "New":
				c.setId(new Random()
						.nextInt(Integer.MAX_VALUE) + 1);
				c.setName("");
				c.setPriceWeekly(150);
				c.setPriceDaily(32);
				c.setPriceHalfDay(20);
				//c.setCamper_id(new Random().nextInt(Integer.MAX_VALUE + 1));
				setFieldData(c);
				createButton.setText("Save");
				break;
			case "Update":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null,
							"Cannot update an empty record");
					return;
				}
				if (bean.update(c) != null)
					JOptionPane.showMessageDialog(
							null,"Person with ID:" + String.valueOf(c.getId()
									+ " is updated successfully"));
				break;
			case "Delete":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null,
							"Cannot delete an empty record");
					return;
				}
				c = bean.getCurrent();
				bean.delete();
				JOptionPane.showMessageDialog(
						null,"Person with ID:"
								+ String.valueOf(c.getId()
										+ " is deleted successfully"));
				break;
			case "<-- First":
				setFieldData(bean.moveFirst()); break;
			case "<- Previous":
				setFieldData(bean.movePrevious()); break;
			case "Next ->":
				setFieldData(bean.moveNext()); break;
			case "Last -->":
				setFieldData(bean.moveLast()); break;
			default:
				JOptionPane.showMessageDialog(null,
						"invalid command");
			}
		}
	}
}
