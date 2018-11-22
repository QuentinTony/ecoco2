package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "paMB")
public class PanierManagedBean {

	// association UML

	@EJB
	private ILigneCommandeService lcService;
	
	@EJB
	private IProduitService pService;
	
	// attributs

	private Panier panier;

	private Produit produit;
	
	private List<LigneCommande> listeLigneCommandes;
	
	private int quantite;
	
	HttpSession maSession;

	// constructeur

	public PanierManagedBean() {
		
	}
	
	@PostConstruct
	public void initPanier() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Panier pOut = (Panier) maSession.getAttribute("paSession");
		if(pOut!=null) {
			this.panier=pOut;
;
		}else {
			this.panier=new Panier();
			maSession.setAttribute("paSession", panier);
		}
		this.produit = new Produit();
	}

	// getter setter

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	// méthodes
	
	public String addToPanier() {
		
		LigneCommande lc = new LigneCommande(quantite, produit.getPrix()*quantite);
		lc.setProduit(produit);
		List<LigneCommande> liste = panier.getListeCommandes();
		liste.add(lc);
		panier.setListeCommandes(liste);
		
		maSession.setAttribute("paSession", this.panier);
		
		return "afficherProduits";
		
	}

}
