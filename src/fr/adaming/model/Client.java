package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "admin")
@DiscriminatorValue(value = "non")
public class Client implements Serializable {

	// attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cl")
	protected long id;
	protected String nom;
	protected String adresse;
	protected String mail;
	protected String mdp;
	protected String tel;

	// association onetomany avec commande
	@OneToMany(mappedBy = "client", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	protected List<Commande> listeCommandes;

	@OneToMany(mappedBy = "client", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	protected List<Produit> listeProduits;

	// constructeurs

	public Client() {

	}

	public Client(String nom, String adresse, String mail, String mdp, String tel) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.mail = mail;
		this.mdp = mdp;
		this.tel = tel;
	}

	public Client(long id, String nom, String adresse, String mail, String mdp, String tel) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.mail = mail;
		this.mdp = mdp;
		this.tel = tel;
	}

	// setter getter

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Commande> getListeCommandes() {
		return listeCommandes;
	}

	public void setListeCommandes(List<Commande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
