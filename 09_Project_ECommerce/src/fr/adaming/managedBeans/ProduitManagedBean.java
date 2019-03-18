package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="proMB")
@RequestScoped
public class ProduitManagedBean implements Serializable{
	// transformation de l'association UML en java
	@EJB
	private IProduitService proService;

	private Produit produit;

	private HttpSession maSession;

	private UploadedFile image;
	
	private boolean listeVisible=false;
	private boolean tableVisible=false;
	
	// constructeur
	public ProduitManagedBean() {
		this.produit = new Produit();
		this.produit.setCategorie(new Categorie());
		this.produit.getCategorie().setListeProduits(new ArrayList<Produit>());
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

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	public boolean isListeVisible() {
		return listeVisible;
	}

	public void setListeVisible(boolean listeVisible) {
		this.listeVisible = listeVisible;
	}

	public boolean isTableVisible() {
		return tableVisible;
	}

	public void setTableVisible(boolean tableVisible) {
		this.tableVisible = tableVisible;
	}

	// methode
	public String ajouterProduit(){
		
		if(this.image != null){
			this.produit.setPhoto(this.image.getContents());
			
		}
		
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
	
	public String chercherProduitParCategorie(){
		
		//appel de la methode
		List<Produit> listeProduit = proService.chercherProduitParCategorie(produit.getCategorie());
		
		if(listeProduit !=null){
			
			listeVisible=true;
			maSession.setAttribute("proSession", listeProduit);
			return "accueilClient";
		}else{
			listeVisible=false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Echoué"));
			return "accueilClient";
		}
	}
	
	public String afficherProduit(){
		
		List<Produit> listeProduit = proService.afficherProduit(produit);
		
		if(listeProduit !=null){
			
			tableVisible=true;
			maSession.setAttribute("proSession", listeProduit);
			return "accueilClient";
		}else{
			tableVisible=false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Echoué"));
			return "accueilClient";
		}
	
	}
	
	
}
