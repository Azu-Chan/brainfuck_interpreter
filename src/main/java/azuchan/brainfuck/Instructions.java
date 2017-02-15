package azuchan.brainfuck;

import java.awt.Color;

/**
 * Enumération pour les trois représentations des instructions.
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.0
 */
public enum Instructions {
	INCR("INCR",'+', new Color(255, 255, 255).getRGB(), "\tmem[pointer] += ", "\t$mem[$pointer] += "),
	DECR("DECR",'-', new Color(75, 0, 130).getRGB(), "\tmem[pointer] -= ", "\t$mem[$pointer] -= "),
	LEFT("LEFT",'<', new Color(148, 0, 211).getRGB(), "\tpointer -= ", "\t$pointer -= "),
	RIGHT("RIGHT",'>', new Color(0, 0, 255).getRGB(), "\tpointer += ", "\t$pointer += "),
	OUT("OUT",'.', new Color(0, 255, 0).getRGB(), "\tputchar(mem[pointer])", "\techo chr($mem[$pointer])"),
	IN("IN",',', new Color(255, 255, 0).getRGB(), "\tmem[pointer] = getchar()", "\t$mem[$pointer] = substr(fgets($STDIN), $pointer, 1)"),
	JUMP("JUMP",'[', new Color(255, 127, 255).getRGB(), "\twhile(mem[pointer] != 0){", "\twhile($mem[$pointer] != 0){"),
	BACK("BACK",']', new Color(255, 0, 0).getRGB(), "\t}", "\t}");
	
	private String longSyntax;
	private char shortSyntax;
	private int rgb;
	private String cEquivalent;
	private String phpEquivalent;

	/**
	 * Constructeur...
	 * 
	 * @param longSyntax
	 * @param shortSyntax
	 * @param rgb
	 * @param cEquiv
	 * @param phpEquiv
	 */
	Instructions(String longSyntax, char shortSyntax, int rgb, String cEquiv, String phpEquiv){
		this.longSyntax = longSyntax;
		this.shortSyntax = shortSyntax;
		this.rgb = rgb;
		this.cEquivalent = cEquiv;
		this.phpEquivalent = phpEquiv;
	}
	
	/**
	 * Getter pour la syntaxe longue
	 * 
	 * @return syntaxe longue
	 */
	public String getLongSyntax(){
		return longSyntax;
	}
	
	/**
	 * Getter pour la syntaxe courte
	 * 
	 * @return syntaxe courte
	 */
	public char getShortSyntax(){
		return shortSyntax;
	}
	
	/**
	 * Getter pour le RGB correspondant à l'instruction
	 * 
	 * @return RGB correspondant à l'instruction
	 */
	public int getRGB(){
		return rgb;
	}
	
	/**
	 * Getter pour l'équivalent en C correspondant à l'instruction
	 * 
	 * @return équivalence C
	 */
	public String getCEquivalent(){
		return cEquivalent;
	}
	
	/**
	 * Getter pour l'équivalent en PHP correspondant à l'instruction
	 * 
	 * @return équivalence PHP
	 */
	public String getPhpEquivalent(){
		return phpEquivalent;
	}
	
}
