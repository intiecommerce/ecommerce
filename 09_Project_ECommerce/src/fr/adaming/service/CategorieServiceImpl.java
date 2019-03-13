package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.view.EditableValueHolderAttachedObjectHandler;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService {

	@EJB
	ICategorieDao cDao;

	@Override
	public List<Categorie> getAllCategorie() {

		return cDao.getAllCategorie();
	}

	@Override
	public Categorie addCategorie(Categorie eIn) {
	
		return cDao.addCategorie(eIn);
	}

	@Override
	public int modifCategorie(Categorie eIn) {
		
		return cDao.modifCategorie(eIn);
	}

	@Override
	public int supprCategorie(Categorie eIn) {
		
		return cDao.supprCategorie(eIn);
	}

	@Override
	public Categorie getCategorieById(Categorie eIn) {
	
		return cDao.getCategorieById(eIn);
	}

}
