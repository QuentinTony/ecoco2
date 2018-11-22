package fr.adaming.managedBeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
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

	// méthode

	public String isExist() {

		Admin aOut = aService.isExist(admin);

		if (aOut != null) {

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aSession", aOut);

			return "accueilAdmin";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Identifiants invalides"));

			return "accueilSite";

		}

	}

}
