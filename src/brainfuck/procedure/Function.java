package brainfuck.procedure;

import java.util.List;

/**
 * Classe qui va faire la fonction
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Najib
 * 
 * @version 1.0
 */

public class Function extends Procedure {
	
	/**
	 * Constructeur d'une fonction
	 * 
	 * @param name, contents, parameters, return
	 */
	public Function(String name, String contents, List<Integer> parameters) {
		super(name, contents, parameters);
	}

}