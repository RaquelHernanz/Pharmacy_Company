package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Doctor;

public interface DoctorManager 
{
	public void createDoctor (Doctor d);
	public List <Doctor> getListOfDoctors ();
	public Doctor searchDoctorById (Integer id);
	public void deleteDoctorbyId (Integer id) throws Exception;
	public Doctor searchDoctorByEmail (String email) throws Exception;
	public Doctor searchDoctorByNameEmail (String name_d,String surname_d,String email_d) throws Exception;
	public void updateName (Integer id, String name) throws Exception;
	public void updateSurname (Integer id, String surname) throws Exception;
	public void updatePhoneNumber (Integer id, Integer phone_number) throws Exception;
	public void updateAddress (Integer id,String address) throws Exception;
	public void updateEmail(Integer id, String email) throws Exception;
}
