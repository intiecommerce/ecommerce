package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// transformation de l'association UML en java
	@EJB
	private ICategorieService catService;

	private Categorie categorie;

	private HttpSession maSession;

	// constructeur
	public CategorieManagedBean() {
		this.categorie = new Categorie();
	}

	@PostConstruct // cette annotation sert � dire que la m�thode doit etre
	// execut�e apres l'instanciation de l'objet

	public void init() {

		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// getter et setter
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	// methode
	public String ajouterCategorie(){
		//appel de la methode service
		Categorie cAjout=catService.addCategorie(this.categorie);
		
		if(cAjout.getIdCategorie()!=0){
			
			//recuperer la nouvelle liste
			List<Categorie> catliste = catService.getAllCategorie();
			
			//liste dans la session
			maSession.setAttribute("catSession", catliste);
			
			return "accueilAdmin";
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("L'ajout a �chou�"));
			return "ajouterCategorie";
		}
	}
	
	public String modifCategorie(){
		// appel de la m�thode
		
		int catModif=catService.modifCategorie(categorie);
		
		if(catModif!=0){
			
			//recuperer la nouvelle liste
			List<Categorie> liste = catService.getAllCategorie();
			
			//liste dans la session
			maSession.setAttribute("catSession", liste);
			return "accueilAdmin";
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La modification a �chou�e"));
			return "modifierCategorie";
		}
		
	}
	
	public String supprCategorie(){
	// appel de la m�thode
		
		int cModif=catService.supprCategorie(categorie);
		
		if(cModif!=0){
			
			//recuperer la nouvelle liste
			List<Categorie> liste = catService.getAllCategorie();
			
			//liste dans la session
			maSession.setAttribute("catSession", liste);
			return "accueilAdmin";
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression a �chou�e"));
			return "supprimerCategorie";
		}
		
	}
	
	public String rechercherCategorie(){
		
		//appel de la methode
		Categorie catRech=catService.getCategorieById(categorie);
		if(catRech!=null){
			this.categorie=catRech;
			return "chercherCategorie";
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La recherche a �chou�e"));
			return "chercherCategorie";
		}
		
	}
}
