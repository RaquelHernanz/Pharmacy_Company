package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Administrator;

public interface AdministratorManager {
  public void createAdministrator(Administrator a);
  public List<Administrator> getListOfAdministrators();
  public Administrator searchAdministratorById (Integer id);
  public void deleteAdministratorbyId (Integer id) throws Exception;
  public Administrator searchAdministratorByEmail (String email) throws Exception;
  public Administrator searchAdministratorByNameEmail (String name_a,String surname_a, String email) throws Exception;
  public void updateName (Integer id, String name) throws Exception;
  public void updateSurname (Integer id, String surname) throws Exception;
  public void updatePhoneNumber (Integer id, Integer phone_number) throws Exception;
}