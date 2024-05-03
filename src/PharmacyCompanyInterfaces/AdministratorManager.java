package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Administrator;

public interface AdministratorManager {
  public void createAdministrator(Administrator a);
  public List<Administrator> getListOfAdministrators();
  public Administrator searchAdministratorById (Integer id);
}