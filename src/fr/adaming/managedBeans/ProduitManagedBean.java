package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "pMB")
public class ProduitManagedBean implements Serializable {

	// transformation et injection de l'asso UML en JAVA
	@EJB
	public IProduitService pService;

	private Produit produit;
	private Client client;
	private Categorie categorie;
	HttpSession maSession;

	// constructeur vide
	public ProduitManagedBean() {
		super();
	}

	// getter et setter
	public ProduitManagedBean(Produit produit) {
		super();
		this.produit = produit;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	// methode
	@PostConstruct
	public void initClient() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.client = (Client) maSession.getAttribute("clSession");
		this.produit = new Produit();
		this.categorie = new Categorie();
	}

	public String addLinkProduct() {
		return "addProduct";
	}

	public String addProduct() {
		Produit pOut = pService.addProduct(this.produit, this.client);
		if (pOut != null) {
			List<Produit> listeProduits = pService.getProductbyClient(this.client);
			maSession.setAttribute("listeProduitsSession", listeProduits);
			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas pu être effectué"));
			return "addProduct";
		}

	}

	public String deleteProduct() {
		int verif = pService.deleteProduct(this.produit, this.client);
		if (verif != 0) {
			List<Produit> listeProduits = pService.getProductbyClient(this.client);
			maSession.setAttribute("listeProduitsSession", listeProduits);
			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression n'a pas pu être effectuée"));
			return "accueilClient";
		}

	}

	public String getProduct() {
		Produit pOut = pService.getProduit(this.produit, this.client);
		if (pOut != null) {
			this.produit = pOut;
			return "getProduct";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La recherche n'a pas pu être effectuée"));
			return "accueilClient";
		}

	}

	public String updateLinkProduct() {
		return "updateProduct";
	}

	public String updateProduct() {
		int verif = pService.updateProduit(this.produit, this.client);
		if (verif != 0) {
			List<Produit> listeProduits = pService.getProductbyClient(this.client);
			maSession.setAttribute("listeProduitsSession", listeProduits);
			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La modification n'a pas pu être effectuée"));
			return "updateProduct";
		}

	}

	public String getProductbyCategory() {
		List<Produit> listeProduits = pService.getProductbyCategory(this.categorie);
		if(listeProduits!=null) {
			maSession.setAttribute("listeProduitsSession", listeProduits);
			return "afficherProduits";
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La recherche n'a pas pu être effectuée"));
			return "accueilSite";
			
		}
	}

}
