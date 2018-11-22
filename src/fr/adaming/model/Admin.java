package fr.adaming.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="admins")
@DiscriminatorValue(value="oui")
public class Admin extends Client{

	// attributs

	private String num;

	public Admin(String nom, String adresse, String mail, String mdp, String tel, String num) {
		super(nom, adresse, mail, mdp, tel);
		this.num = num;
	}

	public Admin(long id, String nom, String adresse, String mail, String mdp, String tel, String num) {
		super(id, nom, adresse, mail, mdp, tel);
		this.num = num;
	}

	public Admin() {
		super();
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	
	
}
