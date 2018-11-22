package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService {

	//transformation de l'association UML en Java
		@EJB
		public ICategorieDao caDao;
	
	
	@Override
	public Categorie addCategory(Categorie ca) {
		return caDao.addCategory(ca);
	}

	@Override
	public List<Categorie> getAllCategory() {
		return caDao.getAllCategory();
	}

	@Override
	public int deleteCategory(Categorie ca) {
		return caDao.deleteCategory(ca);
	}

	@Override
	public Categorie getCategory(Categorie ca) {
		return caDao.getCategory(ca);
	}

	@Override
	public int updateCategory(Categorie ca) {
		
		return caDao.updateCategory(ca);
	}

}
