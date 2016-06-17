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
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import net.miginfocom.swing.MigLayout;

public class CampsUI extends JPanel /*implements ActionListener */{

	/* It should be noted that the following class was
	 * completed using a tutorial online as a template.
	 * The tutorial can be found at the following URL:
	 * 
	 * http://www.developer.com/java/creating-a-jdbc-gui-application.html
	 */


	
	private static final long serialVersionUID = 1L;
	//textfield declarations
	private JTextField idField = new JTextField(11);
	private JTextField nameField = new JTextField(30);
	private JTextField priceWeekly = new JTextField(10);
	private JTextField priceDaily = new JTextField(10);
	private JTextField priceHalfDay = new JTextField(10);

	//button declarations
	private JButton createButton = new JButton ("New");
	private JButton updateButton = new JButton ("Update");
	private JButton deleteButton = new JButton ("Delete");
	private JButton firstButton = new JButton ("<-- First");
	private JButton prevButton = new JButton ("<- Previous");
	private JButton nextButton = new JButton ("Next ->");
	private JButton lastButton = new JButton ("Last -->");

	private CampsBean bean = new CampsBean();

	//declarations for datepicker
	private UtilDateModel model, endModel; 
	private Properties p, endp;
	
	private JDatePanelImpl datePanel; 
	private JDatePickerImpl datePicker;
	private JDatePanelImpl endDatePanel; 
	private JDatePickerImpl endDatePicker;
	
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
		//initializes the buttons on the screen
		
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
		//initializes any sort of input field, including date pickers
		
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


		model = new UtilDateModel();
		endModel = new UtilDateModel();	//second one is for the end date picker
		p = new Properties();
		endp = new Properties();		// ^
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		endp.put("text.today", "Today");
		endp.put("text.month", "Month");
		endp.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		endDatePanel = new JDatePanelImpl(endModel, endp);
		endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
		
		panel.add(new JLabel("Start Date"), "align label");
		panel.add(datePicker, "wrap");
		panel.add(new JLabel("End Date"), "align label");
		panel.add(endDatePicker);


		return panel;
	}

	private Camps getFieldData() {
		//the method names are pretty self descriptive. 
		
		Camps c = new Camps();
		c.setId(Integer.parseInt(idField.getText()));
		c.setName(nameField.getText());
		c.setPriceWeekly(Double.parseDouble(priceWeekly.getText()));
		c.setPriceDaily(Double.parseDouble(priceDaily.getText()));
		c.setPriceHalfDay(Double.parseDouble(priceHalfDay.getText()));
		
		
		c.setStartDate((Date)datePicker.getModel().getValue());
		c.setEndDate((Date)endDatePicker.getModel().getValue());
		
		return c;
	}

	private void setFieldData(Camps c) {
		// ... yup
		idField.setText(String.valueOf(	c.getId()));
		nameField.setText(c.getName());
		priceWeekly.setText(String.valueOf(c.getPriceWeekly()));
		priceDaily.setText(String.valueOf(c.getPriceDaily()));
		priceHalfDay.setText(String.valueOf(c.getPriceHalfDay()));
		datePicker.getJFormattedTextField().setText(c.getStartDate().toString());
		endDatePicker.getJFormattedTextField().setText(c.getStartDate().toString());
	}

	private boolean isEmptyFieldData() {
		// is it empty?
		
		//TODO check this and fix the requirements
		
		return (nameField.getText().trim().isEmpty()
				&& priceWeekly.getText().trim().isEmpty()
				&& priceDaily.getText().trim().isEmpty()
				&& priceHalfDay.getText().trim().isEmpty()
				);
	}

	private class ButtonHandler implements ActionListener {
		// BUTTONS!
		
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
							"New camp created successfully.");
				createButton.setText("New");
				break;
			case "New":
				c.setId(new Random()
						.nextInt(Integer.MAX_VALUE) + 1);
				c.setName("");
				c.setPriceWeekly(150);
				c.setPriceDaily(32);
				c.setPriceHalfDay(20);
				setFieldData(c);
				datePicker.getJFormattedTextField().setText("");
				endDatePicker.getJFormattedTextField().setText("");
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
							null,"Camp " + String.valueOf(c.getName()
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
						null,"Camp "
								+ String.valueOf(c.getName()
										+ " is deleted successfully"));
				break;
			case "<-- First":
				setFieldData(bean.moveFirst()); 
				createButton.setText("New");
				break;
			case "<- Previous":
				setFieldData(bean.movePrevious()); 
				createButton.setText("New");
				break;
			case "Next ->":
				setFieldData(bean.moveNext()); 
				createButton.setText("New");
				break;
			case "Last -->":
				setFieldData(bean.moveLast()); 
				createButton.setText("New");
				break;
			default:
				JOptionPane.showMessageDialog(null,
						"invalid command");
			}
		}
	}
}
