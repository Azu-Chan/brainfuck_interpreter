package brainfuck.parser;

/**
 * OptionArg est la classe servant � stocker chaque option de la
 * ligne de commande dans un objet suivi de son param�tre s'il
 * existe.
 * Classe interne pour chaque option, contient les propri�t�s
 * de chacune
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class Option {	
	private String nom;
	private boolean obligatoire;
	private boolean hasArg;
	private String argument = null;
	private boolean present = false;
	
	/**
	 * Constructeur d'option
	 * 
	 * @param nom
	 * @param obligatoire
	 * @param hasArg
	 */
	public Option(String nom, boolean obligatoire, boolean hasArg){
		this.nom = nom;
		this.obligatoire = obligatoire;
		this.hasArg = hasArg;
	}
	
	/**
	 * getter du nom
	 * 
	 * @return nom de l'option
	 */
	public String getNom(){
		return nom;
	}
	
	/**
	 * getter de l'obligation
	 * 
	 * @return booleen de l'obligation
	 */
	public boolean estObligatoire(){
		return obligatoire;
	}
	
	/**
	 * getter si doit avoir un argument ou non
	 * 
	 * @return booleen
	 */
	public boolean argNecessary(){
		return hasArg;
	}
	
	/**
	 * getter de l'argument de l'option, si aucun
	 * renvoie null
	 * 
	 * @return argument de l'option
	 */
	public String getArgument(){
		return argument;
	}
	
	/**
	 * setter de l'argument de l'option,
	 * 
	 * @return argument de l'option
	 */
	public void setArgument(String arg){
		argument = arg;
	}
	
	/**
	 * getter si l'option a �t� saisie ou non
	 * 
	 * @return boolean
	 */
	public boolean getPresent(){
		return present;
	}
	
	/**
	 * setter si l'option a �t� saisi au d�marrage
	 * 
	 * @return argument de l'option
	 */
	public void optionIsHere(){
		present = true;
	}
	
	/**
	 * indique si la chaine de caract�re pass�e en 
	 * param�tre est �gale au nom de l'option
	 * 
	 * @param chaine
	 * 
	 * @return true ou false selon la comparaison
	 */
	public boolean isThisOption(String chaine){
		if(chaine.equals(getNom())) return true;
		return false;
	}

}
