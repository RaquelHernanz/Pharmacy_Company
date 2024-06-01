package UI_Package;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Client;
import PharmacyCompanyPOJOs.Doctor;
import PharmacyCompanyPOJOs.Medicine;
import PharmacyCompanyPOJOs.Order;
import PharmacyCompanyPOJOs.Pharmacist;
import net.miginfocom.swing.MigLayout;

public class MainMenu_Pharmacies {

	private JFrame pharmacyMenu;
	private JTable table;
	private JPanel panel4;
	private DefaultTableModel tableModel;
	private JButton removeButtonProduct;
	private JButton purchaseButton;
	private int chosenStock;

	private String[] clientTitles;
	private String[] doctorTitles;
	private String[] medicineTitles;

	private List<Client> clientsListArray;
	private List<Doctor> doctorsListArray;
	private List<Medicine> medicinesListArray;
	private JComboBox<String> availableProductsClients;

	private static JDBCManager jdbcmanager;
	private static ClientManager clientmanager;
	private static PharmacistManager pharmacistmanager;
	private static DoctorManager doctormanager;
	private static MedicineManager medicinemanager;
	private static OrderManager ordermanager;
	private static AdministratorManager administratormanager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserManager usermanager;

	public static void main(String[] args) {
		MainMenu_Pharmacies pharm = new MainMenu_Pharmacies();
		try {
			pharm.mainMenu_Pharmacy();
			jdbcmanager.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MainMenu_Pharmacies() {
		pharmacyMenu = null;
		table = null;
		clientsListArray = null;
		doctorsListArray = null;
		medicinesListArray = null;
		removeButtonProduct = null;
		jdbcmanager = new JDBCManager();
		clientmanager = new JDBCClientManager(jdbcmanager);
		doctormanager = new JDBCDoctorManager(jdbcmanager);
		pharmacistmanager = new JDBCPharmacistManager(jdbcmanager);
		medicinemanager = new JDBCMedicineManager(jdbcmanager);
		ordermanager = new JDBCOrderManager(jdbcmanager);
		administratormanager = new JDBCAdministratorManager(jdbcmanager);
		usermanager = new JPAUserManager();
	}

	public void mainMenu_Pharmacy() throws Exception {
		pharmacyMenu = new JFrame();

		pharmacyMenu.setLayout(new MigLayout("fill", "30[]30[]30", "30[]30"));
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}
		pharmacyMenu.setSize(1150, 750);
		pharmacyMenu.getContentPane().setBackground(new Color(12, 11, 61));
		pharmacyMenu.setLocationRelativeTo(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new MigLayout("fill"));
		menuPanel.setBackground(Color.RED);

		ImageIcon darkLogo = new ImageIcon("resources/DarkPharmaCorpLogo.png");
		JLabel darkLabel = new JLabel(darkLogo);

		ImageIcon welcomeLogo = new ImageIcon("resources/welcomePharmacist.png");
		JLabel welcomeLabel = new JLabel(welcomeLogo);

		menuPanel.setLayout(new MigLayout("", "[]", "[][][][][][][]"));
		menuPanel.setBackground(new Color(12, 11, 61));

		JButton productsButton = new JButton("Products");
		productsButton.setForeground(Color.WHITE);
		productsButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		productsButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		productsButton.setBackground(new Color(19, 18, 79));
		productsButton.setPreferredSize(new Dimension(400, 25));
		productsButton.addActionListener(e -> {
			panel4.removeAll();
			try {
				panel4.add(productsList(), "grow");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			panel4.updateUI();
		});

		JButton clients = new JButton("Clients");
		clients.setForeground(Color.WHITE);
		clients.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		clients.setFont(new Font("Calibri", Font.PLAIN, 18));
		clients.setBackground(new Color(19, 18, 79));
		clients.setPreferredSize(new Dimension(400, 25));
		clients.addActionListener(e -> {
			panel4.removeAll();
			try {
				panel4.add(clientsList(), "grow");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			panel4.updateUI();
		});

		JButton doctors = new JButton("Doctors");
		doctors.setForeground(Color.WHITE);
		doctors.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		doctors.setFont(new Font("Calibri", Font.PLAIN, 18));
		doctors.setBackground(new Color(19, 18, 79));
		doctors.setPreferredSize(new Dimension(400, 25));
		doctors.addActionListener(e -> {
			panel4.removeAll();
			try {
				panel4.add(doctorsList(), "grow");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			panel4.updateUI();
		});

		/*
		 * JButton shipments = new JButton("Shipments");
		 * shipments.setForeground(Color.WHITE); shipments.setFont(new Font("Calibri",
		 * Font.PLAIN, 18)); shipments.setBackground(new Color(19, 18, 79));
		 * shipments.setPreferredSize(new Dimension(400, 25));
		 * shipments.addActionListener(e -> { panel4.removeAll();
		 * panel4.add(shipmentsList(), "grow"); panel4.updateUI(); });
		 */

		JButton help = new JButton("Help");
		help.setForeground(Color.WHITE);
		help.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
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
			pharmacyMenu.dispatchEvent(new WindowEvent(pharmacyMenu, WindowEvent.WINDOW_CLOSING));
			Login loginMenu = new Login();
			loginMenu.loginRegister();
		});

		menuPanel.add(welcomeLabel, "wrap, gapbottom 5");
		menuPanel.add(darkLabel, "CENTER, wrap, gapbottom 10");
		menuPanel.add(productsButton, "CENTER, wrap, gapbottom 15");
		menuPanel.add(clients, "CENTER, wrap, gapbottom 15");
		menuPanel.add(doctors, "CENTER, wrap, gapbottom 15");
		// menuPanel.add(shipments, "CENTER, wrap, gapbottom 15");
		menuPanel.add(help, "CENTER, wrap, gapbottom 15");
		menuPanel.add(signout, "CENTER");

		JPanel panel2 = new JPanel();
		panel2.setLayout(new MigLayout("fill"));
		panel2.setBackground(new Color(12, 11, 61));

		panel4 = new JPanel();
		panel4.setLayout(new MigLayout("fill"));
		panel4.setBackground(Color.GREEN);
		panel4.setBackground(new Color(12, 11, 61));
		panel4.add(productsList(), "grow");

		JPanel panel3 = new JPanel();
		panel3.setLayout(new MigLayout("fill"));
		panel3.setBackground(new Color(12, 11, 61));

		removeButtonProduct = new JButton("Remove Product From Table");
		removeButtonProduct.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		removeButtonProduct.setForeground(Color.WHITE);
		removeButtonProduct.setFont(new Font("Calibri", Font.PLAIN, 18));
		removeButtonProduct.setBackground(new Color(19, 18, 79));
		removeButtonProduct.setPreferredSize(new Dimension(600, 25));
		removeButtonProduct.addActionListener(e -> {
			int row = table.getSelectedRow();

			if (row != -1) {
				tableModel.removeRow(table.getSelectedRow());
				Medicine medicine = medicinesListArray.get(row);
				medicinesListArray.remove(row);
				try {
					medicinemanager.deleteMedicinebyCode(medicine.getCode());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error: Medicine not found.");
				}
				JOptionPane.showMessageDialog(null, "Product successfully deleted.");
			}
		});

		JButton addProduct = new JButton("Add New Product");
		addProduct.setForeground(Color.WHITE);
		addProduct.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		addProduct.setFont(new Font("Calibri", Font.PLAIN, 18));
		addProduct.setBackground(new Color(19, 18, 79));
		addProduct.setPreferredSize(new Dimension(600, 25));
		addProduct.addActionListener(e -> {
			addNewProduct();
		});

		JButton updateStock = new JButton("Update Stock");
		updateStock.setForeground(Color.WHITE);
		updateStock.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		updateStock.setFont(new Font("Calibri", Font.PLAIN, 18));
		updateStock.setBackground(new Color(19, 18, 79));
		updateStock.setPreferredSize(new Dimension(600, 25));
		updateStock.addActionListener(e -> {
			updateStockOfProduct();
		});

		panel3.add(addProduct, "gaptop 60, wrap, gapbottom 15");
		panel3.add(updateStock, "wrap, gapbottom 15");
		panel3.add(removeButtonProduct, "gapbottom 20");
		panel2.add(panel3, "wrap, grow");
		panel2.add(panel4, "grow");

		pharmacyMenu.add(menuPanel, "grow");
		pharmacyMenu.add(panel2, "grow");
		pharmacyMenu.setVisible(true);
	}

	public JScrollPane productsList() throws Exception {
		medicineTitles = new String[] { "Name", "Prescription Required", "Price", "Instructions", "Expiration Date",
				"Available Stock" };
		medicinesListArray = getAllMedicines();

		String[][] medicines2DArray = new String[medicinesListArray.size()][6];

		for (int i = 0; i < medicinesListArray.size(); i++) {
			Medicine medicine = medicinesListArray.get(i);
			medicines2DArray[i][0] = medicine.getName();
			medicines2DArray[i][1] = String.valueOf(medicine.getPrescribed());
			medicines2DArray[i][2] = medicine.getPrice().toString();
			medicines2DArray[i][3] = medicine.getInstructions();
			medicines2DArray[i][4] = medicine.getExpirations().toString();
			medicines2DArray[i][5] = medicine.getStock().toString();
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

	public void addNewProduct() {
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

		JLabel registrationName = new JLabel("Add a New Product");
		registrationName.setFont(new Font("CALIBRI", Font.PLAIN, 28));
		registrationName.setForeground(Color.WHITE);

		JLabel name = new JLabel("Name");
		name.setForeground(Color.WHITE);
		name.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField nameText = new JTextField(20);
		nameText.setForeground(new Color(62, 76, 169));
		nameText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		nameText.setPreferredSize(new Dimension(200, 25));

		JLabel price = new JLabel("Price");
		price.setForeground(Color.WHITE);
		price.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField priceText = new JTextField(20);
		priceText.setForeground(new Color(62, 76, 169));
		priceText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		priceText.setPreferredSize(new Dimension(200, 25));

		JLabel stock = new JLabel("Stock");
		stock.setForeground(Color.WHITE);
		stock.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField stockText = new JTextField(20);
		stockText.setForeground(new Color(62, 76, 169));
		stockText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		stockText.setPreferredSize(new Dimension(200, 25));

		JLabel expirationDate = new JLabel("Expiration Date (YYYY-MM-DD)");
		expirationDate.setForeground(Color.WHITE);
		expirationDate.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField expirationDateText = new JTextField(20);
		expirationDateText.setForeground(new Color(62, 76, 169));
		expirationDateText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		expirationDateText.setPreferredSize(new Dimension(200, 25));

		JLabel prescription = new JLabel("Prescription Required?");
		prescription.setForeground(Color.WHITE);
		prescription.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		String[] typeList = { "true", "false" };
		JComboBox prescriptionType = new JComboBox(typeList);
		prescriptionType.setMinimumSize(new Dimension(260, 1));
		prescriptionType.setPreferredSize(
				new Dimension(prescriptionType.getPreferredSize().width, expirationDateText.getPreferredSize().height));
		prescriptionType.setForeground(new Color(62, 76, 169));
		prescriptionType.setFont(new Font("CALIBRI", Font.BOLD, 12));

		JLabel instructions = new JLabel("Instructions");
		instructions.setForeground(Color.WHITE);
		instructions.setFont(new Font("CALIBRI", Font.PLAIN, 16));
		JTextField instructionsText = new JTextField(43);
		instructionsText.setForeground(new Color(62, 76, 169));
		instructionsText.setFont(new Font("CALIBRI", Font.BOLD, 12));
		instructionsText.setPreferredSize(new Dimension(400, 25));

		JButton registerButton = new JButton("DONE");
		registerButton.setFont(new Font("CALIBRI", Font.PLAIN, 20));
		registerButton.setBackground(new Color(12, 11, 61));
		registerButton.setForeground(Color.WHITE);
		registerButton.setPreferredSize(new Dimension(400, 25));
		registerButton.addActionListener(e -> {
			new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() {
					try {
						String nameEx = nameText.getText();
						Float priceEx = Float.parseFloat(priceText.getText());
						String instructionsEx = instructionsText.getText();
						Integer stockEx = Integer.parseInt(stockText.getText());
						String date_stringEx = expirationDateText.getText();
						Date dateEx = Date.valueOf(date_stringEx);
						Boolean prescribed = Boolean.parseBoolean(prescriptionType.getSelectedItem().toString());

						String priceExString = priceText.getText();
						if (priceExString.isEmpty() || !priceExString.matches("\\d+")) {
							throw new IllegalArgumentException("Invalid price.");
						}
						String stockExString = stockText.getText();
						if (stockExString.isEmpty() || !stockExString.matches("\\d+")) {
							throw new IllegalArgumentException("Invalid stock quantity.");
						}

						String emailUserInput = JOptionPane.showInputDialog(null, "Introduce Pharmacist's E-Mail.",
								"Input", JOptionPane.QUESTION_MESSAGE);
						if (emailUserInput != null) {
							Pharmacist pharmacistUser = pharmacistmanager.searchPharmacistByEmail(emailUserInput);

							String blobString = "paracetamol-500mg-caplets-x-32-p278-1003_zoom.png";
							byte[] blobBytes = blobString.getBytes();
							Medicine m = new Medicine(nameEx, instructionsEx, priceEx, stockEx, dateEx, pharmacistUser,
									blobBytes, prescribed);
							medicinemanager.addMedicine(m);
						} else {
							System.out.println("Error");
						}
					} catch (Exception excep) {
						SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Error: Invalid input."));
					}
					return null;
				}

				@Override
				protected void done() {
					registerFrame.dispatchEvent(new WindowEvent(registerFrame, WindowEvent.WINDOW_CLOSING));
				}
			}.execute();
		});

		registerPanel.add(registrationName, "wrap, gaptop 25, gapbottom 25, span 2, CENTER");
		registerPanel.add(name);
		registerPanel.add(price, "wrap");
		registerPanel.add(nameText, "gapbottom 20");
		registerPanel.add(priceText, "wrap");
		registerPanel.add(stock);
		registerPanel.add(expirationDate, "wrap");
		registerPanel.add(stockText, "gapbottom 20");
		registerPanel.add(expirationDateText, "wrap");
		registerPanel.add(instructions, "wrap");
		registerPanel.add(instructionsText, "CENTER, span 2, wrap, gapbottom 20");
		registerPanel.add(prescription);
		registerPanel.add(prescriptionType, "wrap");
		registerPanel.add(registerButton, "CENTER, gaptop 50, span 2");

		registerFrame.add(registerPanel, "CENTER, gapleft 150");
		registerFrame.setVisible(true);
	}

	private static List<Pharmacist> getAllPharmacies() throws Exception {
		return pharmacistmanager.getListOfPharmacist();
	}

	public void updateStockOfProduct() {
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}

		JFrame orderFormFrame = new JFrame();
		orderFormFrame.setLayout(new MigLayout("", "", ""));
		orderFormFrame.setSize(450, 350);
		orderFormFrame.setLocationRelativeTo(null);
		orderFormFrame.getContentPane().setBackground(new Color(12, 11, 61));

		JLabel clientTypeLabel = new JLabel("Available Products");
		clientTypeLabel.setForeground(Color.WHITE);
		clientTypeLabel.setFont(new Font("CALIBRI", Font.PLAIN, 12));
		orderFormFrame.add(clientTypeLabel, "wrap, CENTER");

		List<Medicine> typeList;
		try {
			typeList = getAllMedicines();
			String[] typeListToString = new String[typeList.size()];
			for (int i = 0; i < typeList.size(); i++) {
				Medicine medicine = typeList.get(i);
				typeListToString[i] = medicine.getName();
			}

			availableProductsClients = new JComboBox<>(typeListToString);
			availableProductsClients.setMinimumSize(new Dimension(260, 1));
			availableProductsClients.setForeground(new Color(62, 76, 169));
			availableProductsClients.setFont(new Font("CALIBRI", Font.BOLD, 14));
			orderFormFrame.add(availableProductsClients, "wrap, CENTER, gapbottom 10, gaptop 10");

			String medicineName = (String) availableProductsClients.getSelectedItem();
			Medicine medicineOrder = medicinemanager.searchMedicineByName(medicineName);
			SpinnerNumberModel chooseQuantityModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
			JSpinner chooseQuantity = new JSpinner(chooseQuantityModel);
			chooseQuantity.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					chosenStock = (int) chooseQuantity.getValue();
				}
			});

			JLabel email = new JLabel("Please introduce your e-mail:");
			email.setForeground(Color.WHITE);
			email.setFont(new Font("CALIBRI", Font.PLAIN, 12));
			JTextField emailText = new JTextField(20);
			emailText.setForeground(new Color(62, 76, 169));
			emailText.setFont(new Font("CALIBRI", Font.BOLD, 12));
			emailText.setPreferredSize(new Dimension(200, 25));
			orderFormFrame.add(email, "wrap, gaptop 10, CENTER");
			orderFormFrame.add(emailText, "wrap, gapbottom 10, CENTER");

			JLabel emailAdmin = new JLabel("Please introduce an administrator's e-mail:");
			emailAdmin.setForeground(Color.WHITE);
			emailAdmin.setFont(new Font("CALIBRI", Font.PLAIN, 12));
			JTextField emailTextAdmin = new JTextField(20);
			emailTextAdmin.setForeground(new Color(62, 76, 169));
			emailTextAdmin.setFont(new Font("CALIBRI", Font.BOLD, 12));
			emailTextAdmin.setPreferredSize(new Dimension(200, 25));
			orderFormFrame.add(emailAdmin, "wrap, gaptop 10, CENTER");
			orderFormFrame.add(emailTextAdmin, "wrap, gapbottom 10, CENTER");

			chooseQuantity.setMinimumSize(new Dimension(260, 1));
			chooseQuantity.setForeground(new Color(62, 76, 169));
			chooseQuantity.setFont(new Font("CALIBRI", Font.BOLD, 12));
			orderFormFrame.add(chooseQuantity, "wrap, CENTER, gapbottom 10, gaptop 10");

			purchaseButton = new JButton("ADD STOCK");
			purchaseButton.setFont(new Font("CALIBRI", Font.PLAIN, 16));
			purchaseButton.setBackground(new Color(12, 11, 61));
			purchaseButton.setForeground(Color.WHITE);
			purchaseButton.setPreferredSize(new Dimension(400, 25));
			purchaseButton.addActionListener(e -> {
				new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() {
						Integer medicine_code = medicineOrder.getCode();
						try {
							Pharmacist pharmacistSample = pharmacistmanager
									.searchPharmacistByEmail(emailText.getText());
							Administrator adminInCharge = administratormanager
									.searchAdministratorByEmail(emailTextAdmin.getText());

							Order order = new Order(medicineOrder.getPrice(), chosenStock, pharmacistSample,
									adminInCharge,medicineOrder);
							ordermanager.addOrder(order);
							medicinemanager.updateStock(medicineOrder.getStock() + chosenStock, medicine_code);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Invalid input. Try again.");
						}
						JOptionPane.showMessageDialog(null, "Thank you for updating the stock.");
						return null;
					}

					@Override
					protected void done() {
						orderFormFrame.dispatchEvent(new WindowEvent(orderFormFrame, WindowEvent.WINDOW_CLOSING));
					}
				}.execute();
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		orderFormFrame.add(purchaseButton, "wrap, CENTER, gaptop 20");
		orderFormFrame.setVisible(true);
	}

}
