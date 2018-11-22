package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService {
	
	//transformation de l'association UML en Java
	@EJB
	public IProduitDao pDao;

	@Override
	public Produit addProduct(Produit p, Client cl) {
		p.setClient(cl);
		return pDao.addProduct(p);
	}

}
