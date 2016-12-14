package brainfuck.macros;

/**
 * Interface commune � toutes les macros
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public interface Macros {
	
	/**
	 * Prototype du getter sur ne nom
	 * 
	 * @return le nom
	 */
	public String getName();
	
	/**
	 * Prototype de l'effet d�sir�
	 * 
	 * @return l'effet
	 */
	public String getEffect();
	
}