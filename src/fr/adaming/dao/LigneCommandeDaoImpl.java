package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.LigneCommande;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao{

	@PersistenceContext(unitName = "pu_ecoco")
	private EntityManager em;
	
	@Override
	public LigneCommande addLigneCommande(LigneCommande lc) {
		
		em.persist(lc);
		
		return lc;
	}

	@Override
	public int deleteLigneCommande(LigneCommande lc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLigneCommande(LigneCommande lc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LigneCommande getLigneCommande(LigneCommande lc) {
		// TODO Auto-generated method stub
		return null;
	}

}
