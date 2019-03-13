package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;
@Stateful
public class ClientServiceImpl implements IClientService{
	
	//transformateion de l'association uml en java
		@EJB
		private IClientDao cDao;
	
	@Override
	public Client ajouterClient(Client cIn) {
		
		return cDao.ajouterClient(cIn);
	}

	@Override
	public Client isExist(Client cIn) {
		
		return cDao.isExist(cIn);
	}

}
