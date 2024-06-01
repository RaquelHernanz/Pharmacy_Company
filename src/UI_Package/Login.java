package UI_Package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.security.MessageDigest;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Client;
import PharmacyCompanyPOJOs.Doctor;
import PharmacyCompanyPOJOs.Pharmacist;
import PharmacyCompanyPOJOs.Role;
import PharmacyCompanyPOJOs.User;
import net.miginfocom.swing.MigLayout;

public class Login {

	private JFrame loginFrame;
	private JFrame registerFrame;
	private static JDBCManager jdbcmanager;
	private static AdministratorManager administratormanager;
	private static ClientManager clientmanager;
	private static PharmacistManager pharmacistmanager;
	private static DoctorManager doctormanager;
	private static MedicineManager medicinemanager;
	private static OrderManager ordermanager;
	private static UserManager usermanager;

	public static void main(String[] args) {
		Login loginObj = new Login();
		loginObj.loginRegister();
	}

	public Login() {
		loginFrame = null;
		registerFrame = null;
		jdbcmanager = new JDBCManager();
		administratormanager = new JDBCAdministratorManager(jdbcmanager);
		clientmanager = new JDBCClientManager(jdbcmanager);
		doctormanager = new JDBCDoctorManager(jdbcmanager);
		pharmacistmanager = new JDBCPharmacistManager(jdbcmanager);
		medicinemanager = new JDBCMedicineManager(jdbcmanager);
		ordermanager = new JDBCOrderManager(jdbcmanager);
		usermanager = new JPAUserManager();
	}

	public void loginRegister() {
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}
		loginFrame = new JFrame();
		loginFrame.setLayout(new MigLayout());
		loginFrame.getContentPane().setBackground(new Color(45, 58, 141));

		JPanel textPanel = new JPanel(new MigLayout("", "", ""));
		textPanel.setBackground(new Color(45, 58, 141));
		JPanel imagePanel = new JPanel(new MigLayout("", "[][][]", "[][][]"));
		imagePanel.setBackground(new Color(45, 58, 141));

		loginFrame.add(textPanel, "gaptop 50, gapbottom 50, gapleft 30, gapright 50");
		loginFrame.add(imagePanel, "gapbottom 50, gapleft 5, gapright 50");

		JLabel login = new JLabel("Welcome Back");
		login.setForeground(Color.WHITE);
		login.setFont(new Font("CALIBRI", Font.BOLD, 28));
		imagePanel.add(login, "gaptop 30, wrap");

		JLabel signIn = new JLabel("Sign-in to your Account");
		signIn.setForeground(Color.WHITE);
		signIn.setFont(new Font("CALIBRI", Font.PLAIN, 15));
		imagePanel.add(signIn, "gaptop 5, wrap");

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField usernameField = new JTextField(30);
		usernameField.setForeground(new Color(62, 76, 169));
		usernameField.setFont(new Font("CALIBRI", Font.BOLD, 12));
		usernameField.setPreferredSize(new Dimension(200, 35));

		imagePanel.add(usernameLabel, "gaptop 25, wrap");
		imagePanel.add(usernameField, "gaptop 10, wrap");

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JPasswordField passwordField = new JPasswordField(30);
		passwordField.setForeground(new Color(62, 76, 169));
		passwordField.setFont(new Font("CALIBRI", Font.BOLD, 12));
		passwordField.setPreferredSize(new Dimension(200, 35));

		imagePanel.add(passwordLabel, "gaptop 50, wrap");
		imagePanel.add(passwordField, "gaptop 10, wrap");

		JButton logButton = new JButton("Login");
		logButton.setFont(new Font("CALIBRI", Font.PLAIN, 20));
		logButton.setBackground(new Color(62, 76, 169));
		logButton.setForeground(Color.WHITE);
		imagePanel.add(logButton, "span 3, center, gaptop 75");

		logButton.addActionListener(e -> {
			try {
				String emailExample = usernameField.getText();
				String passwordExample = new String(passwordField.getPassword());
				verifyUserTypeLogin(emailExample, passwordExample);
			} catch (SQLException e1) {
				SwingUtilities.invokeLater(
						() -> JOptionPane.showMessageDialog(loginFrame, "Error: Invalid username or password."));
			} catch (Exception e1) {
				e1.printStackTrace();
				SwingUtilities.invokeLater(
						() -> JOptionPane.showMessageDialog(loginFrame, "Error: Invalid username or password."));
			}
		});

		ImageIcon databaseImage = new ImageIcon("resources/PharmaCorp_LoadingLogo.png");
		JLabel databaseLabel = new JLabel(databaseImage);
		textPanel.add(databaseLabel, "wrap 20");

