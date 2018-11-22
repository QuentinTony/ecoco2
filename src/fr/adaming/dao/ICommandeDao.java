package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Local
public interface ICommandeDao {

	public Commande addCommande(Commande co);
	
	public int deleteCommande(Commande co);
	
	public Commande getCommande(Commande co);
	
	public List<Commande> getAllCommandes(Client cl);
	
}
