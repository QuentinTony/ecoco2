package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {

	public Produit addProduct(Produit p, Client cl);

	public List<Produit> getProductbyClient(Client cl);
	
	public int deleteProduct(Produit p, Client cl);
	
	public Produit getProduit(Produit p, Client cl);
	
	public int updateProduit(Produit p, Client cl);



}
