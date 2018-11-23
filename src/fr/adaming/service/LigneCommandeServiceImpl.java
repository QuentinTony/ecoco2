package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService{

	// association UML
	@EJB
	private ILigneCommandeDao lcDao;
	
	@Override
	public LigneCommande addLigneCommande(LigneCommande lc, Commande co) {
		lc.setCommande(co);
		return lcDao.addLigneCommande(lc);
	}

	@Override
	public int deleteLigneCommande(LigneCommande lc, Commande co, Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLigneCommande(LigneCommande lc, Commande co, Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LigneCommande getLigneCommande(LigneCommande lc, Commande co, Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
