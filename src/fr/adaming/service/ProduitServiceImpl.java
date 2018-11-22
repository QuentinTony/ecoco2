package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService {

	// transformation de l'association UML en Java
	@EJB
	public IProduitDao pDao;

	@Override
	public Produit addProduct(Produit p, Client cl) {
		p.setClient(cl);
		return pDao.addProduct(p);
	}

	@Override
	public List<Produit> getProductbyClient(Client cl) {
		return pDao.getProductbyClient(cl);
	}

	@Override
	public int deleteProduct(Produit p, Client cl) {
		p.setClient(cl);
		return pDao.deleteProduct(p);
	}

	@Override
	public Produit getProduit(Produit p, Client cl) {
		p.setClient(cl);
		return pDao.getProduit(p);
	}

	@Override
	public int updateProduit(Produit p, Client cl) {
		p.setClient(cl);
		return pDao.updateProduit(p);
	}

	@Override
	public List<Produit> getProductbyCategory(Categorie ca) {
		return pDao.getProductbyCategory(ca);
	}

	@Override
	public Produit getProduit(Produit p) {
		
		return pDao.getProduit(p);
	}

}
