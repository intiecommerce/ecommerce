package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Admin;
@Local
public interface IAdminDao {
	public Admin isExist(Admin aIn);

}
