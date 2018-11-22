package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
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
	private byte[] photo;
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
	public Categorie(String nomCategorie, byte[] photo, String description, List<Produit> listeProduits) {
		super();
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
		this.listeProduits = listeProduits;
	}

	// +id
	public Categorie(long idCategorie, String nomCategorie, byte[] photo, String description,
			List<Produit> listeProduits) {
		super();
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
		this.listeProduits = listeProduits;
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

	// ToString
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", photo="
				+ Arrays.toString(photo) + ", description=" + description + ", listeProduits=" + listeProduits + "]";
	}

}
