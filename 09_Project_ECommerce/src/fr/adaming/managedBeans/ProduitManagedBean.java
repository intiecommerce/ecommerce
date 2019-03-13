package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="proMB")
@RequestScoped
public class ProduitManagedBean {
	// transformation de l'association UML en java
	@EJB
	private IProduitService proService;

	private Produit produit;

	private HttpSession maSession;

	// constructeur
	public ProduitManagedBean() {
		this.produit = new Produit();
	}

	@PostConstruct // cette annotation sert à dire que la méthode doit etre
	// executée apres l'instanciation de l'objet

	public void init() {

		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// getter et setter
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	// methode
	public String ajouterProduit(){
		//appel de la methode service
		Produit pAjout=proService.addProduit(this.produit);
		
		if(pAjout.getIdProduit()!=0){
			
			//recuperer la nouvelle liste
			List<Produit> catliste = proService.getAllProduit();
			
			//liste dans la session
			maSession.setAttribute("proSession", catliste);
			
			return "accueilAdmin";
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("L'ajout a échoué"));
			return "ajouterProduit";
		}
	}
	
	public String modifProduit(){
		// appel de la méthode
		
		int proModif=proService.modifProduit(produit);
		
		if(proModif!=0){
			
			//recuperer la nouvelle liste
			List<Produit> liste = proService.getAllProduit();
			
			//liste dans la session
			maSession.setAttribute("proSession", liste);
			return "accueilAdmin";
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La modification a échouée"));
			return "modifierProduit";
		}
		
	}
	
	public String supprProduit(){
	// appel de la méthode
		
		int pModif=proService.supprProduit(produit);
		
		if(pModif!=0){
			
			//recuperer la nouvelle liste
			List<Produit> liste = proService.getAllProduit();
			
			//liste dans la session
			maSession.setAttribute("proSession", liste);
			return "accueilAdmin";
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression a échouée"));
			return "supprimerProduit";
		}
		
	}
	
	public String rechercherProduit(){
		
		//appel de la methode
		Produit proRech=proService.getProduitById(produit);
		if(proRech!=null){
			this.produit=proRech;
			return "chercherProduit";
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La recherche a échouée"));
			return "chercherProduit";
		}
		
	}
}
