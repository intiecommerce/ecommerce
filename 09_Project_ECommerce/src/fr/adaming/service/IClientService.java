package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Admin;
import fr.adaming.model.Client;
@Local
public interface IClientService {

	public Client ajouterClient(Client cIn);
	public Client isExist(Client cIn);
}
