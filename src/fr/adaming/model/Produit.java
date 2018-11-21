package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@Table(name = "produits")
public class Produit {

	// les attributs

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_p")
	private long idProduit;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	private boolean selectionne;
	private byte[] photo;

	// les attributs==> les associations
	@ManyToOne
	@JoinColumn(name = "c_id", referencedColumnName = "id_ca")
	private Categorie categorie;
	
	@OneToMany(mappedBy="Produit")
	private LigneCommande ligneCommande;

}
