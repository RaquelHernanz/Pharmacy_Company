package PharmacyCompanyInterfaces;

import java.io.File;

import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Client;
import PharmacyCompanyPOJOs.Doctor;
import PharmacyCompanyPOJOs.Medicine;
import PharmacyCompanyPOJOs.Order;
import PharmacyCompanyPOJOs.Pharmacist;

public interface XMLManager 
{
	public void Order2Xml(Integer code) throws Exception;
	public void Medicine2Xml(Integer code) throws Exception;
	public void doctor2Xml(Integer id) throws Exception;
	public void pharmacist2Xml(Integer id) throws Exception;
	public void client2Xml(Integer id) throws Exception;
	public void administrator2Xml(Integer id) throws Exception;
	public Client xmltoClient (File xml) throws Exception;
	public Administrator xmltoAdministrator (File xml) throws Exception; 
	public Doctor xmltoDoctor (File xml) throws Exception;
	public Pharmacist xmltoPharmacist (File xml) throws Exception;
	public Medicine xmltoMedicine (File xml) throws Exception;
	public Order xmltoOrder (File xml) throws Exception;
	public void simpleTransform(String sourcePath, String xsltPath,String resultDir) throws Exception;
}
