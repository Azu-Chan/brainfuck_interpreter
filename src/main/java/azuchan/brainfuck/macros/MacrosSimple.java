package azuchan.brainfuck.macros;

import azuchan.brainfuck.Instructions;

/**
 * Enumération pour les macros sans paramètres
 * 
 * @author Dylan Ritrovato
 * 
 * @version 2.0
 */
public enum MacrosSimple implements Macros {
	TO_DIGIT(), // char nb to nb in mem
	FROM_DIGIT(),  // nb in mem to char nb
	TO_ALPHA(), // char char lowercase to nb of this char in the alphabet in mem
	FROM_ALPHA(), // nb in mem to char char lowercase
	TO_ALPHA_CAP(), // char char uppercase to nb of this char in the alphabet in mem
	FROM_ALPHA_CAP()  // nb in mem to char char uppercase
	;
	
	/**
	 * Constructeur
	 */
	MacrosSimple() {}

	/**
	 * Calcule et renvoie l'effet de la macro
	 * 
	 * @return l'effet
	 */
	public String getEffect() {
		String response = "";
		int max = 0;
		if(name().endsWith("DIGIT")){
			max = 48;
		}
		else if(name().endsWith("ALPHA")){
			max = 96;
		}
		else if(name().endsWith("CAP")){
			max = 64;
		}
		
		if(name().startsWith("TO")){
			for(int i = 0; i < max; i++){
				response += Instructions.DECR.getLongSyntax() + "\r\n";
			}
		}
		else if(name().startsWith("FROM")){
			for(int i = 0; i < max; i++){
				response += Instructions.INCR.getLongSyntax() + "\r\n";
			}
		}
		
		return response;
	}

}
