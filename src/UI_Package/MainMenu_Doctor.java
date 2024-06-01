package UI_Package;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
import PharmacyCompanyPOJOs.Doctor;
import PharmacyCompanyPOJOs.Medicine;
import PharmacyCompanyPOJOs.Pharmacist;
import net.miginfocom.swing.MigLayout;

public class MainMenu_Doctor {
	private JFrame doctorMenu;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTable tableClients;
	private JPanel panel4;
	private JButton purchaseButton;
	private int chosenStock;
	private static JDBCManager jdbcmanager;
	private static AdministratorManager administratormanager;
	private static ClientManager clientmanager;
	private static PharmacistManager pharmacistmanager;
	private static DoctorManager doctormanager;
	private static MedicineManager medicinemanager;
	private static OrderManager ordermanager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserManager usermanager;

	private String[] pharmacyTitles;
	private String[] medicineTitles;

	private List<Pharmacist> pharmaciesListArray;
	private List<Medicine> medicinesListArray;

	private java.util.List<java.util.List<String>> products;
	private java.util.List<java.util.List<String>> pharmacies;
	private JComboBox<String> availableProductsDoctors;

	public MainMenu_Doctor() {
		doctorMenu = null;
		table = null;
		tableClients = null;
		products = new ArrayList<>();
		products.add(Arrays.asList("Augmentine", "56", "Yes", "€35,00"));
		products.add(Arrays.asList("Amoxicilin", "56", "Yes", "€35,00"));
		products.add(Arrays.asList("Paracetamol", "56", "No", "€35,00"));
		pharmacyTitles = null;
		medicineTitles = null;
		purchaseButton = null;
		pharmaciesListArray = null;
		medicinesListArray = null;
		tableModel = null;
		availableProductsDoctors = null;
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
		MainMenu_Doctor menu = new MainMenu_Doctor();
		menu.mainMenu_Doctor();
	}

	public void mainMenu_Doctor() {
		doctorMenu = new JFrame();

		doctorMenu.setLayout(new MigLayout("fill", "30[]30[]30", "30[]30"));
		FlatLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}
		doctorMenu.setSize(1150, 750);
		doctorMenu.getContentPane().setBackground(new Color(12, 11, 61));
		doctorMenu.setLocationRelativeTo(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new MigLayout("fill"));
		menuPanel.setBackground(Color.RED);

		ImageIcon darkLogo = new ImageIcon("resources/DarkPharmaCorpLogo.png");
		JLabel darkLabel = new JLabel(darkLogo);

		ImageIcon welcomeLogo = new ImageIcon("resources/welcomeDoctor.png");
		JLabel welcomeLabel = new JLabel(welcomeLogo);

		menuPanel.setLayout(new MigLayout("", "[]", "[][][][][][][]"));
		menuPanel.setBackground(new Color(12, 11, 61));

		JButton productsButton = new JButton("Products");
		productsButton.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		productsButton.setForeground(Color.WHITE);
		productsButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		productsButton.setBackground(new Color(19, 18, 79));
		productsButton.setPreferredSize(new Dimension(400, 25));
		productsButton.addActionListener(e -> {
			panel4.removeAll();
			try {
				panel4.add(medicinesList(), "grow");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			panel4.updateUI();
		});

		JButton pharmacies = new JButton("Pharmacies");
		pharmacies.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		pharmacies.setForeground(Color.WHITE);
		pharmacies.setFont(new Font("Calibri", Font.PLAIN, 18));
		pharmacies.setBackground(new Color(19, 18, 79));
		pharmacies.setPreferredSize(new Dimension(400, 25));
		pharmacies.addActionListener(e -> {
			panel4.removeAll();
			try {
				panel4.add(pharmaciesList(), "grow");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			panel4.updateUI();
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
			doctorMenu.dispatchEvent(new WindowEvent(doctorMenu, WindowEvent.WINDOW_CLOSING));
			Login loginMenu = new Login();
			loginMenu.loginRegister();
		});

		menuPanel.add(welcomeLabel, "wrap, gapbottom 20");
		menuPanel.add(darkLabel, "CENTER, wrap, gapbottom 40");
		menuPanel.add(productsButton, "CENTER, wrap, gapbottom 20");
		menuPanel.add(pharmacies, "CENTER, wrap, gapbottom 20");
		menuPanel.add(help, "CENTER, wrap, gapbottom 20");
		menuPanel.add(signout, "CENTER");

		JPanel panel2 = new JPanel();
		panel2.setLayout(new MigLayout("fill"));
		panel2.setBackground(new Color(12, 11, 61));

		panel4 = new JPanel();
		panel4.setLayout(new MigLayout("fill"));
		panel4.setBackground(new Color(12, 11, 61));
		try {
			panel4.add(medicinesList(), "grow");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		JPanel panel3 = new JPanel();
		panel3.setLayout(new MigLayout("fill"));
		panel3.setBackground(new Color(12, 11, 61));

		JButton makePurchase = new JButton("Make a Purchase");
		makePurchase.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 32, 82), new Color(32, 32, 82)));
		makePurchase.setForeground(Color.WHITE);
		makePurchase.setFont(new Font("Calibri", Font.PLAIN, 18));
		makePurchase.setBackground(new Color(19, 18, 79));
		makePurchase.setPreferredSize(new Dimension(600, 25));
		makePurchase.addActionListener(e -> {
			purchase();
		});

