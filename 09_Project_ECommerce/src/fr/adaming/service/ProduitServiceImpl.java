package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService {

	@EJB
	IProduitDao pDao;

	@Override
	public List<Produit> getAllProduit() {
		return pDao.getAllProduit();
	}

	@Override
	public Produit addProduit(Produit eIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifProduit(Produit eIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int supprProduit(Produit eIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit getProduitById(Produit eIn) {
		// TODO Auto-generated method stub
		return null;
	}

}
