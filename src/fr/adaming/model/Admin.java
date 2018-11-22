package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="admins")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Admin extends Client{

	// attributs

	private String num;
	
}
