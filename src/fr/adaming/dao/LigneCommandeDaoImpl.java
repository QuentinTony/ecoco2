package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Commande;
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

	@Override
	public List<LigneCommande> getAllLigneCommande(Commande co) {
		
		// requete JPQL pour récupérer la liste des commandes

		String req = "SELECT lc FROM LigneCommande as lc WHERE lc.commande.id=:pIdco";

		// création du query

		Query query = em.createQuery(req);

		// setter les params

		query.setParameter("pIdco", co.getId());

		return query.getResultList();

	}

}
