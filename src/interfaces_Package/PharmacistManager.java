package interfaces_Package;

import java.util.List;

import pojos_Package.Pharmacist;

public interface PharmacistManager 
{
	public void createPharmacist (Pharmacist p);
	public List <Pharmacist> getListOfPharmacist();
	public Pharmacist searchPharmacistById (Integer id);
}
