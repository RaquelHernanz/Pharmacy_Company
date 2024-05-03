package interfaces_Package;

import java.util.List;

import pojos_Package.Administrator;
import pojos_Package.Client;


public interface ClientManager {
	public void createClient (Client c);
	public List<Client> getListOfClients();
	public Client searchClientById (Integer id);
}
