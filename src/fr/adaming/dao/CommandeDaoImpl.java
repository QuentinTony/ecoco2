package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao {

	@PersistenceContext(unitName = "pu_ecoco")
	private EntityManager em;

	@Override
	public Commande addCommande(Commande co) {

		em.persist(co);

		return co;
	}

	@Override
	public int deleteCommande(Commande co) {

		try {
			em.remove(co);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public Commande getCommande(Commande co) {
		Commande coOut = em.find(Commande.class, co.getId());
		
		return coOut;
	}

	@Override
	public List<Commande> getAllCommandes(Client cl) {
		
		// requete JPQL pour récupérer la liste des commandes

		String req = "SELECT c FROM Commande as c WHERE c.client.id=:pIdco";

		// création du query

		Query query = em.createQuery(req);

		// setter les params

		query.setParameter("pIdco", cl.getId());

		return query.getResultList();
	}

}
