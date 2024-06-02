package UI_Package;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import PharmacyCompanyInterfaces.AdministratorManager;
import PharmacyCompanyInterfaces.ClientManager;
import PharmacyCompanyInterfaces.DoctorManager;
import PharmacyCompanyInterfaces.MedicineManager;
import PharmacyCompanyInterfaces.OrderManager;
import PharmacyCompanyInterfaces.PharmacistManager;
import PharmacyCompanyInterfaces.UserManager;
import PharmacyCompanyJDBC.JDBCAdministratorManager;
import PharmacyCompanyJDBC.JDBCClientManager;
import PharmacyCompanyJDBC.JDBCDoctorManager;
import PharmacyCompanyJDBC.JDBCManager;
import PharmacyCompanyJDBC.JDBCMedicineManager;
import PharmacyCompanyJDBC.JDBCOrderManager;
import PharmacyCompanyJDBC.JDBCPharmacistManager;
import PharmacyCompanyJPA.JPAUserManager;
import PharmacyCompanyPOJOs.Client;
import PharmacyCompanyPOJOs.Doctor;
import PharmacyCompanyPOJOs.Medicine;
import PharmacyCompanyPOJOs.Order;
import PharmacyCompanyPOJOs.Pharmacist;
import PharmacyCompanyPOJOs.User;
import net.miginfocom.swing.MigLayout;

public class MainMenu_Admin {
	private JFrame menu;
	private JPanel list;
	private JPanel icons;
	private JFrame generalMenuFrameLists;
	private JTable table;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableMode2;
	private JPanel panel4;

	private String[] clientTitles;
	private String[] doctorTitles;
	private String[] pharmacyTitles;
	private String[] medicineTitles;
	private String [] ordersTitles;

	private List<Client> clientsListArray;
	private List<Doctor> doctorsListArray;
	private List<Pharmacist> pharmaciesListArray;
	private List<Medicine> medicinesListArray;
	private List <Order> ordersListArray;

	private JButton removeClientButton;
	private JButton removeDoctorButton;
	private JButton removePharmacyButton;
	private JButton saveButton;

	private static JDBCManager jdbcmanager;
	private static AdministratorManager administratormanager;
	private static ClientManager clientmanager;
	private static PharmacistManager pharmacistmanager;
	private static DoctorManager doctormanager;
	private static MedicineManager medicinemanager;
	private static OrderManager ordermanager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserManager usermanager;

	public MainMenu_Admin() {
		menu = null;
		list = null;
		icons = null;
		clientsListArray = null;
		doctorsListArray = null;
		medicinesListArray = null;
		pharmaciesListArray = null;
		jdbcmanager = new JDBCManager();
		administratormanager = new JDBCAdministratorManager(jdbcmanager);
		clientmanager = new JDBCClientManager(jdbcmanager);
		doctormanager = new JDBCDoctorManager(jdbcmanager);
		pharmacistmanager = new JDBCPharmacistManager(jdbcmanager);
		medicinemanager = new JDBCMedicineManager(jdbcmanager);
		ordermanager = new JDBCOrderManager(jdbcmanager);
		usermanager = new JPAUserManager();
	}

	public static void main(String[] args) {
		MainMenu_Admin menuObj = new MainMenu_Admin();
		menuObj.mainmenu();
		jdbcmanager.disconnect();
	}

	public void mainmenu() {
		menu = new JFrame();
		list = new JPanel();
		icons = new JPanel();

		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}

		menu.setLayout(new GridBagLayout());
		menu.setSize(1300, 750);
		menu.getContentPane().setBackground(new Color(12, 11, 61));
		menu.setLocationRelativeTo(null);

		GridBagConstraints constraints = new GridBagConstraints();
		list.setLayout(new MigLayout("", "[]", "[][][][][][][]"));
		list.setBackground(new Color(12, 11, 61));
		icons.setLayout(new MigLayout("", "20[]20[]20[]20", "[]20[]"));

