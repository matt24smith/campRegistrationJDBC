package campRegistrationJDBC;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Camps2CampersUI extends JPanel {

	private Camps2CampersBean bean = new Camps2CampersBean();
	
	private JComboBox selectCamper = new JComboBox(bean.getCamperNames());
	private JComboBox selectCamp = new JComboBox(bean.getCampNames());
	
	private JButton refresh = new JButton("Refresh");
	private JButton registerCamper = new JButton("Register");
	
	private JTextField registrationDiscount;
	
	
	
	public Camps2CampersUI(){
		setBorder(new TitledBorder(new EtchedBorder(), "Register Campers"));
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
		//setFieldData(bean.moveFirst());
	}
	
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		
		panel.add(selectCamper);
		selectCamper.addActionListener(new ButtonHandler());
		panel.add(selectCamp);
		selectCamp.addActionListener(new ButtonHandler());
		panel.add(refresh);
		refresh.addActionListener(new ButtonHandler());
		panel.add(registerCamper);
		registerCamper.addActionListener(new ButtonHandler());
		
		return panel;
	}
	
	private JPanel initFields() {
		
	}
}
