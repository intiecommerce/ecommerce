package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
@Local
public interface ICategorieDao {
	public List<Categorie> getAllCategorie();

	public Categorie addCategorie(Categorie cIn);

	public int modifCategorie(Categorie cIn);

	public int supprCategorie(Categorie cIn);

	public Categorie getCategorieById(Categorie cIn);

}