		JLabel newToPfizer = new JLabel("New to Pharma Corp?");
		newToPfizer.setForeground(Color.WHITE);
		newToPfizer.setFont(new Font("CALIBRI", Font.PLAIN, 25));
		textPanel.add(newToPfizer, "wrap, center, span 3, gapbottom 10");

		JButton registerButton = new JButton("Join Today");
		registerButton.setFont(new Font("CALIBRI", Font.PLAIN, 25));
		registerButton.setBackground(new Color(62, 76, 169));
		registerButton.setForeground(Color.WHITE);
		registerButton.addActionListener(e -> {
			SwingUtilities.invokeLater(this::register);
			loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
		});

		textPanel.add(registerButton, "span 3, center, wrap");

		loginFrame.setSize(900, 600);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setVisible(true);
	}

	private void register() {
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}

		JFrame registerFrame = new JFrame();
		JPanel registerPanel = new JPanel();
		registerFrame.setLayout(new MigLayout());
		registerPanel.setLayout(new MigLayout("", "20[]20", ""));
		registerFrame.setSize(900, 600);
		registerPanel.setBackground(new Color(12, 11, 61));
		registerFrame.setLocationRelativeTo(null);
		registerFrame.getContentPane().setBackground(new Color(12, 11, 61));

		JLabel pharmaCorpLogo = new JLabel();
		ImageIcon pharmaCorpIcon = new ImageIcon("resources/swissIcon.png");
		pharmaCorpLogo.setIcon(pharmaCorpIcon);

		JLabel registrationName = new JLabel("Create your Account");
		registrationName.setFont(new Font("CALIBRI", Font.PLAIN, 28));
		registrationName.setForeground(Color.WHITE);

		JLabel name = new JLabel("Name");
		name.setForeground(Color.WHITE);
		name.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField nameText = new JTextField(20);
		nameText.setForeground(new Color(62, 76, 169));
		nameText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		nameText.setPreferredSize(new Dimension(200, 25));

		JLabel surname = new JLabel("Surname");
		surname.setForeground(Color.WHITE);
		surname.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField surnameText = new JTextField(20);
		surnameText.setForeground(new Color(62, 76, 169));
		surnameText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		surnameText.setPreferredSize(new Dimension(200, 25));

		JLabel email = new JLabel("E-mail");
		email.setForeground(Color.WHITE);
		email.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField emailText = new JTextField(20);
		emailText.setForeground(new Color(62, 76, 169));
		emailText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		emailText.setPreferredSize(new Dimension(200, 25));

		JLabel password = new JLabel("Password");
		password.setForeground(Color.WHITE);
		password.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setForeground(new Color(62, 76, 169));
		passwordText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		passwordText.setPreferredSize(new Dimension(200, 25));

		JLabel address = new JLabel("Address");
		address.setForeground(Color.WHITE);
		address.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField addressText = new JTextField(43);
		addressText.setForeground(new Color(62, 76, 169));
		addressText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		addressText.setPreferredSize(new Dimension(400, 25));

		JLabel phoneNumber = new JLabel("Phone Number");
		phoneNumber.setForeground(Color.WHITE);
		phoneNumber.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField phoneNumberText = new JTextField(20);
		phoneNumberText.setForeground(new Color(62, 76, 169));
		phoneNumberText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		phoneNumberText.setPreferredSize(new Dimension(200, 25));

		JLabel clientTypeLabel = new JLabel("Client Type");
		clientTypeLabel.setForeground(Color.WHITE);
		clientTypeLabel.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		String[] typeList = { "Administrator", "Client", "Doctor", "Pharmacist" };
		JComboBox clientType = new JComboBox(typeList);
		clientType.setMinimumSize(new Dimension(260, 1));
		clientType.setPreferredSize(
				new Dimension(clientType.getPreferredSize().width, phoneNumberText.getPreferredSize().height));
		clientType.setForeground(new Color(62, 76, 169));
		clientType.setFont(new Font("CALIBRI", Font.BOLD, 12));

		JButton registerButton = new JButton("SIGN UP");
		registerButton.setFont(new Font("CALIBRI", Font.PLAIN, 20));
		registerButton.setBackground(new Color(12, 11, 61));
		registerButton.setForeground(Color.WHITE);
		registerButton.setPreferredSize(new Dimension(400, 25));
		registerButton.addActionListener(e -> {
			try {
				String phoneNumberTextValue = phoneNumberText.getText();
				if (phoneNumberTextValue.isEmpty() || !phoneNumberTextValue.matches("\\d+")) {
					throw new IllegalArgumentException("Invalid phone number");
				}

				createUser(clientType.getSelectedItem().toString(), nameText.getText(), surnameText.getText(),
						emailText.getText(), addressText.getText(), Integer.parseInt(phoneNumberTextValue),
						new String(passwordText.getPassword()));

				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
				registerFrame.dispatchEvent(new WindowEvent(registerFrame, WindowEvent.WINDOW_CLOSING));

			} catch (Exception excep) {
				SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(registerFrame, "Error: Invalid input."));
				excep.printStackTrace();
			}
		});

		registerPanel.add(registrationName, "wrap, gaptop 25, gapbottom 25, span 2, CENTER");
		registerPanel.add(name);
		registerPanel.add(surname, "wrap");
		registerPanel.add(nameText, "gapbottom 20");
		registerPanel.add(surnameText, "wrap");
		registerPanel.add(email);
		registerPanel.add(password, "wrap");
		registerPanel.add(emailText, "gapbottom 20");
		registerPanel.add(passwordText, "wrap");
		registerPanel.add(address, "wrap");
		registerPanel.add(addressText, "CENTER, span 2, wrap, gapbottom 20");
		registerPanel.add(phoneNumber);
		registerPanel.add(clientTypeLabel, "wrap, gapbottom 20");
		registerPanel.add(phoneNumberText);
		registerPanel.add(clientType, "wrap");
		registerPanel.add(registerButton, "CENTER, gaptop 50, span 2");

		registerFrame.add(registerPanel, "CENTER, gapleft 150");
		registerFrame.setVisible(true);
	}

	private void verifyUserTypeLogin(String emailToVerify, String passwordToVerify)
			throws java.sql.SQLException, Exception {
		User userVar = usermanager.checkPassword(emailToVerify, passwordToVerify);
		System.out.println(userVar.toString());

		if (userVar != null & userVar.getRole().getName().equals("client")) {
			MainMenu_Client clientMenuObj = new MainMenu_Client();
			clientMenuObj.mainMenu_Client();
			loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
		} else if (userVar != null & userVar.getRole().getName().equals("doctor")) {
			MainMenu_Doctor doctorMenuObj = new MainMenu_Doctor();
			doctorMenuObj.mainMenu_Doctor();
			loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
		} else if (userVar != null & userVar.getRole().getName().equals("administrator")) {
			MainMenu_Admin adminMenuObj = new MainMenu_Admin();
			adminMenuObj.mainmenu();
			loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
		} else if (userVar != null & userVar.getRole().getName().equals("pharmacist")) {
			MainMenu_Pharmacies pharmaciesMenuObj = new MainMenu_Pharmacies();
			pharmaciesMenuObj.mainMenu_Pharmacy();
			loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
		}
	}

	private void createUser(String userType, String name, String surname, String email, String address,
			Integer phoneNumber, String password) throws java.sql.SQLException, Exception {
		if (userType.equals("Administrator")) {

			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] pw = md.digest();

				Role roleEx = usermanager.getRole(3);
				User generalUser = new User(email, pw, roleEx);
				usermanager.newUser(generalUser);

				Administrator adminUser = new Administrator(name, surname, phoneNumber, email);
				administratormanager.createAdministrator(adminUser);

				MainMenu_Admin adminMenuObj = new MainMenu_Admin();
				adminMenuObj.mainmenu();
				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (userType.equals("Client")) {

			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] pw = md.digest();

				Role roleEx = usermanager.getRole(1);
				User generalUser = new User(email, pw, roleEx);
				usermanager.newUser(generalUser);

				Client clientUser = new Client(name, surname, address, phoneNumber, email);
				clientmanager.createClient(clientUser);

				MainMenu_Client clientMenuObj = new MainMenu_Client();
				clientMenuObj.mainMenu_Client();
				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (userType.equals("Doctor")) {

			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] pw = md.digest();

				Role roleEx = usermanager.getRole(2);
				User generalUser = new User(email, pw, roleEx);
				usermanager.newUser(generalUser);

				Doctor doctorUser = new Doctor(name, surname, address, phoneNumber, email);
				doctormanager.createDoctor(doctorUser);

				MainMenu_Doctor doctorMenuObj = new MainMenu_Doctor();
				doctorMenuObj.mainMenu_Doctor();
				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (userType.equals("Pharmacist")) {

			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] pw = md.digest();

				Role roleEx = usermanager.getRole(4);
				User generalUser = new User(email, pw, roleEx);
				usermanager.newUser(generalUser);

				Pharmacist pharmacistUser = new Pharmacist(name, surname, phoneNumber, email);
				pharmacistmanager.createPharmacist(pharmacistUser);

				MainMenu_Pharmacies pharmaciesMenuObj = new MainMenu_Pharmacies();
				pharmaciesMenuObj.mainMenu_Pharmacy();
				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			SwingUtilities.invokeLater(
					() -> JOptionPane.showMessageDialog(registerFrame, "Error: Invalid input. CREATEUSER"));
		}
		
		jdbcmanager.disconnect();
	}


}
