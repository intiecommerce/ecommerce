package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		em.persist(cIn);
		return cIn;
	}

	@Override
	public int modifCategorie(Categorie cIn) {

		String req = "UPDATE Categorie as c SET c.nomCategorie=:pCat, c.description=:pDes WHERE idCategorie=:pId";
		Query queryJPQL = em.createQuery(req);

		// passage des parametres
		queryJPQL.setParameter("pCat", cIn.getNomCategorie());
		queryJPQL.setParameter("pDes", cIn.getDescription());
		queryJPQL.setParameter("pId", cIn.getIdCategorie());

		return queryJPQL.executeUpdate();
	}

	@Override
	public int supprCategorie(Categorie cIn) {

		String req = "Delete Categorie as c WHERE idCategorie=:pId";

		Query queryJPQL = em.createQuery(req);

		// passage des parametres
		queryJPQL.setParameter("pId", cIn.getIdCategorie());

		return queryJPQL.executeUpdate();

	}

	@Override
	public Categorie getCategorieById(Categorie cIn) {
		Categorie cOut = em.find(Categorie.class, cIn.getIdCategorie());
		return cOut;
	}

}
