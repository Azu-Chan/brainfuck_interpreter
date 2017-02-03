package azuchan.brainfuck.exceptions;

/**
 * Exception personnalisée, l'instruction lue n'est
 * pas une instruction brainfuck.
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.1
 */
@SuppressWarnings("serial")
public class IsNotBrainfuckInstructionException extends Exception implements ExitException {

	/**
     * Constructeur.
     * 
     * @param wInstr
     */
	public IsNotBrainfuckInstructionException(String wInstr){
		super("ERREUR (9) : " + wInstr + " n'est pas une instruction brainfuck.");
	}
	
	/**
     * Rend le code d'erreur de l'exception en question
     * 
     * @return code
     */
	public int getErrorCode(){
		return 9;
	}
}