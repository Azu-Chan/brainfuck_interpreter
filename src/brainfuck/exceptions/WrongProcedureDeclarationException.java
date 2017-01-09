package brainfuck.exceptions;

/**
 * Exception personnalisée, la procédure actuelle est mal déclarée
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WrongProcedureDeclarationException extends Exception implements ExitException {

	/**
     * Constructeur.
     */
	public WrongProcedureDeclarationException(String proc){
		super("ERREUR (11) : La procédure " + proc + " est mal déclarée.");
	}
	
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 11;
	}

}
