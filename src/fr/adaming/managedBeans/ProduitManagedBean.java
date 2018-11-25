package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

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
	private UploadedFile file;
	private String saisie;
	private double prix1;
	private double prix2;
	private List<Produit> listeProduit;

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

	public double getPrix1() {
		return prix1;
	}

	public void setPrix1(double prix1) {
		this.prix1 = prix1;
	}

	public double getPrix2() {
		return prix2;
	}

	public void setPrix2(double prix2) {
		this.prix2 = prix2;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getSaisie() {
		return saisie;
	}

	public void setSaisie(String saisie) {
		this.saisie = saisie;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	// methode
	@PostConstruct
	public void initClient() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.client = (Client) maSession.getAttribute("clSession");
		this.produit = new Produit();
		this.categorie = new Categorie();
		
	}

	public String afficherProduit() {

		maSession.setAttribute("prSession", produit);

		return "afficherOneProduit";
	}

	public String addLinkProduct() {
		return "addProduct";
	}

	public String addProduct() {
		this.produit.setPhoto(file.getContents());
		Produit pOut = pService.addProduct(this.produit, this.client, this.categorie);
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
		this.produit.setPhoto(file.getContents());

		int verif = pService.updateProduit(this.produit, this.client, this.categorie);
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
		if (listeProduits != null) {
			maSession.setAttribute("listeProduitsSession", listeProduits);
			maSession.setAttribute("caSession", categorie);
			return "afficherProduits";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La recherche n'a pas pu être effectuée"));
			return "accueilSite";

		}
	}

	public String getProductbySaisie() {
		List<Produit> listeProduits = pService.getProductbyString(this.saisie);
		if (listeProduits != null) {
			maSession.setAttribute("listeProduitsSession", listeProduits);
			return "afficherProduitsSaisie";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche n'a pas aboutie"));
			return "accueilSite";

		}
	}

	public String getProductbyPrix() {
		if (prix1 <= prix2) {
			List<Produit> listeProduits = pService.getProductbyDouble(prix1, prix2);
			if (listeProduits != null) {
				maSession.setAttribute("listeProduitsSession", listeProduits);
				return "afficherProduitsPrix";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche n'a pas aboutie"));
				return "accueilSite";

			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Le prix minimum doit être inférieur ou égal au prix maximum"));
			return "accueilSite";
		}

	}

	public String getProductbyCatAndSaisie() {
		categorie = (Categorie) maSession.getAttribute("caSession");
		List<Produit> listeProduits = pService.getProductbyCatAndString(this.saisie, categorie);
		if (listeProduits != null) {
			maSession.setAttribute("listeProduitsSession", listeProduits);
			return "afficherProduitsSaisieCat";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche n'a pas aboutie"));
			return "accueilSite";

		}
	}

	public String getProductbyClAndSaisie() {
		client = (Client) maSession.getAttribute("clSession");
		List<Produit> listeProduits = pService.getProductbyClAndString(this.saisie, client);
		if (listeProduits != null) {
			maSession.setAttribute("listeProduitsSession", listeProduits);
			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche n'a pas aboutie"));
			return "accueilClient";

		}
	}

	public String espaceclient() {
		return "accueilClient";
	}
	
	public String getAllProducts() {
		List<Produit> listeOut=pService.getAllProducts();
		if(listeOut!=null) {
			this.listeProduit=listeOut;
						return "getAllProducts";
		}else {FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche n'a pas aboutie"));
		return "accueilSite";
			
		}
	}

}
