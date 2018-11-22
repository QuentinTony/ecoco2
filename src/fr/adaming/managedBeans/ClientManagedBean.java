package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "clMB")
public class ClientManagedBean implements Serializable {

	// association UML
	@EJB
	private IClientService clService;

	@EJB
	private IProduitService pService;

	private List<Produit> listeProduits;
	private List<Client> listeClients;
	HttpSession maSession;

	private Client client;

	// constructeurs vides
	public ClientManagedBean() {

	}

	// getters setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public List<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	// methode
	@PostConstruct
	public void initClient() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.client = new Client();

	}

	public String isExist() {
		Client clOut = clService.isExist(client);
		if (clOut != null) {
			this.listeProduits = pService.getProductbyClient(clOut);
			maSession.setAttribute("listeProduitsSession", listeProduits);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clSession", clOut);

			return "accueilClient";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Identifiants invalides"));

			return "accueilSite";

		}
	}

	public String addLinkClient() {
		return "addClient";
	}

	public String addClient() {
		Client clOut = clService.addClient(this.client);
		if (clOut != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �t� effectu�"));
			return "accueilSite";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas pu �tre effectu�"));
			return "addClient";
		}

	}

	public String deleteClient() {
		int verif = clService.deleteClient(this.client);
		if (verif != 0) {

			return "ListeAdmin";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression n'a pas pu �tre effectu�e"));
			return "accueilSite";
		}
	}

	public String updateLinkClient() {
		return "updateClient";
	}
	
	public String updateClient(Client cl) {
		Client clOut = clService.getClient(this.client);
		if (clOut != null) {

			return "ListeAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La modification n'a pas pu �tre effectu�e"));
			return "updateClient";

		}
	}

	public String getClient(Client cl) {
		Client clOut = clService.getClient(this.client);
		if (clOut != null) {
			this.client = clOut;
			return "getClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La recherche n'a pas pu �tre effectu�e"));
			return "ListeAdmin";
		}
	}
}