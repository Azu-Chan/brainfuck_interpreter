package brainfuck.macros;

import brainfuck.Instructions;

/**
 * Enumération pour les macros sans paramètres
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public enum MacrosSimple implements Macros {
	TO_DIGIT("TO_DIGIT"), // char nb to nb in mem
	FROM_DIGIT("FROM_DIGIT"),  // nb in mem to char nb
	TO_ALPHA("TO_ALPHA"), // char char lowercase to nb of this char in the alphabet in mem
	FROM_ALPHA("FROM_ALPHA"), // nb in mem to char char lowercase
	TO_ALPHA_CAP("TO_ALPHA_CAP"), // char char uppercase to nb of this char in the alphabet in mem
	FROM_ALPHA_CAP("FROM_ALPHA_CAP")  // nb in mem to char char uppercase
	;
	
	private String name;
	
	/**
	 * Constructeur
	 * 
	 * @param name
	 */
	MacrosSimple(String name){
		this.name = name;
	}

	/**
	 * Getter sur le nom
	 * 
	 * @return le nom
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Calcule et renvoie l'effet de la macro
	 * 
	 * @return l'effet
	 */
	@Override
	public String getEffect() {
		String response = "";
		switch(name){
			case "TO_DIGIT" :
				for(int i = 0; i < 48; i++){
					response += Instructions.DECR.getLongSyntax() + "\r\n";
				}
			break;
			case "FROM_DIGIT" :
				for(int i = 0; i < 48; i++){
					response += Instructions.INCR.getLongSyntax() + "\r\n";
				}
			break;
			case "TO_ALPHA" :
				for(int i = 0; i < 96; i++){
					response += Instructions.DECR.getLongSyntax() + "\r\n";
				}
			break;
			case "FROM_ALPHA" :
				for(int i = 0; i < 96; i++){
					response += Instructions.INCR.getLongSyntax() + "\r\n";
				}
			break;
			case "TO_ALPHA_CAP" :
				for(int i = 0; i < 64; i++){
					response += Instructions.DECR.getLongSyntax() + "\r\n";
				}
			break;
			case "FROM_ALPHA_CAP" :
				for(int i = 0; i < 64; i++){
					response += Instructions.INCR.getLongSyntax() + "\r\n";
				}
			break;
			default : ;
		}
		return response;
	}

}
