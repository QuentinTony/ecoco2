package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;


@Entity
@Table(name="categories")
public class Categorie {
	
	//les attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ca")
	private long idCategorie;
	private String nomCategorie;
	private byte[] photo;
	private String description;

	//les attributs ==> les association
	@OneToMany(mappedBy="categorie")
	private Produit produit;
	
	

}
