package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Administrator;

public interface AdministratorManager {
  public void createAdministrator(Administrator a);
  public List<Administrator> getListOfAdministrators();
  public Administrator searchAdministratorById (Integer id);
  public void deleteAdministratorbyId (Integer id) throws Exception;
  public Administrator searchAdministratorByEmail (String email) throws Exception;
  //Tenemos name y email porque puede pasar que un usuario posea los mismos nombres que otro, pero no el mismo correo
  public Administrator searchAdministratorByNameEmail (String name_a,String surname_a, String email) throws Exception;
}