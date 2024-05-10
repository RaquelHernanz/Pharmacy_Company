package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Administrator;

public interface AdministratorManager {
  public void createAdministrator(Administrator a);
  public List<Administrator> getListOfAdministrators();
  public Administrator searchAdministratorById (Integer id);
  public void deleteAdministratorbyId (Integer id) throws Exception;
  public Administrator searchAdministratorByName (String name_a,String surname_a) throws Exception;
}