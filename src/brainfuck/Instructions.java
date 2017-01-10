package brainfuck;

import java.awt.Color;

/**
 * Enumération pour les trois représentations des instructions.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public enum Instructions {
	INCR("INCR",'+', new Color(255, 255, 255).getRGB()),
	DECR("DECR",'-', new Color(75, 0, 130).getRGB()),
	LEFT("LEFT",'<', new Color(148, 0, 211).getRGB()),
	RIGHT("RIGHT",'>', new Color(0, 0, 255).getRGB()),
	OUT("OUT",'.', new Color(0, 255, 0).getRGB()),
	IN("IN",',', new Color(255, 255, 0).getRGB()),
	JUMP("JUMP",'[', new Color(255, 127, 255).getRGB()),
	BACK("BACK",']', new Color(255, 0, 0).getRGB());
	
	private String longSyntax;
	private char shortSyntax;
	private int rgb;

	/**
	 * Constructeur...
	 */
	Instructions(String longSyntax, char shortSyntax, int rgb){
		this.longSyntax = longSyntax;
		this.shortSyntax = shortSyntax;
		this.rgb = rgb;
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
	
}
