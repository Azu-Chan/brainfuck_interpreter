package brainfuck.exceptions;

/**
 * Exception personnalis�e, une option obligatoire de la ligne de commande 
 * n'est pas pr�sente
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.1
 */
@SuppressWarnings("serial")
public class ObligatoryOptionMissingException extends Exception implements ExitException {
	
	/**
     * Constructeur.
     * 
     * @param option
     */
	public ObligatoryOptionMissingException(String option){
		super("ERREUR (6) : l'option obligatoire " + option + " n'est pas pr�sente.");
	}
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 6;
	}
}