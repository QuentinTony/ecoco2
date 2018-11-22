package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;

@Local
public interface ICategorieDao {
	
	public Categorie addCategory(Categorie ca);
	
	public List<Categorie> getAllCategory();

	public int deleteCategory(Categorie ca);
	
	public Categorie getCategory(Categorie ca);
	
	public int updateCategory(Categorie ca);
	

}
