package brainfuck.macros;

/**
 * Classe pour la macro sp�ciale DEFINE
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class MacroDefine implements Macros {
	private String name;
	private String param1; // valeur � remplacer
	private String param2; // nouvelle valeur
	
	public static final String NAME_MACRO = "DEFINE";
	
	/**
	 * Constructeur...
	 * 
	 * @param name
	 * @param param1
	 * @param param2
	 */
	public MacroDefine(String param1, String param2){
		this.name = NAME_MACRO;
		this.param1 = param1;
		this.param2 = param2;
	}
	
	/**
	 * Getter sur le param�tre � remplacer
	 * 
	 * @return param1
	 */
	public String getParam(){
		return param1;
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
	 * Nouvelle valeur suite au DEFINE
	 * 
	 * @return la nouvelle valeur
	 */
	@Override
	public String getEffect() {
		return param2;
	}

}
