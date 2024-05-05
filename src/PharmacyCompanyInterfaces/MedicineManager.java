package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Medicine;


public interface MedicineManager 
{
	public void addMedicine (Medicine m);
	public List <Medicine> getListofMedicines ();
	public List<Medicine> getMedicinesofPharmacist(Integer pharmacist_id);
	/*public List <Medicine> getMedicinesPurchasedC (Integer client_id);
	public List <Medicine> getMedicinesPurchasedD (Integer doctor_id);*/

}
