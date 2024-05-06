package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Pharmacist;

public interface PharmacistManager 
{
	public void createPharmacist (Pharmacist p);
	public List <Pharmacist> getListOfPharmacist();
	public Pharmacist searchPharmacistById (Integer id);
	public void deletePharmacistbyId (Integer id) throws Exception;
}
