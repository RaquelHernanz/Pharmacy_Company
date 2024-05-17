package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Client;


public interface ClientManager 
{
	public void createClient (Client c);
	public List<Client> getListOfClients();
	public Client searchClientById (Integer id);
	public void deleteClientbyId (Integer id) throws Exception;
	public Client searchClientByNameEmail (String name_c,String surname_c,String email) throws Exception;
	public Client searchClientByEmail (String email) throws Exception;
	public void updateName (Integer id, String name) throws Exception;
	public void updateSurname (Integer id, String surname) throws Exception;
	public void updatePhoneNumber (Integer id, Integer phone_number) throws Exception;
	public void updateAddress (Integer id,String address) throws Exception;
	public void updateEmail(Integer id, String email) throws Exception;
}
