package UI_Package;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

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
import PharmacyCompanyPOJOs.Role;
import PharmacyCompanyPOJOs.User;

public class MainMenu {

	private static JDBCManager jdbcmanager;
	private static AdministratorManager administratormanager;
	private static ClientManager clientmanager;
	private static PharmacistManager pharmacistmanager;
	private static DoctorManager doctormanager;
	private static MedicineManager medicinemanager;
	private static OrderManager ordermanager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserManager usermanager;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		jdbcmanager = new JDBCManager();
		administratormanager = new JDBCAdministratorManager(jdbcmanager);
		clientmanager = new JDBCClientManager(jdbcmanager);
		doctormanager = new JDBCDoctorManager(jdbcmanager);
		pharmacistmanager = new JDBCPharmacistManager(jdbcmanager);
		medicinemanager = new JDBCMedicineManager(jdbcmanager);
		ordermanager = new JDBCOrderManager(jdbcmanager);
		usermanager = new JPAUserManager();
		Administrator a = null;
		Client c = null;
		Doctor d = null;
		Pharmacist p = null;
		Order o = null;
		Medicine m = null;

		try {
			int choice;
			do {
				System.out.println("Choose an initial option");
				System.out.println("1. Login User");
				System.out.println("2. SignUp User");
				System.out.println("0. Exit the aplication");
				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1 -> //
				{
					try {
						createAdministrator();
						createPharmacist();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Resolve it");
					}
				}
				case 2 -> {
					try {

						/* PharmacistMakeOrder(1); */
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Resolve it");
					}
				}
				case 3 -> {
					try {
						p = pharmacistmanager.searchPharmacistById(1);
						addMedicinetoCatalogue(p);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Resolve it");
					}
				}
				case 4 -> {
					try {
						System.out.println(ordermanager.getListOfOrders());
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Resolve it");
					}
				}
				case 5 -> {
					try {
						System.out.println(ordermanager.getOrderOfPharmacist(1));
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Resolve it");
					}
				}
				case 6 -> {
					try {

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Resolve it");
					}
				}
				case 7 -> {
					try {
						System.out.println("Introduce the id of the person: ");
						Integer id = Integer.parseInt(reader.readLine());
						p = pharmacistmanager.searchPharmacistById(id);
						addMedicinetoCatalogue(p);

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Resolve it");
					}
				}
				case 8 -> {
					try {
						ClientBuyMedicine(1);

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Resolve it");
					}
				}

				case 0 -> {
					jdbcmanager.disconnect();
					System.out.println("At least this doesnt fail");
					System.exit(0);
				}
				}

			} while (choice != 0);

		} catch (Exception e) {
			System.out.println("At least this doesnt fail");
			e.printStackTrace();
		}
	}
	// Se están haciendo pruebas de tipo

	// DONE
	private static void login() throws java.sql.SQLException, Exception {
		// TODO Auto-generated method stub
		System.out.println("Introduce your email: ");
		String email = reader.readLine();

		System.out.println("Introduce your password: ");
		String passwd = reader.readLine();

		User u = usermanager.checkPassword(email, passwd);

		if (u == null) {
			System.out.println("Not able to login");
		}
		if (u != null & u.getRole().getName().equals("client")) {
			System.out.println("Login of client successful!");

			// We can now call a menu for each type of user
		} else if (u != null & u.getRole().getName().equals("doctor")) {
			System.out.println("Login of doctor successful!");

		} else if (u != null & u.getRole().getName().equals("administrator")) {
			System.out.println("Login of administrator successful!");
			Administrator a = administratormanager.searchAdministratorByEmail(email);
			System.out.println(a.toString());

		} else if (u != null & u.getRole().getName().equals("pharmacist")) {
			System.out.println("Login of pharmacist successful!");
		}

	}

	private static void signUpUser() {
		// TODO Auto-generated method stub
		try {
			System.out.print("Write an email for your account: ");
			String email = reader.readLine();
			System.out.print("Write a password for your account: ");
			String password = reader.readLine();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();
			System.out.println("Introduce the role of the user");
			System.out.println("1: client, 2: doctor 3: administrator 4: pharmacist");
			Integer rol = Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(rol);
			User u = new User(email, pass, r);
			usermanager.newUser(u);
			System.out.println("Sign-Up and Registration in the system done, now try to login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updatePassword() throws Exception {

		System.out.println("Email: ");
		String email = reader.readLine();

		System.out.println("Enter current Password");
		String passwd = reader.readLine();

		System.out.println("Enter new Password");
		String new_passwd = reader.readLine();

		User u = usermanager.checkPassword(email, passwd);

		if (u != null) {
			usermanager.changePassword(u, new_passwd);
			// El método de changePassword está incompleto
		}

	}

	public static void createAdministrator() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Introduce your name");
		String name = reader.readLine();
		System.out.println("Introduce your surname");
		String surname = reader.readLine();
		System.out.println("Introduce your email");
		String email = reader.readLine();
		System.out.println("Introduce your phone number");
		Integer phonenumber = Integer.parseInt(reader.readLine());
		Administrator a = new Administrator(name, surname, phonenumber, email);
		administratormanager.createAdministrator(a);
	}

	private static void getAlladministrators() throws Exception {

		List<Administrator> administrators = null;
		administrators = administratormanager.getListOfAdministrators();
		System.out.println(administrators);

	}

	private static void createClient() throws Exception {
		System.out.println("Introduce your name");
		String name = reader.readLine();
		System.out.println("Introduce your surname");
		String surname = reader.readLine();
		System.out.println("Introduce your email");
		String email = reader.readLine();
		System.out.println("Introduce your phone number");
		Integer phonenumber = Integer.parseInt(reader.readLine());
		System.out.println("Introduce your address for deliveries");
		String address = reader.readLine();
		Client c = new Client(name, surname, address, phonenumber, email);
		System.out.println(c.toString());
		clientmanager.createClient(c);
	}

	private static void getAllClients() throws Exception {
		List<Client> clients = null;
		clients = clientmanager.getListOfClients();
		System.out.println(clients);
	}

	private static void createPharmacist() throws Exception {
		System.out.println("Introduce your name");
		String name = reader.readLine();
		System.out.println("Introduce your surname");
		String surname = reader.readLine();
		System.out.println("Introduce your email");
		String email = reader.readLine();
		System.out.println("Introduce your phone number");
		Integer phonenumber = Integer.parseInt(reader.readLine());
		Pharmacist p = new Pharmacist(name, surname, phonenumber, email);
		System.out.println(p.toString());
		pharmacistmanager.createPharmacist(p);
	}

	private static void createDoctor() throws Exception {
		System.out.println("Introduce your name");
		String name = reader.readLine();
		System.out.println("Introduce your surname");
		String surname = reader.readLine();
		System.out.println("Introduce your email");
		String email = reader.readLine();
		System.out.println("Introduce your phone number");
		Integer phonenumber = Integer.parseInt(reader.readLine());
		System.out.println("Introduce your address for deliveries");
		String address = reader.readLine();
		Doctor d = new Doctor(name, surname, address, phonenumber, email);
		System.out.println(d.toString());
		doctormanager.createDoctor(d);
	}

	private static void addMedicinetoCatalogue(Pharmacist pharmacist) throws Exception, SQLException {
		// Rellenar los datos de la medicina, incluyendo un stock inicial.

		System.out.print("Introduce your name of the medicine:");
		String name = reader.readLine();
		System.out.print("Introduce the price: ");
		Float price = Float.parseFloat(reader.readLine());
		System.out.print("Introduce the instructions:");
		String instructions = reader.readLine();
		System.out.print("Introduce the initial stock:");
		Integer stock = Integer.parseInt(reader.readLine());
		System.out.print("Introduce a date in this structure YYYY-MM-DD:");
		String date_string = reader.readLine();
		Date date = Date.valueOf(date_string);
		System.out.print("Introduce if the product must have a prescription by TRUE or FALSE:");
		Boolean prescribed = Boolean.parseBoolean(reader.readLine());
		String blobString = "paracetamol-500mg-caplets-x-32-p278-1003_zoom.png";
		byte[] blobBytes = blobString.getBytes();
		Medicine m = new Medicine(name, instructions, price, stock, date, pharmacist, blobBytes, prescribed);
		medicinemanager.addMedicine(m);
	}

	private static void getAllMedicine() throws Exception {

		List<Medicine> medicines = null;
		medicines = medicinemanager.getListofMedicines();
		System.out.println(medicines);
	}

	private static void getAllMedicineofPharmacist(Integer pharmacist_id) throws Exception {

		List<Medicine> medicines = null;
		medicines = medicinemanager.getMedicinesofPharmacist(pharmacist_id);
		System.out.println(medicines);
	}

	private static void ClientBuyMedicine(Integer client_id) throws Exception {
		System.out.print("Which medicine do you want to buy:");
		String name_medicine = reader.readLine();
		Medicine m = medicinemanager.searchMedicineByName(name_medicine);
		System.out.print("How much do you want to buy?:");
		Integer quantity = Integer.parseInt(reader.readLine());

		if (m.equals(null) || m.getPrescribed() == false) {
			System.out.print("You cannot buy a medicine that isn't in the list");
		}
		// Hay que garantizar que el stock es suficiente para la compra
		else if (quantity > m.getStock() || quantity < 0 || quantity == 0) {
			System.out.print("You cannot buy anything with that quantity, try again");
		} else {
			System.out.print("Purchase verified");
			Integer medicine_code = m.getCode();
			Float bill_number = quantity * (m.getPrice());
			String Bill = "Medicine:" + name_medicine + " Quantity: " + quantity + " Bill: " + bill_number;
			System.out.print(Bill);
			medicinemanager.assignMedicinetoClient(client_id, medicine_code, bill_number, quantity);
			medicinemanager.updateStock(m.getStock() - quantity, medicine_code);
		}

	}

	private static void DoctorBuyMedicine(Integer doctor_id) throws Exception {
		medicinemanager.getListofMedicines();
		System.out.print("Which medicine do you want to buy:");
		String name_medicine = reader.readLine();
		Medicine m = medicinemanager.searchMedicineByName(name_medicine);
		System.out.print("How much quantity do you want to buy?:");
		Integer quantity = Integer.parseInt(reader.readLine());

		// Puede comprar medicamentos que no sean prescritos o no
		if (m.equals(null)) {
			System.out.print("You cannot buy a medicine that isn't in the list");
		}
		if (quantity > m.getStock() || quantity < 0 || quantity == 0) {
			System.out.print("You cannot buy anything with that quantity, try again");
		} else {
			System.out.print("Purchase verified");
			Integer medicine_code = m.getCode();
			Float bill_number = quantity * (m.getPrice());
			String Bill = "Medicine:" + name_medicine + " Quantity: " + quantity + " Bill: " + bill_number + "€";
			System.out.print(Bill);
			medicinemanager.assignMedicinetoClient(doctor_id, medicine_code, bill_number, quantity);
			medicinemanager.updateStock(m.getStock() - quantity, medicine_code);
		}
	}

	private static void PharmacistMakeOrder(Integer pharmacist_id) throws Exception {
		Pharmacist pharmacist = pharmacistmanager.searchPharmacistById(pharmacist_id);
		System.out.println(medicinemanager.getMedicinesofPharmacist(pharmacist_id));
		System.out.print("Which medicine do you want to restock?: ");
		String name_medicine = reader.readLine();
		Medicine m = medicinemanager.searchMedicineByName(name_medicine);
		System.out.print("The email of the administrator you want to assign the order: ");
		String email = reader.readLine();
		System.out.print("The name of the administrator: ");
		String name_ad = reader.readLine();
		System.out.print("The surname of the administrator: ");
		String surnmane_ad = reader.readLine();
		/* String name_a, String surname_a,String email_a */
		Administrator assigned = administratormanager.searchAdministratorByNameEmail(name_ad, surnmane_ad, email);

		if (medicinemanager.checkListofMedicinesPharmacist(pharmacist_id, name_medicine) && assigned != null) {
			System.out.print("How much stock do you want to add?:");
			Integer quantity = Integer.parseInt(reader.readLine());

			if (quantity < 0 || quantity == 0) {
				System.out.print("You cannot update the stock with that quantity, try again later");
			} else {
				System.out.println("Data verified verified");
				Integer medicine_code = m.getCode();
				// Cada iteración tendrá un precio distinto, ya que las empresas podrán comprar
				// en ocasciones a precios bajos o altos
				// Además es para grantizar la recuperación de la orden.
				Integer valuepercentage = (int) Math.random() * ((100 - 50 + 1) + 50);
				Float total_price = quantity * (m.getPrice()) * valuepercentage / 100;
				String order_string = "Medicine:" + name_medicine + " Quantity: " + quantity + " Bill: " + total_price
						+ "€";
				System.out.println(order_string);
				Order order = new Order(total_price, quantity, pharmacist, assigned);
				ordermanager.addOrder(order);
				medicinemanager.updateStock(m.getStock() + quantity, medicine_code);
				Order database = ordermanager.searchOrderByPrice(total_price);
				ordermanager.assignMedicinetoOrder(medicine_code, database.getCode());
				System.out.println("Order verified and uploaded");
			}
		}

		// Métodos para ver listados de datos
		// De todo tipo, medicinas, ordenes, clientes... Otra cosa es quien pueda
		// acceder a todos.
	}
}
