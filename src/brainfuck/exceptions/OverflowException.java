package brainfuck.exceptions;

/**
 * Exception personnalis�e, une case de la m�moire brainfuck 
 * contient une valeur interdite
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.1
 */
@SuppressWarnings("serial")
public class OverflowException extends Exception implements ExitException {

	/**
     * Constructeur.
     * 
     * @param pointer
     * @param value
     */
	public OverflowException(int pointer, int value){
		super("ERREUR (1) : Overflow sur la case m�moire " + pointer + " (" + value + ").");
	}
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 1;
	}
}