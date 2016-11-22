package brainfuck.exceptions;

/**
 * Exception personnalisée, l'ardument de l'option -i ne peut pas ouvrir de fichier...
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InputMissingFileException extends Exception implements ExitException {
	
	/**
     * Constructeur.
     * 
     * @param nameFile
     */
	public InputMissingFileException(String nameFile){
		super("ERREUR (3) : le fichier d'entrée " + nameFile + " ne peut pas être ouvert ou n'existe pas.");
	}
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 3;
	}
}
