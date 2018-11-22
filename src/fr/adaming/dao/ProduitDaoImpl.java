package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao{
	
	@PersistenceContext(unitName="pu_ecoco")
	private EntityManager em;
	
	@Override
	public Produit addProduct(Produit p) {
		em.persist(p);
		return p;
		
	}

}
