package campRegistrationJDBC;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class CamperUI extends JPanel{

	private JTextField idField = new JTextField(11);
	private JTextField fnameField = new JTextField(30);
	private JTextField lnameField = new JTextField(30);
	private JTextField lunchesOrderedField = new JTextField(3);
	private JTextField amountPaidField = new JTextField(8);
	private JTextField notesField = new JTextField(2000);

	private CamperBean bean = new CamperBean();

	private UtilDateModel model;
	private Properties p;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	//button declarations
	private JButton createButton = new JButton ("New");
	private JButton updateButton = new JButton ("Update");
	private JButton deleteButton = new JButton ("Delete");
	private JButton firstButton = new JButton ("<-- First");
	private JButton prevButton = new JButton ("<- Previous");
	private JButton nextButton = new JButton ("Next ->");
	private JButton lastButton = new JButton ("Last -->");


	public CamperUI()
	{
		setBorder(new TitledBorder
				(new EtchedBorder(),"Camper Details"));
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
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.add(new JLabel("ID"), "align label");
		panel.add(idField, "wrap");
		panel.add(new JLabel("First Name"), "align label");
		panel.add(fnameField, "wrap");
		panel.add(new JLabel("Last Name"), "align label");
		panel.add(lnameField, "wrap");
		panel.add(new JLabel("Lunches Ordered"), "align label");
		panel.add(lunchesOrderedField, "wrap");
		panel.add(new JLabel("Amount Paid"), "align label");
		panel.add(amountPaidField, "wrap");
		
		model = new UtilDateModel();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		panel.add(new JLabel("Registered On"), "align label");
		panel.add(datePicker, "wrap");
		
		return panel;
	}
	
	private Camper getFieldData()
	{
		Camper c = new Camper();
		c.setId(Integer.parseInt(idField.getText()));
		c.setFname(fnameField.getText());
		c.setLname(lnameField.getText());
		c.setLunchesOrdered(Integer.parseInt(lunchesOrderedField.getText()));
		c.setAmountPaid(Double.parseDouble(amountPaidField.getText()));
		c.setNotes(notesField.getText());
		
		c.setRegistrationDate((Date) datePicker.getModel().getValue());
		
		return c;
	}
}
