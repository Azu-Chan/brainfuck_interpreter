package Azu_Chan.brainfuck.exceptions;

/**
 * Exception personnalisée, le pointer de la 
 * mémoire brainfuck est sortie de sa plage de valeurs
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.1
 */
@SuppressWarnings("serial")
public class OutOfMemoryException extends Exception implements ExitException {

	/**
     * Constructeur.
     * 
     * @param pointer
     */
	public OutOfMemoryException(int pointer){
		super("ERREUR (2) : Le pointeur est en dehors de la plage mémoire définie (" + pointer + ").");
	}
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 2;
	}
}