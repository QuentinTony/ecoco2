package fr.adaming.model;

import java.util.List;

public class Panier {

	// atributs
	
    private List<LigneCommande> listeCommandes;

    // constructeur
    
	public Panier(List<LigneCommande> listeCommandes) {
		super();
		this.listeCommandes = listeCommandes;
	}

	public Panier() {
		super();
	}

	// getter setter
	
	public List<LigneCommande> getListeCommandes() {
		return listeCommandes;
	}

	public void setListeCommandes(List<LigneCommande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}
	
    
    
}
