package fr.adaming.managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "caMB")
public class CategorieManagedBean {

	// transformation et injection de l'asso UML en JAVA
	@EJB
	public ICategorieService caService;

	private Categorie categorie;
	private Admin admin;

	// constructeur vide
	public CategorieManagedBean() {
		this.categorie = new Categorie();
	}

	// getter setter
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	// les méthodes

	public String addLinkCategory() {
		return "addCategory";
	}

	public String addCategory() {
		Categorie caOut = caService.addCategory(this.categorie);
		if (caOut != null) {
			List<Categorie> listeCategories = caService.getAllCategory();
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas pu être effectué"));
			return "addCategory";
		}

	}

	public String deleteCategory() {
		int verif = caService.deleteCategory(this.categorie);
		if (verif != 0) {
			List<Categorie> listeCategories = caService.getAllCategory();
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression n'a pas pu être effectuée"));
			return "accueilAdmin";
		}

	}
	
	public String getCategory() {
		Categorie caOut = caService.getCategory(this.categorie);
		if(caOut!=null) {
			this.categorie=caOut;
			return "getCategory";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche n'a pas pu être effectuée"));
			return "getCategory";
		}
	}
	
	public String updateCategory() {
		int verif = caService.updateCategory(this.categorie);
		return "updateCategory";
	}
}
