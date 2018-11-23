package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao {

	@PersistenceContext(unitName = "pu_ecoco")
	private EntityManager em;

	@Override
	public Produit addProduct(Produit p) {
		em.persist(p);
		return p;

	}

	@Override
	public List<Produit> getProductbyClient(Client cl) {
		String req = "SELECT p FROM Produit p WHERE p.client.id=:pIdcl";
		Query query = em.createQuery(req);
		query.setParameter("pIdcl", cl.getId());
		List<Produit> liste = query.getResultList();
		for(Produit p : liste){
			p.setImage("data:image/png;base64,"+ Base64.encodeBase64String(p.getPhoto()));
	}
	return liste;
	}

	@Override
	public int deleteProduct(Produit p) {
		Produit pOut = em.find(Produit.class, p.getIdProduit());
		if (pOut != null) {
			em.remove(pOut);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Produit getProduit(Produit p) {
		Produit pOut =em.find(Produit.class, p.getIdProduit());
		pOut.setImage("data:image/png;base64,"+ Base64.encodeBase64String(p.getPhoto()));
		return pOut;
	}

	@Override
	public int updateProduit(Produit p) {
		String req="UPDATE Produit p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite, p.photo=:pPhoto WHERE p.idProduit=:pIdProduit";
		Query query=em.createQuery(req);
		query.setParameter("pDesignation", p.getDesignation());
		query.setParameter("pDescription", p.getDescription());
		query.setParameter("pPrix", p.getPrix());
		query.setParameter("pQuantite", p.getQuantite());
		query.setParameter("pPhoto", p.getPhoto());
		query.setParameter("pIdProduit", p.getIdProduit());
		return query.executeUpdate();
	}

	@Override
	public List<Produit> getProductbyCategory(Categorie ca) {
		String req = "SELECT p FROM Produit p WHERE p.categorie.id=:pIdca";
		Query query = em.createQuery(req);
		query.setParameter("pIdca", ca.getIdCategorie());
		return query.getResultList();
	
	}

}
