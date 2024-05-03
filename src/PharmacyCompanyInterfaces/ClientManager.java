package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Client;


public interface ClientManager {
	public void createClient (Client c);
	public List<Client> getListOfClients();
	public Client searchClientById (Integer id);
}
