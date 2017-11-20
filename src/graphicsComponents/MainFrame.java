package graphicsComponents;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Font;
import javax.swing.JCheckBox;

import main.SpaceSystem;
import schedule.Booking;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;

public class MainFrame extends JPanel {

	private JTable calendar;
	private JComboBox monthCB;
	private JSpinner timeSpinner;
	private JSpinner durationSpinner;
	private JComboBox roomCB;
	private JTextField textField;
	private String[] rooms = {"Gym", "Library", "EN1052", "Computer Lab"};
	private SpaceSystem system;
	private JLabel userLabel;
	private JList<String> bookingsList;

	public enum Month{
		JANUARY(0, 31, 0), 
		FEBRUARY(1, 28, 4), 
		MARCH(2, 31, 4), 
		APRIL(3, 30, 6), 
		MAY(4, 31, 1), 
		JUNE(5, 30, 4), 
		JULY(6, 31, 6), 
		AUGUST(7, 31, 2), 
		SEPTEMBER(8, 30, 5), 
		OCTOBER(9, 31, 0), 
		NOVEMBER(10, 30, 3), 
		DECEMBER(11, 31, 5);
		
		private final int monthIndex;
		private final int days;
		private final int indexStartDay;
		
		Month(int monthIndex, int days, int indexStartDay) {
			this.monthIndex = monthIndex;
			this.days = days;
			this.indexStartDay = indexStartDay;
		}
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(0, 0, 535, 665);
		this.setLayout(null);
	
		userLabel = new JLabel("Welcome " + null);
		userLabel.setBounds(252, 11, 126, 23);
		this.add(userLabel);
		
		JLabel lblNewLabel = new JLabel("Scheduled Bookings");
		lblNewLabel.setBounds(10, 45, 126, 14);
		this.add(lblNewLabel);
		
		monthCB = new JComboBox(Month.values());
		monthCB.setBounds(10, 66, 126, 20);
		monthCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initializeCalendar();
			}
		});
		this.add(monthCB);
		
		calendar = new JTable(7, 7);
		calendar.setBounds(10, 97, 402, 112);
		initializeCalendar();
		this.add(calendar);
		
		Date date = new Date();
		timeSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		timeSpinner.setBounds(92, 310, 132, 20);
		JSpinner.DateEditor de = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(de);
		this.add(timeSpinner);
		
		durationSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
		durationSpinner.setBounds(92, 341, 132, 20);
		JSpinner.NumberEditor ne1 = new JSpinner.NumberEditor(durationSpinner);
		durationSpinner.setEditor(ne1);
		this.add(durationSpinner);
		
		JLabel lblNewLabel_1 = new JLabel("Request or Remove Booking");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 11, 232, 23);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Request");
		lblNewLabel_3.setBounds(128, 220, 77, 14);
		this.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Length");
		lblNewLabel_4.setBounds(10, 344, 46, 14);
		this.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Time");
		lblNewLabel_5.setBounds(10, 313, 46, 14);
		this.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(92, 251, 132, 19);
		this.add(textField);
		textField.setColumns(10);
		
		JLabel lblActivityName = new JLabel("Activity Name");
		lblActivityName.setBounds(10, 254, 97, 14);
		this.add(lblActivityName);
		
		JLabel lblNewLabel_6 = new JLabel("Room ");
		lblNewLabel_6.setBounds(10, 279, 46, 14);
		this.add(lblNewLabel_6);
		
		roomCB = new JComboBox(rooms);
		roomCB.setBounds(92, 279, 132, 20);
		this.add(roomCB);
		
		JButton btnNewButton = new JButton("Submit Request");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				submitButtonPressed();
			}
		});
		btnNewButton.setBounds(92, 378, 132, 23);
		this.add(btnNewButton);
		
		JButton btnRemoveBooking = new JButton("Remove Booking");
		btnRemoveBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				removeButtonPressed();
			}
		});
		btnRemoveBooking.setBounds(260, 309, 222, 23);
		this.add(btnRemoveBooking);
		
		JLabel lblActions = new JLabel("Actions");
		lblActions.setBounds(336, 220, 46, 14);
		this.add(lblActions);
		
		JButton myBookingsButton = new JButton("My Bookings");
		myBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myBookings();
			}
		});
		myBookingsButton.setBounds(261, 279, 221, 23);
		this.add(myBookingsButton);
		
		JButton selectDayBookingsButton = new JButton("Bookings on Selected Day");
		selectDayBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		selectDayBookingsButton.setBounds(260, 250, 221, 23);
		this.add(selectDayBookingsButton);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(393, 11, 89, 23);
		add(btnLogout);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		bookingsList = new JList<>(model);
		bookingsList.setBounds(10, 449, 393, 182);
		add(bookingsList);
		
		JLabel lblBookings = new JLabel("Bookings");
		lblBookings.setBounds(10, 424, 112, 14);
		add(lblBookings);
		
	}
	
	public void setUserLabel(String userLabel) {
		this.userLabel.setText(userLabel);
	}
	
	public void submitButtonPressed() {
		boolean selected = false;
		String activityName = textField.getText();
		if(!activityName.isEmpty()) {
			for(int i = 0; i < calendar.getRowCount(); i++) {
				if(calendar.isRowSelected(i)) {
					selected = true;
				}
			}
			
			if(selected == true) {
				String roomName = (String) roomCB.getSelectedItem();
				Date spinnerDate = (Date) timeSpinner.getValue();
				Calendar date = Calendar.getInstance();
				date.setTime(spinnerDate);
				int day = ((int) calendar.getValueAt(calendar.getSelectedRow(), calendar.getSelectedColumn()));
				int monthIndex = ((Month) monthCB.getSelectedItem()).monthIndex;
				date.set(Calendar.DAY_OF_MONTH, day);
				date.set(Calendar.MONTH, monthIndex);
				int duration = (Integer) durationSpinner.getValue();
				
				system.addBooking(new Booking(system.getUserLoggedIn(), system.searchRoom(roomName), duration, date));
				
			}
			else {
				JOptionPane.showMessageDialog(this, "No date selected in table");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "No activity name specified for booking");
		}
	
	}
	
	public void removeButtonPressed() {
		
	}
	
	public void myBookings() {
		ArrayList<Booking> bookings = system.getBookings().get(system.getUserLoggedIn());
		for(int i = 0; i < bookings.size(); i++) {
			((DefaultListModel<String>) bookingsList.getModel()).addElement(bookings.get(i).toString());
		}
	}
	
	public void bookingsOnSelectedDay() {
		
	}
	
	public void addSystem(SpaceSystem system) {
		this.system = system;
	}
	
	public void initializeCalendar() {
		String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for(int i = 0; i < calendar.getColumnCount(); i++) {
			calendar.setValueAt(dayOfWeek[i], 0, i);
		}
		
		Month selected = (Month) monthCB.getSelectedItem();
		int dayCount = 0;
		for(int i = 1; i < 7; i++) {
			for(int j = 0; j < 7 && dayCount < selected.days; j++) {
				if(j < selected.indexStartDay && i == 1) {
					calendar.setValueAt(null, 1, j);
				}
				else if(i == 1) {
					dayCount++;
					calendar.setValueAt(dayCount ,1, j);
				}
				else {
					dayCount++;
					calendar.setValueAt(dayCount ,i, j);
				}
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
		SpaceSystem system = new SpaceSystem();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage loginFrame = new LoginPage(system);
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
