package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IPanierService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "aMB")
@RequestScoped
public class AdminManagedBean implements Serializable{

	// Declaration des attributs
	private Admin admin;
	
	// transformation de l'association UML en java
	@EJB
	private IAdminService aService;

	@EJB
	private ICategorieService cService;
	
	@EJB
	private IProduitService pService;

	// Constructeur
	public AdminManagedBean() {
		this.admin = new Admin();
	}

	// getter et setter
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	// methode metier
	public String seConnecter() {
		// chercher le formateur par son mail et son mdp
		Admin aOut = aService.isExist(admin);

		if (aOut != null) {

			
			// recuperer les listes de produits et categories
		
			List<Categorie> cListe=cService.getAllCategorie();
			List<Produit> pListe=pService.getAllProduit();
			
			//mettre les listes dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catSession", cListe);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("proSession", pListe);

			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Identifiant et/ou mot de passe incorrect"));
			return "login";
		}
	}

}
