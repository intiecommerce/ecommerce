package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;
import fr.adaming.model.Client;
@Stateless
public class ClientDaoImpl implements IClientDao{
	@PersistenceContext(unitName="PU_eCommerce")
	private EntityManager em;
	@Override
	public Client isExist(Client cIn) {
		//requete JPQL		on ne travail pas avec la base de donné avec jpql, c'est avec les objets
		String req="SELECT c FROM Client as c WHERE c.email=:pMail";
		
		//recuperer un objet de type Query
		Query query=em.createQuery(req);
		
		//passage des parametres
		query.setParameter("pMail", cIn.getEmail());

		try{
		return (Client) query.getSingleResult();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	
	}
	@Override
	public Client ajouterClient(Client cIn) {
		em.persist(cIn);
		return cIn;
	}
	

}
