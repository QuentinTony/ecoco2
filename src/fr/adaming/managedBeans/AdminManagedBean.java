package fr.adaming.managedBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Admin;
import fr.adaming.model.Client;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "aMB")
public class AdminManagedBean {

	// association UML

	@EJB
	private IAdminService aService;

	@EJB
	private ICategorieService caService;

	// attributs

	private Admin admin;
	HttpSession maSession;
	// constructeur

	public AdminManagedBean() {
		this.admin = new Admin();
	}

	// getter setter

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	@PostConstruct
	public void initAdmin() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = new Admin();

	}

	// méthode

	public String isExist() {

		Admin aOut = aService.isExist(admin);

		if (aOut != null) {

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aSession", aOut);

			return "accueilAdmin";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Identifiants invalides"));

			return "loginAdmin";

		}

	}
	
	public String deconnexion() {
		maSession.removeAttribute("aSession");
		return "accueilSite";
	}

}
