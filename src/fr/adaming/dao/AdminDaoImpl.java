package fr.adaming.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;

@Stateful
public class AdminDaoImpl implements IAdminDao {

	@PersistenceContext(unitName = "pu_ecoco")
	private EntityManager em;

	@Override
	public Admin isExist(Admin a) {
		
		// requete jpql
		
		String req = "SELECT a FROM Admin as a WHERE a.mail=:pMail AND a.mdp=:pMdp";
		
		// création du query
		
		Query query = em.createQuery(req);
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());
		
		return (Admin) query.getSingleResult();
	}

}
