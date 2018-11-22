package fr.adaming.model;

import java.util.List;

public class Panier {

	// atributs

	private List<LigneCommande> listeCommandes;

	private double prixTotal;

	// constructeur

	public Panier() {
		super();
	}

	public Panier(List<LigneCommande> listeCommandes, double prixTotal) {
		super();
		this.listeCommandes = listeCommandes;
		this.prixTotal = prixTotal;
	}

	// getter setter

	public List<LigneCommande> getListeCommandes() {
		return listeCommandes;
	}

	public void setListeCommandes(List<LigneCommande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

}
