package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

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

	private int quantite;

	HttpSession maSession;

	// constructeur

	public PanierManagedBean() {

	}

	@PostConstruct
	public void initPanier() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Panier pOut = (Panier) maSession.getAttribute("paSession");
		if (pOut != null) {
			this.panier = pOut;
			
		} else {
			
			List<LigneCommande> liste = new ArrayList<LigneCommande>();
			
			this.panier = new Panier();
			panier.setListeLigneCommandes(liste);
			
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

	public void vQuantite(FacesContext context, UIComponent composant, Object valeur) throws ValidatorException {

		String saisie = Integer.toString(quantite);
		int longueur = saisie.length();
		String nb = "01234565789";

		for (int i = 0; i < longueur; i++) {
			String test = Character.toString(saisie.charAt(i));

			if (!nb.contains(test)) {
				throw new ValidatorException(new FacesMessage("La quantité est un nombre !"));
			} 
		}

	}

	public String addToPanier() {

		LigneCommande lc = new LigneCommande(quantite, produit.getPrix() * quantite);
		lc.setProduit(produit);

		List<LigneCommande> liste = panier.getListeLigneCommandes();
		liste.add(lc);
		panier.setListeLigneCommandes(liste);
		panier.setPrixTotal(panier.getPrixTotal()+lc.getPrix());
		
		maSession.setAttribute("paSession", this.panier);

		return "afficherProduits";

	}

}
