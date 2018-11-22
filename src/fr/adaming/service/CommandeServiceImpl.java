package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateful
public class CommandeServiceImpl implements ICommandeService {

	@PersistenceContext(unitName = "pu_ecoco")
	private EntityManager em;

	@EJB
	private ICommandeDao coDao;

	@Override
	public Commande addCommande(Commande co, Client cl) {

		co.setClient(cl);

		em.persist(co);

		return co;
	}

	@Override
	public int deleteCommande(Commande co, Client cl) {

		Commande coOut = this.getCommande(co, cl);

		if (coOut != null) {
			return coDao.deleteCommande(coOut);
		} else {
			return 0;
		}

	}

	@Override
	public Commande getCommande(Commande co, Client cl) {

		Client clOut = em.find(Client.class, cl.getId());

		if (clOut.getId() == co.getClient().getId()) {

			return coDao.getCommande(co);

		} else {

			return null;
		}
	}

	@Override
	public List<Commande> getAllCommandes(Client cl) {
		
		return coDao.getAllCommandes(cl);
	}

}
