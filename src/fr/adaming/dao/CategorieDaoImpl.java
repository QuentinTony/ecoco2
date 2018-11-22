package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName = "pu_ecoco")
	private EntityManager em;

	@Override
	public Categorie addCategory(Categorie ca) {
		em.persist(ca);
		return ca;

	}

	@Override
	public List<Categorie> getAllCategory() {
		String req = "SELECT ca FROM Categorie ca ";
		Query query = em.createQuery(req);
		return query.getResultList();
	}

	@Override
	public int deleteCategory(Categorie ca) {
		Categorie caOut = em.find(Categorie.class, ca.getIdCategorie());
		if (caOut != null) {
			em.remove(caOut);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Categorie getCategory(Categorie ca) {
		return em.find(Categorie.class, ca.getIdCategorie());
	}

	@Override
	public int updateCategory(Categorie ca) {
		String req = "UPDATE Categorie ca SET ca.nomCategorie=:pNomCategorie, ca.photo=:pPhoto, ca.description=:pDescription WHERE ca.idCategorie=:pIdCategorie";
		Query query = em.createQuery(req);
		query.setParameter("pNomCategorie", ca.getNomCategorie());
		query.setParameter("pPhoto", ca.getPhoto());
		query.setParameter("pDescription", ca.getDescription());
		query.setParameter("pIdCategorie", ca.getIdCategorie());
		return query.executeUpdate();
	}

}
