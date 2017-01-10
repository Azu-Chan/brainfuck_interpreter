package brainfuck.exceptions;

/**
 * Exception personnalisée, une option de la ligne de commande est présente plusieurs fois
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.1
 */
@SuppressWarnings("serial")
public class DuplicateOptionException extends Exception implements ExitException {

	/**
     * Constructeur.
     * 
     * @param option
     */
	public DuplicateOptionException(String option){
		super("ERREUR (5) : l'option " + option + " est présente plus d'une fois.");
	}
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 5;
	}
	
}