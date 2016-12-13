package brainfuck.macros;

import brainfuck.Instructions;

/**
 * Enumération pour les macros avec paramètre
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public enum MacrosParam implements Macros {
	MULTI_INCR("MULTI_INCR"), // multi incrementation
	MULTI_DECR("MULTI_DECR"), // multi decrementation
	MULTI_LEFT("MULTI_LEFT"), // multi left
	MULTI_RIGHT("MULTI_RIGHT"), // multi right
	;
	
	private String name;
	private String param = "0";
	
	/**
	 * Constructeur...
	 * 
	 * @param name
	 */
	MacrosParam(String name){
		this.name = name;
	}
	
	/**
	 * Prise du paramètre...
	 * 
	 * @param p
	 */
	public void setParam(String p){
		param = p;
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
		int n = Integer.valueOf(param);
		switch(name){
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
			case "FROM_RIGHT" :
				for(int i = 0; i < n; i++){
					response += Instructions.RIGHT.getLongSyntax() + "\r\n";
				}
			break;
			default : ;
		}
		return response;
	}

}
