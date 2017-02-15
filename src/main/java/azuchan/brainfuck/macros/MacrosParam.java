package azuchan.brainfuck.macros;

import azuchan.brainfuck.Instructions;

/**
 * Enumération pour les macros avec paramètre
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.0
 */
public enum MacrosParam implements Macros {
	MULTI_INCR(), // multi incrementation
	MULTI_DECR(), // multi decrementation
	MULTI_LEFT(), // multi left
	MULTI_RIGHT(), // multi right
	;
	
	/**
	 * Constructeur...
	 */
	MacrosParam() {}

	/**
	 * Calcule et renvoie l'effet de la macro
	 * 
	 * @return l'effet
	 */
	public String getEffect(String param) {
		String response = "";
		if(param == null){
			System.err.println("Argument macro param null.");
		}
		int  n;
		try {
		    n = Integer.parseInt(param);
			switch(name()){
				case "MULTI_INCR" :
					for(int i = 0; i < n; i++){
						response += Instructions.INCR.getLongSyntax() + "\r\n";
					}
				break;
				case "MULTI_DECR" :
					for(int i = 0; i < n; i++){
						response += Instructions.DECR.getLongSyntax() + "\r\n";
					}
				break;
				case "MULTI_LEFT" :
					for(int i = 0; i < n; i++){
						response += Instructions.LEFT.getLongSyntax() + "\r\n";
					}
				break;
				case "MULTI_RIGHT" :
					for(int i = 0; i < n; i++){
						response += Instructions.RIGHT.getLongSyntax() + "\r\n";
					}
				break;
				default : ;
			}
		} catch (NumberFormatException e) {
		    System.err.println("Un entier est attendu comme argument de macro à paramètre.");
		}
		return response;
	}

}
