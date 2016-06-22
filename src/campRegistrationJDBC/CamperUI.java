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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class CamperUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6225361288314703557L;
	private JTextField idField = new JTextField(11);
	private JTextField fnameField = new JTextField(30);
	private JTextField lnameField = new JTextField(30);
	private JTextField lunchesOrderedField = new JTextField(3);
	private JTextField amountPaidField = new JTextField(8);
	private JTextArea notesField = new JTextArea(4, 40);

	private CamperBean bean = new CamperBean();

	private UtilDateModel model;
	private Properties p;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	// button declarations
	private JButton createButton = new JButton("New");
	private JButton updateButton = new JButton("Update");
	private JButton deleteButton = new JButton("Delete");
	private JButton firstButton = new JButton("<-- First");
	private JButton prevButton = new JButton("<- Previous");
	private JButton nextButton = new JButton("Next ->");
	private JButton lastButton = new JButton("Last -->");

	public CamperUI() {
		setBorder(new TitledBorder(new EtchedBorder(), "Camper Details"));
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
		setFieldData(bean.moveFirst());
	}

	private JPanel initButtons() {
		// initializes the buttons on the screen

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

	private JPanel initFields() {
		// initializes input fields
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.add(new JLabel("ID"), "align label");
		panel.add(idField, "wrap");
		idField.setEditable(false);
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

		panel.add(new JLabel("Notes"), "align label");
		panel.add(notesField, "wrap");
		notesField.setLineWrap(true);
		notesField.setWrapStyleWord(true);

		return panel;
	}

	private Camper getFieldData() {
		// retrieves data from input fields
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

	private void setFieldData(Camper c) {
		// sets the input field data to default values
		idField.setText(String.valueOf(c.getId()));
		fnameField.setText(c.getFname());
		lnameField.setText(c.getLname());
		lunchesOrderedField.setText(String.valueOf(c.getLunchesOrdered()));
		amountPaidField.setText(String.valueOf(c.getAmountPaid()));
		notesField.setText(c.getNotes());

		try {
			datePicker.getJFormattedTextField().setText(c.getRegistrationDate().toString());
		} catch (NullPointerException n) {
			// fixing this bug was a major pain in the rear. Don't mess this
			// part up
			datePicker.getJFormattedTextField().setText(datePicker.getModel().getYear() + "-"
					+ datePicker.getModel().getMonth() + "-" + datePicker.getModel().getDay());
		}
	}

	private boolean isEmptyFieldData() {
		// checks if the field is empty
		return (fnameField.getText().trim().isEmpty() || lnameField.getText().trim().isEmpty()
				|| lunchesOrderedField.getText().trim().isEmpty() || amountPaidField.getText().trim().isEmpty());
	}

	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Camper c = getFieldData();
			switch (e.getActionCommand()) {
			case "Save":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null,
							"Cannot create an empty record./nPlease check to ensure all input boxes are filled.");
					return;
				}
				if (bean.create(c) != null)
					JOptionPane.showMessageDialog(null, "New Camper info stored successfully.");
				createButton.setText("New");
				break;
			case "New":
				c.setId(new Random().nextInt(Integer.MAX_VALUE) + 1);
				c.setFname("");
				c.setLname("");
				c.setLunchesOrdered(0);
				datePicker.getJFormattedTextField().setText("");
				c.setAmountPaid(0.0);
				c.setNotes("");
				setFieldData(c);
				createButton.setText("Save");
				datePicker.getJFormattedTextField().setText("");
				break;
			case "Update":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Error 2: Cannot create empty record");
					return;
				}
				bean.update(c, true);
				break;
			case "Delete":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot delete an empty record.");
					return;
				}
				c = bean.getCurrent();
				bean.delete(true);
				setFieldData(bean.moveNext());
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
				JOptionPane.showMessageDialog(null, "invalid command");

			}
		}
	}
}
