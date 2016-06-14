package campRegistrationJDBC;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class HomeUI {

	private JButton newCamper = new JButton ("New Camper");
	private JButton newCamp = new JButton ("New Camp");

	public HomeUI()
	{
		setBorder(new TitledBorder
				(new EtchedBorder(),"Creative Kids Camp Database"));
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
		setFieldData(bean.moveFirst());

	}

	private Object initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));

		panel.add(newCamper);
		newCamper.addActionListener(new ButtonHandler());
		panel.add(newCamp);
		newCamp.addActionListener(new ButtonHandler());
	}

	private Object initFields() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setLayout(BorderLayout borderLayout) {
	
	//TODO make this stuff

	}

	private void setBorder(TitledBorder titledBorder) {

		//TODO this stuff also 
	}

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//Camps c = getFieldData();
			switch (e.getActionCommand()) {
			case "New Camp":

				JFrame w=new JFrame();
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
				w.getContentPane().add(new CampsUI());
				w.setSize(600, 280);
				w.setVisible(true);

				break;
			case "New Camper":
				JFrame q=new JFrame();
				q.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				q.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
				
				// TODO fix this when campersUI is done
				
				//q.getContentPane().add(new CampersUI());
				
				
				q.setSize(600, 280);
				q.setVisible(true);
				break;
			default:
				JOptionPane.showMessageDialog(null,
						"invalid command");
			}
		}

	}
