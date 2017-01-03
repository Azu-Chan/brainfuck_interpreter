package brainfuck.functions;

import java.util.ArrayList;

import brainfuck.Instructions;

/**
 * Enumération pour les macros sans paramètres
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Najib
 * 
 * @version 1.0
 */

public class FunctionsSimple {

	private String name;
	private ArrayList<String> function = new ArrayList<String>();
	private ArrayList<String> contents = new ArrayList<String>();
	

	/**
	 * Constructeur
	 * 
	 * @param name
	 */
	FunctionsSimple(String name){
		this.name = name;
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
	 * Calcule et renvoie l'effet de la fonction
	 * 
	 * @return l'effet
	 */
	public String getEffect() {
		String response = "";
		switch(name){
			case "function_TODIGIT" :
				for(int i = 0; i < 48; i++){
					response += Instructions.DECR.getLongSyntax() + "\r\n";
				}
			break;
			
			default : ;
		}
		return response;
	}

}
