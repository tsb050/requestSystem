package graphicsComponents;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.SpaceSystem;

public class LoginPage extends JFrame {
	
	private JTextField userNameField;
	private JTextField passwordField;
	private JButton btnLogin;
	private SpaceSystem system;
	/**
	 * @wbp.parser.entryPoint
	 */
	public LoginPage(SpaceSystem system) {
		this.system = system;
		initialize();
	}
	
	public void initialize() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Login to Space System");
		this.setLayout(null);
		
		userNameField = new JTextField();
		userNameField.setBounds(117, 71, 161, 20);
		this.add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(117, 127, 161, 20);
		this.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(170, 46, 108, 14);
		this.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(170, 102, 108, 14);
		this.add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new LoginListener());
		btnLogin.setBounds(150, 158, 89, 23);
		this.add(btnLogin);
	}
	
	public void drawMainFrame(MainFrame window) {
		this.getContentPane().removeAll();
		this.revalidate();
		this.setSize(522, 695);
		this.add(window);
		this.repaint();
	}
	
	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLogin) {
				String userName = userNameField.getText();
				String password = passwordField.getText();
				System.out.println(userName);
				if(system.validate(userName, password)) {
					MainFrame window = new MainFrame();
					window.addSystem(system);
					window.setUserLabel("Welcome " + system.getUserLoggedIn().getName() + "!");
					system.addGui(window);
					LoginPage.this.drawMainFrame(window);
				}
				else {
					JOptionPane.showMessageDialog(LoginPage.this, "Wrong username or password entered");
				}
			}
		}
	}
}
