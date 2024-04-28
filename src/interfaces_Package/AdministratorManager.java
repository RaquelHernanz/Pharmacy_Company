package interfaces_Package;

import java.util.List;
import pojos_Package.Administrator;

public interface AdministratorManager {
  public void createAdministrator(Administrator a);
  public List<Administrator> getListOfAdministrators();
}