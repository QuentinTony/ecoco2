package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {
	
	public Produit addProduct(Produit p, Client cl);

}
