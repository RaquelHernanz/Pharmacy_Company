package interfaces_Package;

import java.util.List;

import pojos_Package.Administrator;
import pojos_Package.Doctor;

public interface DoctorManager 
{
	public void createDoctor (Doctor d);
	public List <Doctor> getListOfDoctors ();
	public Doctor searchDoctorById (Integer id);
}
