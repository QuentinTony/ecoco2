package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
@Table(name = "categories")
public class Categorie {

	// les attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ca")
	private long idCategorie;
	private String nomCategorie;
	@Lob
	private byte[] photo;
	@Transient
	private String image;
	
	private String description;

	// les attributs ==> les association
	@OneToMany(mappedBy = "categorie")
	private List<Produit> listeProduits;

	// les constructeurs
	// vide
	public Categorie() {
		super();

	}

	// -id
	public Categorie(String nomCategorie,  String description) {
		super();
		this.nomCategorie = nomCategorie;
		this.description = description;
	}

	// +id
	public Categorie(long idCategorie, String nomCategorie, String description) {
		super();
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.description = description;
	}

	// getters et setters
	public long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// ToString
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", photo="
				+ Arrays.toString(photo) + ", description=" + description + "]";
	}

}
