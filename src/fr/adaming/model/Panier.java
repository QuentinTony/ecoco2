package fr.adaming.model;

import java.util.List;

public class Panier {

	// atributs

	private List<LigneCommande> listeLigneCommandes;

	private double prixTotal;

	// constructeur

	public Panier() {
		super();
	}

	public Panier(List<LigneCommande> listeCommandes, double prixTotal) {
		super();
		this.listeLigneCommandes = listeCommandes;
		this.prixTotal = prixTotal;
	}

	// getter setter

	public List<LigneCommande> getListeLigneCommandes() {
		return listeLigneCommandes;
	}

	public void setListeLigneCommandes(List<LigneCommande> listeCommandes) {
		this.listeLigneCommandes = listeCommandes;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

}
