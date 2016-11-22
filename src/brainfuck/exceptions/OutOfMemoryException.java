package brainfuck.exceptions;

/**
 * Exception personnalis�e, le pointer de la 
 * m�moire brainfuck est sortie de sa plage de valeurs
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
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
		super("ERREUR (2) : Le pointeur est en dehors de la plage m�moire d�finie (" + pointer + ").");
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