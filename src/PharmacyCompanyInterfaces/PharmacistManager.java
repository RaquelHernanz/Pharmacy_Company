package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Pharmacist;

public interface PharmacistManager 
{
	public void createPharmacist (Pharmacist p);
	public List <Pharmacist> getListOfPharmacist();
	public Pharmacist searchPharmacistById (Integer id);
	public void deletePharmacistbyId (Integer id) throws Exception;
	public Pharmacist searchPharmacistByEmail (String email_p) throws Exception;
	public Pharmacist searchPharmacistByNameEmail (String name_p, String surname_p, String email_p) throws Exception;
}
