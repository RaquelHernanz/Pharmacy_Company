package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Client;


public interface ClientManager {
	public void createClient (Client c);
	public List<Client> getListOfClients();
	public Client searchClientById (Integer id);
	public void deleteClientbyId (Integer id) throws Exception;
	public Client searchClientByNameEmail (String name_c,String surname_c,String email) throws Exception;
	public Client searchClientByEmail (String email) throws Exception;
}
