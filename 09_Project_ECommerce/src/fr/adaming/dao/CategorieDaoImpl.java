package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName = "PU_eCommerce")
	private EntityManager em;

	@Override
	public List<Categorie> getAllCategorie() {

		// Requete JPQL

		String req = "SELECT c FROM Categorie as c";

		// recuperer un objet de type Query
		Query query = em.createQuery(req);

		return query.getResultList();
	}

	@Override
	public Categorie addCategorie(Categorie cIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifCategorie(Categorie cIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int supprCategorie(Categorie cIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie getCategorieById(Categorie cIn) {
		// TODO Auto-generated method stub
		return null;
	}

}
