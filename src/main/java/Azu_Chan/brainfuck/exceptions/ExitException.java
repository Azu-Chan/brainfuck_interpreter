package Azu_Chan.brainfuck.exceptions;

/**
 * Interface pour les exceptions devant quitter le programme
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public interface ExitException {
	
	/**
     * Rend le code d'erreur de l'exception en question
     */
	public int getErrorCode();

}
