package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;

@Stateless
public class AdminDaoImpl implements IAdminDao{

	@PersistenceContext(unitName = "PU_eCommerce") // cette annotation permet
	// d'injecter un em intancié par le
	// contenur EJB

private EntityManager em;
	
	@Override
	public Admin isExist(Admin aIn) {
		//requete JPQL		on ne travail pas avec la base de donné avec jpql, c'est avec les objets
		String req="SELECT a FROM Admin as a WHERE a.mail=:pMail and a.mdp=:pMdp";
		
		//recuperer un objet de type Query
		Query query=em.createQuery(req);
		
		//passage des parametres
		query.setParameter("pMail", aIn.getMail());
		query.setParameter("pMdp", aIn.getMdp());
		
		try{
		return (Admin) query.getSingleResult();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	
		

}
}
