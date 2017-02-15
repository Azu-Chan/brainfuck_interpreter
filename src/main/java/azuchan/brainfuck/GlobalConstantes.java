package azuchan.brainfuck;

import java.awt.Color;

/**
 * Classe qui stocke certaines valeurs constantes 
 * utiles pour la gestion des options
 * 
 * @author Dylan Ritrovato
 * 
 * @version 2.0
 */
public abstract class GlobalConstantes {
	// taille en pixel du côté d'un carré pour représenter une instruction brainfuck 
	public static final int PIXEL_LENGTH = 3;
			
	// RGB de la couleur manquante (noire) pour le traitement d'images (programmes brainfuck .bmp)
	public static final int COLOR_MISSING = new Color(0, 0, 0).getRGB();
	
	// Options courtes
	public static final String NOM_OPT_P = "-p";
	public static final String NOM_OPT_I = "-i";
	public static final String NOM_OPT_O = "-o";
	
	// Options longues
	public static final String NOM_OPT_REWRITE = "--rewrite";
	public static final String NOM_OPT_CHECK = "--check";
	public static final String NOM_OPT_TRANSLATE = "--translate";
	public static final String NOM_OPT_TRACE = "--trace";
	public static final String NOM_OPT_CONVERT = "--convert";
	
	// Constantes pour l'UI du --convert
	public static final String LANGAGE_C = "C";
	public static final String LANGAGE_PHP = "PHP";
	public static final String ANNUL = "ANNULER";
	
	private GlobalConstantes() {}
}
