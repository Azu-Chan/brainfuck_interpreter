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
public class WrongNumberArgumentsException extends Exception implements ExitException {
	
	/**
     * Constructeur.
     * 
     * @param option
     */
	public WrongNumberArgumentsException(String option){
		super("ERREUR (7) : l'option " + option + " n'a pas le bon nombre d'argument.");
	}
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 7;
	}
}