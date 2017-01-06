package brainfuck.exceptions;

/**
 * Exception personnalisée, la procédure demandée n'existe pas
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class UnknowProcedureException extends Exception implements ExitException {

	/**
     * Constructeur.
     */
	public UnknowProcedureException(String name){
		super("ERREUR (10) : La procédure " + name + " n'existe pas.");
	}
	
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 10;
	}

}
