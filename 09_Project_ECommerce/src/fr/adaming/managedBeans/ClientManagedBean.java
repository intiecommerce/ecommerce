package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="cliMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	//declaration des attributs
	private Client client;
	
	//transformation
	@EJB
	private IClientService cliService;
	
	@EJB
	private ICategorieService cService;
	
	@EJB
	private IProduitService pService;


	//constructeur
	public ClientManagedBean() {
		this.client =new Client();
	}

	//getter et setter
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	//methode
	
	public String seConnecter() {
		
		// chercher le client
		Client cOut = cliService.isExist(client);

		if (cOut != null) {

			// recuperer les listes de produits et categories
			List<Categorie> cListe=cService.getAllCategorie();
			List<Produit> pListe=pService.getAllProduit();
			
			//mettre les listes dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catSession", cListe);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("proSession", pListe);
			
			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Email incorrect"));
			return "login";
		}
	}
	public String ajouterClient(){
		//appel de la methode service
		Client cAjout=cliService.ajouterClient(client);
		
		if(cAjout.getIdClient()!=0){
	
	
			
			return "accueilClient";
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("L'ajout a échoué"));
			return "creationClient";
		}
	}
	
	
}