		ImageIcon pharmaLogo = new ImageIcon("resources/PharmaCorp.png");
		JLabel label = new JLabel();
		label.setIcon(pharmaLogo);

		JLabel welcomeUsers = new JLabel("Main Menu");
		welcomeUsers.setFont(new Font("Calibri", Font.BOLD, 30));
		welcomeUsers.setForeground(Color.WHITE);
		icons.add(welcomeUsers, "dock north, gapbottom 30");

		JButton clients = new JButton();
		ImageIcon clientsIm = new ImageIcon("resources/Clients.png");
		clients.setIcon(clientsIm);
		clients.addActionListener(e -> {
			try {
				clients();
			} catch (Exception e1) {
				SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(menu, "Error: No clients available."));
			}
		});
		icons.add(clients);

		JButton doctors = new JButton();
		ImageIcon doctorsIm = new ImageIcon("resources/doctors.png");
		doctors.setIcon(doctorsIm);
		doctors.addActionListener(e -> {
			try {
				doctors();
			} catch (Exception e1) {
				SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(menu, "Error: No doctors available."));
			}
		});
		icons.add(doctors);

		JButton pharmacies = new JButton();
		ImageIcon pharmacyIcon = new ImageIcon("resources/Pharmacies.png");
		pharmacies.setIcon(pharmacyIcon);
		pharmacies.addActionListener(e -> {
			try {
				pharmacies();
			} catch (Exception e1) {
				SwingUtilities
						.invokeLater(() -> JOptionPane.showMessageDialog(menu, "Error: No pharmacists available."));
			}
		});
		icons.add(pharmacies, "wrap");

		ImageIcon mountain = new ImageIcon("resources/MountainNew.png");
		JLabel mountainLabel = new JLabel();
		mountainLabel.setIcon(mountain);
		icons.add(mountainLabel, "span 3, align right");

		JButton productsButton = new JButton("Products");
		productsButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		productsButton.setForeground(Color.WHITE);
		productsButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		productsButton.setBackground(new Color(19, 18, 79));
		productsButton.setPreferredSize(new Dimension(400, 25));
		productsButton.addActionListener(e -> {
			try {
				products();
			} catch (Exception e1) {
				SwingUtilities
						.invokeLater(() -> JOptionPane.showMessageDialog(menu, "Error: No products available."));
			}
		});

		JButton help = new JButton("Help");
		help.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		help.setForeground(Color.WHITE);
		help.setFont(new Font("Calibri", Font.PLAIN, 18));
		help.setBackground(new Color(19, 18, 79));
		help.setPreferredSize(new Dimension(400, 25));
		help.addActionListener(e -> {
			userManual();
		});
		
		JButton signout = new JButton("Sign out");
		signout.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		signout.setForeground(Color.WHITE);
		signout.setFont(new Font("Calibri", Font.PLAIN, 18));
		signout.setBackground(new Color(19, 18, 79));
		signout.setPreferredSize(new Dimension(400, 25));
		signout.addActionListener(e -> {
			menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
			Login loginMenu = new Login();
			loginMenu.loginRegister();
		});
		
		JButton orderssButton = new JButton("Shipments of Restock");
		orderssButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		orderssButton.setForeground(Color.WHITE);
		orderssButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		orderssButton.setBackground(new Color(19, 18, 79));
		orderssButton.setPreferredSize(new Dimension(400, 25));
		orderssButton.addActionListener(e -> {
			try {
				orders();
			} catch (Exception e1) {
				SwingUtilities
						.invokeLater(() -> JOptionPane.showMessageDialog(menu, "Error: No shipments avaible available."));
			}
		});

		JLabel profile = new JLabel();
		ImageIcon profileIcon = new ImageIcon("resources/profile.png");
		profile.setIcon(profileIcon);

		JLabel nameLabelEx = new JLabel("Welcome, Admin");
		nameLabelEx.setFont(new Font("Calibri", Font.BOLD, 20));
		nameLabelEx.setForeground(Color.WHITE);
		
		list.add(label, "align left, wrap 50");
		list.add(productsButton, "CENTER, wrap 20");
		list.add(orderssButton,"CENTER, wrap 30");
		list.add(help, "CENTER, wrap 50");
		list.add(signout,"CENTER, wrap 70");
		list.add(profile, "CENTER, gapbottom 10, wrap");
		list.add(nameLabelEx, "CENTER, gapbottom 10");

		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 0;
		menu.add(list);

		icons.setBackground(new Color(12, 11, 61));
		constraints.gridx = 1;
		constraints.gridwidth = 0;
		constraints.gridheight = 1;
		menu.add(icons);
		menu.setVisible(true);
	}

	// CLIENT INFORMATION
	public void clients() throws Exception {
		generalMenuFrameLists = new JFrame();

		generalMenuFrameLists.setLayout(new MigLayout("fill", "30[]30[]30", "30[]30"));
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}
		generalMenuFrameLists.setSize(1300, 750);
		generalMenuFrameLists.getContentPane().setBackground(new Color(12, 11, 61));
		generalMenuFrameLists.setLocationRelativeTo(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new MigLayout("fill"));
		menuPanel.setBackground(Color.RED);

		ImageIcon darkLogo = new ImageIcon("resources/DarkPharmaCorpLogo.png");
		JLabel darkLabel = new JLabel(darkLogo);

		ImageIcon welcomeLogo = new ImageIcon("resources/welcomeAdmin.png");
		JLabel welcomeLabel = new JLabel(welcomeLogo);

		menuPanel.setLayout(new MigLayout("", "[]", "[][][][][][][]"));
		menuPanel.setBackground(new Color(12, 11, 61));

		JButton closeButton = new JButton("Close");
		closeButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		closeButton.setBackground(new Color(19, 18, 79));
		closeButton.setPreferredSize(new Dimension(400, 25));
		closeButton.addActionListener(e -> {
			generalMenuFrameLists.dispatchEvent(new WindowEvent(generalMenuFrameLists, WindowEvent.WINDOW_CLOSING));
		});

		JPanel panel2 = new JPanel();
		panel2.setLayout(new MigLayout("fill"));
		panel2.setBackground(new Color(12, 11, 61));

		panel4 = new JPanel();
		panel4.setLayout(new MigLayout("fill"));
		panel4.setBackground(new Color(12, 11, 61));
		panel4.add(clientsList(), "grow");

		JPanel panel3 = new JPanel();
		panel3.setLayout(new MigLayout("fill"));
		panel3.setBackground(new Color(12, 11, 61));

		saveButton = new JButton("Save Changes");
		saveButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		saveButton.setForeground(Color.WHITE);
		saveButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		saveButton.setBackground(new Color(19, 18, 79));
		saveButton.setPreferredSize(new Dimension(400, 25));
		saveButton.addActionListener(e -> {
			// TODO
		});

		removeClientButton = new JButton("Remove Client");
		removeClientButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		removeClientButton.setForeground(Color.WHITE);
		removeClientButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		removeClientButton.setBackground(new Color(19, 18, 79));
		removeClientButton.setPreferredSize(new Dimension(400, 25));
		removeClientButton.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (row != -1) 
			{
				tableModel.removeRow(table.getSelectedRow());
				Client client = clientsListArray.get(row);
				clientsListArray.remove(row);
				User client_U = usermanager.getUser(client.getEmail());
				try {
					clientmanager.deleteClientbyId(client.getId());
					usermanager.deleteUser(client_U);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error: Client not found.");
				}
				JOptionPane.showMessageDialog(null, "Client has been removed from the table.");
			}
		}
		);

		menuPanel.add(welcomeLabel, "wrap, gapbottom 20");
		menuPanel.add(darkLabel, "CENTER, wrap, gapbottom 40");
		menuPanel.add(saveButton, "CENTER, wrap, gapbottom 10");
		menuPanel.add(removeClientButton, "CENTER, wrap, gapbottom 10");
		menuPanel.add(closeButton, "CENTER, wrap, gapbottom 10");

		panel2.add(panel3, "wrap, grow");
		panel2.add(panel4, "grow");

		generalMenuFrameLists.add(menuPanel, "grow");
		generalMenuFrameLists.add(panel2, "grow");
		generalMenuFrameLists.setVisible(true);
	}


	public JScrollPane clientsList() throws Exception {
		clientTitles = new String[] { "Name", "Surname", "E-Mail", "Address", "Phone Number" };
		clientsListArray = getAllClients();

		String[][] clients2DArray = new String[clientsListArray.size()][5];

		for (int i = 0; i < clientsListArray.size(); i++) {
			Client client = clientsListArray.get(i);
			clients2DArray[i][0] = client.getName();
			clients2DArray[i][1] = client.getSurname();
			clients2DArray[i][2] = client.getEmail();
			clients2DArray[i][3] = client.getAddress();
			clients2DArray[i][4] = client.getPhone_number().toString();
		}

		tableModel = new DefaultTableModel(clients2DArray, clientTitles);
		table = new JTable(tableModel);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setBackground(new Color(19, 18, 79));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN, 12));
		table.setBackground(new Color(19, 18, 79));
		table.setForeground(Color.WHITE);
		table.setFont(new Font("CALIBRI", Font.PLAIN, 12));
		return new JScrollPane(table);
	}

	private static List<Client> getAllClients() throws Exception {
		return clientmanager.getListOfClients();
	}

	// DOCTOR INFORMATION
	public void doctors() throws Exception {
		generalMenuFrameLists = new JFrame();

		generalMenuFrameLists.setLayout(new MigLayout("fill", "30[]30[]30", "30[]30"));
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}
		generalMenuFrameLists.setSize(1300, 750);
		generalMenuFrameLists.getContentPane().setBackground(new Color(12, 11, 61));
		generalMenuFrameLists.setLocationRelativeTo(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new MigLayout("fill"));
		menuPanel.setBackground(Color.RED);

		ImageIcon darkLogo = new ImageIcon("resources/DarkPharmaCorpLogo.png");
		JLabel darkLabel = new JLabel(darkLogo);

		ImageIcon welcomeLogo = new ImageIcon("resources/welcomeAdmin.png");
		JLabel welcomeLabel = new JLabel(welcomeLogo);

		menuPanel.setLayout(new MigLayout("", "[]", "[][][][][][][]"));
		menuPanel.setBackground(new Color(12, 11, 61));

		JButton closeButton = new JButton("Close");
		closeButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		closeButton.setBackground(new Color(19, 18, 79));
		closeButton.setPreferredSize(new Dimension(400, 25));
		closeButton.addActionListener(e -> {
			generalMenuFrameLists.dispatchEvent(new WindowEvent(generalMenuFrameLists, WindowEvent.WINDOW_CLOSING));
		});

		JPanel panel2 = new JPanel();
		panel2.setLayout(new MigLayout("fill"));
		panel2.setBackground(new Color(12, 11, 61));

		panel4 = new JPanel();
		panel4.setLayout(new MigLayout("fill"));
		panel4.setBackground(new Color(12, 11, 61));
		panel4.add(doctorsList(), "grow");

		JPanel panel3 = new JPanel();
		panel3.setLayout(new MigLayout("fill"));
		panel3.setBackground(new Color(12, 11, 61));

		saveButton = new JButton("Save Changes");
		saveButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		saveButton.setForeground(Color.WHITE);
		saveButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		saveButton.setBackground(new Color(19, 18, 79));
		saveButton.setPreferredSize(new Dimension(400, 25));
		saveButton.addActionListener(e -> {
			// TODO
		});

		removeDoctorButton = new JButton("Remove Doctor");
		removeDoctorButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		removeDoctorButton.setForeground(Color.WHITE);
		removeDoctorButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		removeDoctorButton.setBackground(new Color(19, 18, 79));
		removeDoctorButton.setPreferredSize(new Dimension(400, 25));
		removeDoctorButton.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (row != -1) {
				tableModel.removeRow(table.getSelectedRow());
				Doctor doctor = doctorsListArray.get(row);
				doctorsListArray.remove(row);
				try {
					doctormanager.deleteDoctorbyId(doctor.getId());
					User doctor_U = usermanager.getUser(doctor.getEmail());
					usermanager.deleteUser(doctor_U);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error: Doctor not found.");
				}
				JOptionPane.showMessageDialog(null, "Doctor has been removed from the table.");
			}
		});

		menuPanel.add(welcomeLabel, "wrap, gapbottom 20");
		menuPanel.add(darkLabel, "CENTER, wrap, gapbottom 40");
		menuPanel.add(saveButton, "CENTER, wrap, gapbottom 10");
		menuPanel.add(removeDoctorButton, "CENTER, wrap, gapbottom 10");
		menuPanel.add(closeButton, "CENTER, wrap, gapbottom 10");

		panel2.add(panel3, "wrap, grow");
		panel2.add(panel4, "grow");

		generalMenuFrameLists.add(menuPanel, "grow");
		generalMenuFrameLists.add(panel2, "grow");
		generalMenuFrameLists.setVisible(true);
	}

	public JScrollPane doctorsList() throws Exception {
		doctorTitles = new String[] { "Name", "Surname", "E-Mail", "Address", "Phone Number" };
		doctorsListArray = getAllDoctors();

		String[][] doctors2DArray = new String[doctorsListArray.size()][5];

		for (int i = 0; i < doctorsListArray.size(); i++) {
			Doctor doctor = doctorsListArray.get(i);
			doctors2DArray[i][0] = doctor.getName();
			doctors2DArray[i][1] = doctor.getSurname();
			doctors2DArray[i][2] = doctor.getEmail();
			doctors2DArray[i][3] = doctor.getAddress();
			doctors2DArray[i][4] = doctor.getPhone_number().toString();
		}

		tableModel = new DefaultTableModel(doctors2DArray, doctorTitles);
		table = new JTable(tableModel);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setBackground(new Color(19, 18, 79));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN, 12));
		table.setBackground(new Color(19, 18, 79));
		table.setForeground(Color.WHITE);
		table.setFont(new Font("CALIBRI", Font.PLAIN, 12));
		return new JScrollPane(table);
	}

	private static List<Doctor> getAllDoctors() throws Exception {
		return doctormanager.getListOfDoctors();
	}

	// PHARMACY INFORMATION
	public void pharmacies() throws Exception {
		generalMenuFrameLists = new JFrame();

		generalMenuFrameLists.setLayout(new MigLayout("fill", "30[]30[]30", "30[]30"));
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}
		generalMenuFrameLists.setSize(1300, 750);
		generalMenuFrameLists.getContentPane().setBackground(new Color(12, 11, 61));
		generalMenuFrameLists.setLocationRelativeTo(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new MigLayout("fill"));
		menuPanel.setBackground(Color.RED);

		ImageIcon darkLogo = new ImageIcon("resources/DarkPharmaCorpLogo.png");
		JLabel darkLabel = new JLabel(darkLogo);

		ImageIcon welcomeLogo = new ImageIcon("resources/welcomeAdmin.png");
		JLabel welcomeLabel = new JLabel(welcomeLogo);

		menuPanel.setLayout(new MigLayout("", "[]", "[][][][][][][]"));
		menuPanel.setBackground(new Color(12, 11, 61));

		JButton closeButton = new JButton("Close");
		closeButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		closeButton.setBackground(new Color(19, 18, 79));
		closeButton.setPreferredSize(new Dimension(400, 25));
		closeButton.addActionListener(e -> {
			generalMenuFrameLists.dispatchEvent(new WindowEvent(generalMenuFrameLists, WindowEvent.WINDOW_CLOSING));
		});

		JPanel panel2 = new JPanel();
		panel2.setLayout(new MigLayout("fill"));
		panel2.setBackground(new Color(12, 11, 61));

		panel4 = new JPanel();
		panel4.setLayout(new MigLayout("fill"));
		panel4.setBackground(new Color(12, 11, 61));
		panel4.add(pharmaciesList(), "grow");

		JPanel panel3 = new JPanel();
		panel3.setLayout(new MigLayout("fill"));
		panel3.setBackground(new Color(12, 11, 61));

		saveButton = new JButton("Save Changes");
		saveButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		saveButton.setForeground(Color.WHITE);
		saveButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		saveButton.setBackground(new Color(19, 18, 79));
		saveButton.setPreferredSize(new Dimension(400, 25));
		saveButton.addActionListener(e -> {
			// TODO
		});

		removePharmacyButton = new JButton("Remove Pharmacy");
		removePharmacyButton
				.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		removePharmacyButton.setForeground(Color.WHITE);
		removePharmacyButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		removePharmacyButton.setBackground(new Color(19, 18, 79));
		removePharmacyButton.setPreferredSize(new Dimension(400, 25));
		removePharmacyButton.addActionListener(e -> {
			try {
				int row = table.getSelectedRow();
				if (row != -1) {
					tableModel.removeRow(table.getSelectedRow());
					Pharmacist pharmacist = pharmaciesListArray.get(row);
					pharmaciesListArray.remove(row);
					pharmacistmanager.deletePharmacistbyId(pharmacist.getId());
					User pharmacist_U = usermanager.getUser(pharmacist.getEmail());
					usermanager.deleteUser(pharmacist_U);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Error: Pharmacist not found.");
			}
			JOptionPane.showMessageDialog(null, "Pharmacist has been removed from the table.");
		});

		menuPanel.add(welcomeLabel, "wrap, gapbottom 20");
		menuPanel.add(darkLabel, "CENTER, wrap, gapbottom 40");
		menuPanel.add(saveButton, "CENTER, wrap, gapbottom 10");
		menuPanel.add(removePharmacyButton, "CENTER, wrap, gapbottom 10");
		menuPanel.add(closeButton, "CENTER, wrap, gapbottom 10");

		panel2.add(panel3, "wrap, grow");
		panel2.add(panel4, "grow");

		generalMenuFrameLists.add(menuPanel, "grow");
		generalMenuFrameLists.add(panel2, "grow");
		generalMenuFrameLists.setVisible(true);
	}

	public JScrollPane pharmaciesList() throws Exception {
		pharmacyTitles = new String[] { "Name", "Surname", "E-Mail", "Phone NUmber" };
		pharmaciesListArray = getAllPharmacies();

		String[][] pharmacies2DArray = new String[pharmaciesListArray.size()][5];

		for (int i = 0; i < pharmaciesListArray.size(); i++) {
			Pharmacist pharmacist = pharmaciesListArray.get(i);
			pharmacies2DArray[i][0] = pharmacist.getName();
			pharmacies2DArray[i][1] = pharmacist.getSurname();
			pharmacies2DArray[i][2] = pharmacist.getEmail();
			pharmacies2DArray[i][3] = pharmacist.getPhone_number().toString();
		}

		tableModel = new DefaultTableModel(pharmacies2DArray, pharmacyTitles);
		table = new JTable(tableModel);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setBackground(new Color(19, 18, 79));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN, 12));
		table.setBackground(new Color(19, 18, 79));
		table.setForeground(Color.WHITE);
		table.setFont(new Font("CALIBRI", Font.PLAIN, 12));
		return new JScrollPane(table);
	}

	private static List<Pharmacist> getAllPharmacies() throws Exception {
		return pharmacistmanager.getListOfPharmacist();
	}

	public void userManual() {
		try {
			File file = new File("resources/User Manual.rtf");
			if (!Desktop.isDesktopSupported()) {
				System.out.println("not supported");
				return;
			}
			Desktop desktop = Desktop.getDesktop();
			if (file.exists())
				desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void products() throws Exception {
		generalMenuFrameLists = new JFrame();

		generalMenuFrameLists.setLayout(new MigLayout("fill", "30[]30[]30", "30[]30"));
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}
		generalMenuFrameLists.setSize(1300, 750);
		generalMenuFrameLists.getContentPane().setBackground(new Color(12, 11, 61));
		generalMenuFrameLists.setLocationRelativeTo(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new MigLayout("fill"));
		menuPanel.setBackground(Color.RED);

		ImageIcon darkLogo = new ImageIcon("resources/DarkPharmaCorpLogo.png");
		JLabel darkLabel = new JLabel(darkLogo);

		ImageIcon welcomeLogo = new ImageIcon("resources/welcomeAdmin.png");
		JLabel welcomeLabel = new JLabel(welcomeLogo);

		menuPanel.setLayout(new MigLayout("", "[]", "[][][][][][][]"));
		menuPanel.setBackground(new Color(12, 11, 61));

		JButton closeButton = new JButton("Close");
		closeButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		closeButton.setBackground(new Color(19, 18, 79));
		closeButton.setPreferredSize(new Dimension(400, 25));
		closeButton.addActionListener(e -> {
			generalMenuFrameLists.dispatchEvent(new WindowEvent(generalMenuFrameLists, WindowEvent.WINDOW_CLOSING));
		});

		JPanel panel2 = new JPanel();
		panel2.setLayout(new MigLayout("fill"));
		panel2.setBackground(new Color(12, 11, 61));

		panel4 = new JPanel();
		panel4.setLayout(new MigLayout("fill"));
		panel4.setBackground(new Color(12, 11, 61));
		panel4.add(medicinesList(), "grow");

		JPanel panel3 = new JPanel();
		panel3.setLayout(new MigLayout("fill"));
		panel3.setBackground(new Color(12, 11, 61));

		menuPanel.add(welcomeLabel, "wrap, gapbottom 20");
		menuPanel.add(darkLabel, "CENTER, wrap, gapbottom 65");
		menuPanel.add(closeButton, "CENTER, wrap, gapbottom 10");

		panel2.add(panel3, "wrap, grow");
		panel2.add(panel4, "grow");

		generalMenuFrameLists.add(menuPanel, "grow");
		generalMenuFrameLists.add(panel2, "grow");
		generalMenuFrameLists.setVisible(true);
	}

	public JScrollPane medicinesList() throws Exception {
		medicineTitles = new String[] { "Name", "Prescription Required", "Price", "Expiration Date",
				"Available Stock" };
		medicinesListArray = getAllMedicines();

		String[][] medicines2DArray = new String[medicinesListArray.size()][5];

		for (int i = 0; i < medicinesListArray.size(); i++) {
			Medicine medicine = medicinesListArray.get(i);
			medicines2DArray[i][0] = medicine.getName();
			medicines2DArray[i][1] = String.valueOf(medicine.getPrescribed());
			medicines2DArray[i][2] = medicine.getPrice().toString();
			medicines2DArray[i][3] = medicine.getExpirations().toString();
			medicines2DArray[i][4] = medicine.getStock().toString();
		}

		tableModel = new DefaultTableModel(medicines2DArray, medicineTitles);
		table = new JTable(tableModel);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setBackground(new Color(19, 18, 79));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN, 12));
		table.setBackground(new Color(19, 18, 79));
		table.setForeground(Color.WHITE);
		table.setFont(new Font("CALIBRI", Font.PLAIN, 12));
		return new JScrollPane(table);
	}

	private static List<Medicine> getAllMedicines() throws Exception {
		return medicinemanager.getListofMedicines();
	}
	
	
	public JScrollPane OrdersList() throws Exception {
		ordersTitles = new String[] { "Code", "Quantity", "Total Price", "Pharmacist","Medicine"};
		ordersListArray = ordermanager.getListOfOrders();

		String[][] orders2DArray = new String[ordersListArray.size()][5];

		for (int i = 0; i < ordersListArray.size(); i++) {
			Order order = ordersListArray.get(i);
			orders2DArray[i][0] = String.valueOf(order.getCode());
			orders2DArray[i][1] = String.valueOf(order.getQuantity());
			orders2DArray[i][2] = String.valueOf(order.getTotalprice());
			orders2DArray[i][3] = order.getPharmacist().getEmail();
			orders2DArray[i][4] = order.getMedicine().getName();
		}

		tableMode2 = new DefaultTableModel(orders2DArray, ordersTitles);
		table = new JTable(tableMode2);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setBackground(new Color(19, 18, 79));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN, 12));
		table.setBackground(new Color(19, 18, 79));
		table.setForeground(Color.WHITE);
		table.setFont(new Font("CALIBRI", Font.PLAIN, 12));
		return new JScrollPane(table);
	}
	
	public void orders() throws Exception {
		generalMenuFrameLists = new JFrame();

		generalMenuFrameLists.setLayout(new MigLayout("fill", "30[]30[]30", "30[]30"));
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}
		generalMenuFrameLists.setSize(1300, 750);
		generalMenuFrameLists.getContentPane().setBackground(new Color(12, 11, 61));
		generalMenuFrameLists.setLocationRelativeTo(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new MigLayout("fill"));
		menuPanel.setBackground(Color.RED);

		ImageIcon darkLogo = new ImageIcon("resources/DarkPharmaCorpLogo.png");
		JLabel darkLabel = new JLabel(darkLogo);

		ImageIcon welcomeLogo = new ImageIcon("resources/welcomeAdmin.png");
		JLabel welcomeLabel = new JLabel(welcomeLogo);

		menuPanel.setLayout(new MigLayout("", "[]", "[][][][][][][]"));
		menuPanel.setBackground(new Color(12, 11, 61));

		JButton closeButton = new JButton("Close");
		closeButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		closeButton.setBackground(new Color(19, 18, 79));
		closeButton.setPreferredSize(new Dimension(400, 25));
		closeButton.addActionListener(e -> {
			generalMenuFrameLists.dispatchEvent(new WindowEvent(generalMenuFrameLists, WindowEvent.WINDOW_CLOSING));
		});

		JPanel panel2 = new JPanel();
		panel2.setLayout(new MigLayout("fill"));
		panel2.setBackground(new Color(12, 11, 61));

		panel4 = new JPanel();
		panel4.setLayout(new MigLayout("fill"));
		panel4.setBackground(new Color(12, 11, 61));
		panel4.add(OrdersList(), "grow");

		JPanel panel3 = new JPanel();
		panel3.setLayout(new MigLayout("fill"));
		panel3.setBackground(new Color(12, 11, 61));

		menuPanel.add(welcomeLabel, "wrap, gapbottom 20");
		menuPanel.add(darkLabel, "CENTER, wrap, gapbottom 65");
		menuPanel.add(closeButton, "CENTER, wrap, gapbottom 10");

		panel2.add(panel3, "wrap, grow");
		panel2.add(panel4, "grow");

		generalMenuFrameLists.add(menuPanel, "grow");
		generalMenuFrameLists.add(panel2, "grow");
		generalMenuFrameLists.setVisible(true);
	}
	
	

}
