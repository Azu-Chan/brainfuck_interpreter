package brainfuck;

/**
 * Classe qui stocke certaines valeurs constantes 
 * utiles pour la gestion des options
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.1
 */
public interface OptionInclude {
	public static final String nomOptP = "-p";
	public static final String nomOptI = "-i";
	public static final String nomOptO = "-o";
	
	public static final String nomOptRewrite = "--rewrite";
	public static final String nomOptCheck = "--check";
	public static final String nomOptTranslate = "--translate";
	public static final String nomOptTrace = "--trace";
	
	public static final String nomOptConvert = "--convert";
	
	//public static final String languageJava = "JAVA";
	public static final String languageC = "C";
	public static final String languagePHP = "PHP";
}
