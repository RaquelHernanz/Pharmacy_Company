package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Medicine;


public interface MedicineManager 
{
	public void addMedicine (Medicine m);
	public List <Medicine> getListofMedicines ();
	public List<Medicine> getMedicinesofPharmacist(Integer pharmacist_id);
	public void assignMedicinetoClient (Integer client_id,Integer code) throws Exception;
	public void assignMedicinetoDoctor (Integer doctor_id, Integer code) throws Exception;
	public void deleteMedicinebyCode (Integer medicine_code) throws Exception;
}
