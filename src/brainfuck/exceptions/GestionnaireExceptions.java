package brainfuck.exceptions;

import java.util.Arrays;
import java.util.List;

/**
 * Classe de gestion des exceptions
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class GestionnaireExceptions {
	private Exception e;
	
	/**
     * Constructeur.
     * 
     * @param e
     */
	public GestionnaireExceptions(Exception e){
		this.e = e;
	}
	
	/**
     * Affiche le message d'une exception. Si l'exception 
     * est associée à un code d'erreur, quitte le programme
     * avec ce code
     */
	public void traiterException(){
		System.out.println(e.getMessage());
		
		List<Class<?>> c = Arrays.asList(e.getClass().getInterfaces());
		
		if(c.contains(ExitException.class)){
			System.exit( ( (ExitException) e).getErrorCode() );
		}
	}
}
