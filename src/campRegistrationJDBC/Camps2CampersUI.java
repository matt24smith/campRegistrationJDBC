package campRegistrationJDBC;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Camps2CampersUI {

	private JComboBox selectCamper = new JComboBox();
	private JComboBox selectCamp = new JComboBox();
	
	private JButton refresh = new JButton("Refresh");
	private JButton registerCamper = new JButton("Register");
	
	private JTextField registrationDiscount;
	
	private Camps2CampersBean bean = new Camps2CampersBean();
	
	public Camps2CampersUI(){
		
	}
}
