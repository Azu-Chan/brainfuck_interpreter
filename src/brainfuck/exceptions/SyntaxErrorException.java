package brainfuck.exceptions;

/**
 * Exception personnalisée, le programme brainfuck
 * est invalide sémantiquement
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.1
 */
@SuppressWarnings("serial")
public class SyntaxErrorException extends Exception implements ExitException {
	/**
     * Constructeur.
     */
	public SyntaxErrorException(){
		super("ERREUR (4) : Programme sémantiquement incorrect.");
	}
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 4;
	}
}