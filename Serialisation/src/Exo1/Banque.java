package Exo1;

/*
 * @author : Madjid KETFI
 */

import java.util.Vector;

/**
 * @author Madjid KETFI
 */
public class Banque
{
	private String			nom		= null;
	private static Banque	banque	= null;
	private Vector<Compte>	comptes	= null;

	// Constructeur sans arguments
	// N�cessaire pour la s�rialisation XML
	public Banque()
	{}

	public Banque(String nom)
	{
		this.nom = nom;
		this.comptes = new Vector<Compte>();
	}

	public static Banque creerBanque(String nom)
	{
		if (banque == null)
			banque = new Banque(nom);

		return banque;
	}

	/**
	 * Ajouter un compte � la banque
	 * 
	 * @param code
	 * @param nom
	 * @return
	 */
	public void ajouterCompte(int code, String nom)
	{
		Compte nc = new Compte(code, nom);
		comptes.add(nc);
	}

	/**
	 * Consulter un compte
	 * 
	 * @param code
	 *            du compte
	 * @return
	 */
	public int consulter(int code)
	{
		Compte cpt = rechercherCompte(code);
		if (cpt != null)
			return cpt.consulter();
		return -1; // compte introuvable
	}

	/**
	 * Cr�diter un compte
	 * 
	 * @param code
	 * @param somme
	 * @return
	 */
	public boolean crediter(int code, int somme)
	{
		Compte cpt = rechercherCompte(code);
		if (cpt != null)
			return cpt.crediter(somme);
		return false; // compte introuvable
	}

	/**
	 * D�biter une somme d'un compte
	 * 
	 * @param code
	 * @param somme
	 * @return
	 */
	public boolean debiter(int code, int somme)
	{
		Compte cpt = rechercherCompte(code);
		if (cpt != null)
			return cpt.debiter(somme);
		return false; // compte introuvable
	}

	/**
	 * Rechercher un compte particulier
	 * 
	 * @param code
	 * @return
	 */
	private Compte rechercherCompte(int code)
	{
		for (int i = 0; i < comptes.size(); i++)
		{
			Compte cpt = comptes.get(i);
			if (cpt.getCode() == code)
				return cpt;
		}
		return null;
	}
}

