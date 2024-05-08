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
}
