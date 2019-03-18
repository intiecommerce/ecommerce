package fr.adaming.managedBeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import fr.adaming.service.IPanierService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="lcMB")
@RequestScoped
public class LigneCommandeManagedBean {

	//transformation de l'association
	
	private IProduitService proService;
	
}