		panel3.add(makePurchase, "gaptop 100");
		panel2.add(panel3, "wrap, grow");
		panel2.add(panel4, "grow");

		doctorMenu.add(menuPanel, "grow");
		doctorMenu.add(panel2, "grow");
		doctorMenu.setVisible(true);
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

	public JScrollPane pharmaciesList() throws Exception {
		pharmacyTitles = new String[] { "Name", "Surname", "E-Mail", "Phone Number" };
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

	public void purchase() {
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

		JLabel doctorTypeLabel = new JLabel("Products List, Prescription required");
		doctorTypeLabel.setForeground(Color.WHITE);
		doctorTypeLabel.setFont(new Font("CALIBRI", Font.PLAIN, 12));
		orderFormFrame.add(doctorTypeLabel, "wrap, CENTER");

		List<Medicine> typeList;
		try {
			typeList = getAllMedicines();
			String[] typeListToString = new String[typeList.size()];
			for (int i = 0; i < typeList.size(); i++) {
				Medicine medicine = typeList.get(i);
				typeListToString[i] = medicine.getName();
			}

			availableProductsDoctors = new JComboBox<>(typeListToString);
			availableProductsDoctors.setMinimumSize(new Dimension(260, 1));
			availableProductsDoctors.setForeground(new Color(62, 76, 169));
			availableProductsDoctors.setFont(new Font("CALIBRI", Font.BOLD, 14));
			orderFormFrame.add(availableProductsDoctors, "wrap, CENTER, gapbottom 10, gaptop 10");

			SpinnerNumberModel chooseQuantityModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
			JSpinner chooseQuantity = new JSpinner(chooseQuantityModel);
			chooseQuantity.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					chosenStock = (int) chooseQuantity.getValue();
				}
			});

			chooseQuantity.setMinimumSize(new Dimension(260, 1));
			chooseQuantity.setForeground(new Color(62, 76, 169));
			chooseQuantity.setFont(new Font("CALIBRI", Font.BOLD, 12));
			orderFormFrame.add(chooseQuantity, "wrap, CENTER, gapbottom 10, gaptop 10");

			JLabel email = new JLabel("Please introduce your e-mail:");
			email.setForeground(Color.WHITE);
			email.setFont(new Font("CALIBRI", Font.PLAIN, 12));
			JTextField emailText = new JTextField(20);
			emailText.setForeground(new Color(62, 76, 169));
			emailText.setFont(new Font("CALIBRI", Font.BOLD, 12));
			emailText.setPreferredSize(new Dimension(200, 25));
			orderFormFrame.add(email, "wrap, gaptop10, CENTER");
			orderFormFrame.add(emailText, "wrap, gapbottom 10, CENTER");

			purchaseButton = new JButton("COMPLETE PURCHASE");
			purchaseButton.setFont(new Font("CALIBRI", Font.PLAIN, 16));
			purchaseButton.setBackground(new Color(12, 11, 61));
			purchaseButton.setForeground(Color.WHITE);
			purchaseButton.setPreferredSize(new Dimension(400, 25));

			purchaseButton.addActionListener(e -> {
				new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() {
						try {
							String medicineName = (String) availableProductsDoctors.getSelectedItem();
							Medicine medicineOrder = medicinemanager.searchMedicineByName(medicineName);
							if (chosenStock > medicineOrder.getStock()) {
								JOptionPane.showMessageDialog(null, "Error: Insufficient stock. Try again.");
								return null;
							}
							Integer medicine_code = medicineOrder.getCode();
							Float bill_number = chosenStock * (medicineOrder.getPrice());
							Doctor doctorBuyer = doctormanager.searchDoctorByEmail(emailText.getText());
							int doctorID = doctorBuyer.getId();
							medicinemanager.assignMedicinetoDoctor(doctorID, medicine_code, bill_number, chosenStock);
							medicinemanager.updateStock(medicineOrder.getStock() - chosenStock, medicine_code);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Error: Invalid input. Try again.");
							return null;
						}
						JOptionPane.showMessageDialog(null, "Thank you for your purchase.");
						return null;
					}

					@Override
					protected void done() {
						orderFormFrame.dispatchEvent(new WindowEvent(orderFormFrame, WindowEvent.WINDOW_CLOSING));
					}
				}.execute();
			});

		} catch (

		Exception e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid input. Try again.");
			return;
		}
		orderFormFrame.add(purchaseButton, "wrap, CENTER, gaptop 40");
		orderFormFrame.setVisible(true);
	}
}
