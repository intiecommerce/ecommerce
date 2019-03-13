package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Produit;
@Stateless
public class ProduitDaoImpl implements IProduitDao{

	
	@PersistenceContext(unitName = "PU_eCommerce")
	private EntityManager em;
	
	
	public List<Produit> recPro(){
		
		//requete JPQL
		String req="SELECT p FROM Produit as p";
		
		//Recuperer un objet de type query
		Query query = em.createQuery(req);
		
		List<Produit> listePro = query.getResultList();
		
		for(Produit p:listePro){
			p.setImage("data:image/png;base64,"+Base64.encodeBase64String(p.getPhoto()));
		}
		return listePro;
	}

	
	@Override
	public List<Produit> getAllProduit() {

		// Requete JPQL

		String req = "SELECT p FROM Produit as p";

		// recuperer un objet de type Query
		Query query = em.createQuery(req);

		return query.getResultList();

	}

	@Override
	public Produit addProduit(Produit pIn) {
		em.persist(pIn);
		return pIn;
	}

	@Override
	public int modifProduit(Produit pIn) {
		String req = "UPDATE Produit as p SET p.designation=:pDesi, p.description=:pDesc, p.prix=:pPri, p.quantite=:pQua, p.selectionne=:pSel WHERE idProduit=:pId";
		Query queryJPQL = em.createQuery(req);

		// passage des parametres
		queryJPQL.setParameter("pDesi", pIn.getDesignation());
		queryJPQL.setParameter("pDesc", pIn.getDescription());
		queryJPQL.setParameter("pPri", pIn.getPrix());
		queryJPQL.setParameter("pQua", pIn.getQuantite());
		queryJPQL.setParameter("pSel", pIn.isSelectionne());
		queryJPQL.setParameter("pId", pIn.getIdProduit());

		return queryJPQL.executeUpdate();
	}

	@Override
	public int supprProduit(Produit pIn) {
		String req = "Delete Produit as p WHERE idProduit=:pId";

		Query queryJPQL = em.createQuery(req);

		// passage des parametres
		queryJPQL.setParameter("pId", pIn.getIdProduit());

		return queryJPQL.executeUpdate();
	}

	@Override
	public Produit getProduitById(Produit pIn) {
		Produit pOut = em.find(Produit.class, pIn.getIdProduit());
		return pOut;
	}

}
