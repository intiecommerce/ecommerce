package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifCategorie(Categorie eIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int supprCategorie(Categorie eIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie getCategorieById(Categorie eIn) {
		// TODO Auto-generated method stub
		return null;
	}

}
