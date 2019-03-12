package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;
@Stateful
public class AdminServiceImpl implements IAdminService{

	//transformateion de l'association uml en java
	@EJB
	private IAdminDao aDao;
	
	@Override
	public Admin isExist(Admin aIn) {
		return aDao.isExist(aIn);
	}

}
