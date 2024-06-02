package PharmacyCompanyXML;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import PharmacyCompanyInterfaces.AdministratorManager;
import PharmacyCompanyInterfaces.ClientManager;
import PharmacyCompanyInterfaces.DoctorManager;
import PharmacyCompanyInterfaces.MedicineManager;
import PharmacyCompanyInterfaces.OrderManager;
import PharmacyCompanyInterfaces.PharmacistManager;
import PharmacyCompanyInterfaces.XMLManager;
import PharmacyCompanyJDBC.JDBCAdministratorManager;
import PharmacyCompanyJDBC.JDBCClientManager;
import PharmacyCompanyJDBC.JDBCDoctorManager;
import PharmacyCompanyJDBC.JDBCManager;
import PharmacyCompanyJDBC.JDBCMedicineManager;
import PharmacyCompanyJDBC.JDBCOrderManager;
import PharmacyCompanyJDBC.JDBCPharmacistManager;
import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Client;
import PharmacyCompanyPOJOs.Doctor;
import PharmacyCompanyPOJOs.Medicine;
import PharmacyCompanyPOJOs.Order;
import PharmacyCompanyPOJOs.Pharmacist;

public class XMLManagerImpl implements XMLManager 
{
	JDBCManager manager;
	AdministratorManager administratormanager;
	PharmacistManager pharmacistmanager;
	ClientManager clientmanager;
	DoctorManager doctormanager;
	OrderManager ordermanager;
	MedicineManager medicinemanager;
	
	public XMLManagerImpl () 
	{
		super ();
		manager = new JDBCManager();
		administratormanager = new JDBCAdministratorManager(manager);
		ordermanager = new JDBCOrderManager(manager);
		clientmanager = new JDBCClientManager(manager);
		pharmacistmanager = new JDBCPharmacistManager(manager);
		doctormanager = new JDBCDoctorManager(manager);
		medicinemanager = new JDBCMedicineManager(manager);
	}
	
	@Override
	public void administrator2Xml(Integer id) throws Exception 
	{
		Administrator a = null;
		List<Order> orders = new LinkedList<Order>(); 
		
		
		try {
			
			a = administratormanager.searchAdministratorById(id);
			orders = ordermanager.getOrderOfAdministrator(id);
			a.setOrders(orders);
			
			//export the administrator to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Administrator.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Administrator.xml");
			marshaller.marshal(a,file);
			System.out.println(a);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void client2Xml(Integer id) throws Exception 
	{
		Client c = null;
		List<Medicine> medicines = new LinkedList<Medicine>();
		
		try {
			

			medicines = medicinemanager.getListofMedicinesPurchasedClient(id);
			c = clientmanager.searchClientById(id);
			c.setMedicines(medicines);
			
			//export the client to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Client.xml");
			marshaller.marshal(c, file);
			System.out.println(c);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	


	@Override
	public void pharmacist2Xml(Integer id) throws Exception 
	{
		Pharmacist p = null;
		List<Medicine> medicines = new LinkedList<Medicine>();
		List <Order> orders = new LinkedList<Order>();
		
		try {
			medicines = medicinemanager.getMedicinesofPharmacist(id);
			p = pharmacistmanager.searchPharmacistById(id);
			orders = ordermanager.getOrderOfPharmacist(id);
			p.setMedicines_created(medicines);
			p.setOrders(orders);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacist.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Pharmacist.xml");
			marshaller.marshal(p,file);
			System.out.print(p);
			
		}catch(Exception e) {
			
		}
	}
	
	@Override
	public void doctor2Xml(Integer id) throws Exception 
	{
		Doctor d = null;
		List<Medicine> medicines = new LinkedList <Medicine>();
		
		try {
			medicines = medicinemanager.getListofMedicinesPurchasedDoctor(id);
			d = doctormanager.searchDoctorById(id);
			d.setMedicines(medicines);
		
			JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Doctor.xml");
			marshaller.marshal(d, file);
			System.out.println(d);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void Medicine2Xml(Integer code) throws Exception 
	{
		Medicine m = null;
		
		try {
			m = medicinemanager.searchMedicineByCode(code);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Medicine.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Medicine.xml");
			marshaller.marshal(m,file);
			System.out.println(m);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void Order2Xml(Integer code) throws Exception 
	{
		Order o = null;
		
		try {
			o = ordermanager.searchOrderByCode(code);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File ("./xmls/Order.xml");
			marshaller.marshal(o,file);
			System.out.println(o);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public Client xmltoClient (File xml) throws Exception 
	{
		Client c = null;
		
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			c = (Client) unmarshaller.unmarshal(xml);
			clientmanager.createClient(c);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	
	@Override
	public Administrator xmltoAdministrator (File xml) throws Exception 
	{
		Administrator a = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Administrator.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			a = (Administrator) unmarshaller.unmarshal(xml);
			administratormanager.createAdministrator(a);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
	@Override
	public Doctor xmltoDoctor (File xml) throws Exception 
	{
		Doctor d = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			d = (Doctor) unmarshaller.unmarshal(xml);
			doctormanager.createDoctor(d);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	
	@Override
	public Pharmacist xmltoPharmacist (File xml) throws Exception 
	{
		Pharmacist p = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacist.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			p = (Pharmacist) unmarshaller.unmarshal(xml);
			pharmacistmanager.createPharmacist(p);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	@Override
	public Medicine xmltoMedicine (File xml) throws Exception 
	{
		Medicine m = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacist.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			m = (Medicine) unmarshaller.unmarshal(xml);
			medicinemanager.addMedicine(m);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	
	@Override
	public Order xmltoOrder (File xml) throws Exception 
	{
		Order o = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacist.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			o = (Order) unmarshaller.unmarshal(xml);
			ordermanager.addOrder(o);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	@Override
	public void simpleTransform(String sourcePath, String xsltPath,String resultDir) throws Exception 
	{
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

