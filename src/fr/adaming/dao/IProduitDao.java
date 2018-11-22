package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Local
public interface IProduitDao {
	
	public Produit addProduct(Produit p);
	
	public List<Produit> getProductbyClient(Client cl);

	public int deleteProduct(Produit p);
	
	public Produit getProduit(Produit p);
	
	public int updateProduit(Produit p);
	
	public List<Produit> getProductbyCategory (Categorie ca);
	
}
