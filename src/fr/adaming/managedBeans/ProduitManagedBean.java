package fr.adaming.managedBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="pMB")
public class ProduitManagedBean {
	
	//transformation et injection de l'asso UML en JAVA
	@EJB
	public IProduitService pService;
	
	private Produit produit;
	private Client client;
	private Categorie categorie;
	HttpSession maSession;
	
	//constructeur vide
	public ProduitManagedBean() {
		super();
	}

	//getter et setter
	public ProduitManagedBean(Produit produit) {
		super();
		this.produit = produit;
	}
	
	//methode
	@PostConstruct
	public void initClient() {
		this.maSession =(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.client=(Client) maSession.getAttribute("clSession");
		this.produit=new Produit();
	}
	
	
	public String addLinkProduct() {
		return "addProduct";
	}
	
	
	
	
	
	

}
