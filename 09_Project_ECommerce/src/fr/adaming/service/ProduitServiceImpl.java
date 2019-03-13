package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
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
	public Produit addProduit(Produit pIn) {
		
		return pDao.addProduit(pIn);
	}

	@Override
	public int modifProduit(Produit pIn) {
		
		return pDao.modifProduit(pIn);
	}

	@Override
	public int supprProduit(Produit pIn) {
	
		return pDao.supprProduit(pIn);
	}

	@Override
	public Produit getProduitById(Produit pIn) {
		
		return pDao.getProduitById(pIn);
	}

	@Override
	public List<Produit> chercherProduitParCategorie(Categorie cIn) {
		
		return pDao.chercherProduitParCategorie(cIn);
	}

}
