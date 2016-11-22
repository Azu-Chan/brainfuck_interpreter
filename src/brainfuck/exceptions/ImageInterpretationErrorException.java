package brainfuck.exceptions;

/**
 * Exception personnalisée, l'image ne correcpond pas
 * à un fichier brainfuck valide.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.1
 */
@SuppressWarnings("serial")
public class ImageInterpretationErrorException extends Exception implements ExitException {
	
	/**
     * Constructeur.
     */
	public ImageInterpretationErrorException(){
		super("ERREUR (8) : L'image ne peut pas être interprétée comme un programme brainfuck.");
	}
	
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 8;
	}
}