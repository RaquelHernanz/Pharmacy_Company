package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Medicine;


public interface MedicineManager 
{
	public void addMedicine (Medicine m);
	public List <Medicine> getListofMedicines ();
	public List<Medicine> getMedicinesofPharmacist(Integer pharmacist_id);
	public void assignMedicinetoClient (Integer client_id,Integer code, Float bill,Integer quantity) throws Exception;
	public void assignMedicinetoDoctor (Integer doctor_id, Integer code, Float bill,Integer quantity) throws Exception;
	public void deleteMedicinebyCode (Integer medicine_code) throws Exception;
	public Medicine searchMedicineByName (String name)throws Exception;
	public void updateStock (Integer quantity, Integer medicine_code) throws Exception;
	public Medicine searchMedicineByCode (Integer code)throws Exception;
	public List <Medicine> getListofMedicinesPurchasedClient (Integer client_id) throws Exception;
	public Boolean checkListofMedicinesPharmacist (Integer pharmacist_id, String name_med) throws Exception;
	public List <Medicine> getListofMedicinesPurchasedDoctor (Integer doctor_id) throws Exception;
;}
