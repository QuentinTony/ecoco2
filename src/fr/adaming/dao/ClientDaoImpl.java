package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao {

	@PersistenceContext(unitName = "pu_ecoco")
	private EntityManager em;

	@Override
	public Client addClient(Client cl) {

		em.persist(cl);

		return cl; // il le recup après le persist, donc avec l'id
	}

	@Override
	public int deleteClient(Client cl) {

		try {
			em.remove(cl);
			return 1;
		} catch (Exception ex) {
			return 0;
		}

	}

	@Override
	public int updateClient(Client cl) {
		try {
			em.merge(cl);
			return 1;
		} catch (Exception ex) {
			return 0;
		}

	}

	@Override
	public Client getClient(Client cl) {

		Client clOut = em.find(Client.class, cl.getId());

		return clOut;
	}

	@Override
	public Client isExist(Client cl) {
		// requete jpql

		String req = "SELECT cl FROM Client as cl WHERE cl.mail=:pMail AND cl.mdp=:pMdp";

		// création du query

		Query query = em.createQuery(req);
		query.setParameter("pMail", cl.getMail());
		query.setParameter("pMdp", cl.getMdp());
		return (Client) query.getSingleResult();
	}

}
