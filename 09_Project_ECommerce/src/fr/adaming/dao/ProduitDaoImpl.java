package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;
import fr.adaming.model.Produit;
@Stateless
public class ProduitDaoImpl implements IProduitDao{

	@PersistenceContext(unitName = "PU_eCommerce")
	private EntityManager em;
	
	@Override
	public List<Produit> getAllProduit() {

		// Requete JPQL

		String req = "SELECT p FROM Produit as p";

		// recuperer un objet de type Query
		Query query = em.createQuery(req);

		return query.getResultList();

	}

	@Override
	public Produit addProduit(Produit cIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifProduit(Produit cIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int supprProduit(Produit cIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit getProduitById(Produit cIn) {
		// TODO Auto-generated method stub
		return null;
	}

}
