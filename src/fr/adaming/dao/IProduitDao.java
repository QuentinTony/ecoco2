package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local
public interface IProduitDao {
	
	public Produit addProduct(Produit p);

}
