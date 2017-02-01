package Azu_Chan.brainfuck.exceptions;

/**
 * Exception personnalisée, une option obligatoire de la ligne de commande 
 * n'est pas présente
 * 
 * @author Dylan Ritrovato
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
		super("ERREUR (6) : l'option obligatoire " + option + " n'est pas présente.");
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