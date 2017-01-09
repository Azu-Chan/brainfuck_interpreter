package brainfuck.procedure;

import java.util.ArrayList;
import java.util.List;


import brainfuck.core.services.Checker;
import brainfuck.exceptions.SyntaxErrorException;

/**
 * Classe qui va faire le procéduure comme fonction
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Najib
 * 
 * @version 1.0
 */

public class Procedure {
	protected String name;
	protected String contents;
	protected List<Integer> parameters;
	
	/**
	 * Constructeur d'un Procedure
	 * 
	 * @param name, contents, parameters
	 */
	public Procedure(String name, String contents, List<Integer> parameters){
		this.name = name;
		this.contents=contents;
		this.parameters = new ArrayList<Integer>();
		this.parameters.addAll(parameters);
	}
	
	/**
	 * Getter sur le nom
	 * 
	 * @return le nom
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter sur le contenu
	 * 
	 * @return le contenu
	 */
	public String getContents() {
		return contents;
	}
	
	/**
	 * Getter sur le paramètre
	 * 
	 * @return le paramètre
	 */
	public Integer getParameter(Integer nb) {
		return parameters.get(nb);
	}

	/**
	 * Getter sur le nombre de paramètre
	 * 
	 * @return le paramètre
	 */
	public Integer getNbParameter(Integer nb) {
		return parameters.size();
	}
	
	/**
	 * Effectue la vérification de la contenu
	 * @throws SyntaxErrorException 
	 */
	public boolean verify() throws SyntaxErrorException{
		Checker c = new Checker(contents);
		c.verify();
		
		if(c.isWellFormed()){
			return true;
		}
		return false;
	}

}
