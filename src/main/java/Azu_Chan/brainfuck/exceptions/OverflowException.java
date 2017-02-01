package Azu_Chan.brainfuck.exceptions;

/**
 * Exception personnalisée, une case de la mémoire brainfuck 
 * contient une valeur interdite
 * 
 * @author Dylan Ritrovato
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
		super("ERREUR (1) : Overflow sur la case mémoire " + pointer + " (" + value + ").");
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