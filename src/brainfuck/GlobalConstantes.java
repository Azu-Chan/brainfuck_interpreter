package brainfuck;

import java.awt.Color;

/**
 * Classe qui stocke certaines valeurs constantes 
 * utiles pour la gestion des options
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 2.0
 */
public abstract class GlobalConstantes {
	// taille en pixel du côté d'un carré pour représenter une instruction brainfuck 
	public static final int PIXEL_LENGTH = 3;
			
	// RGB de la couleur manquante (noire) pour le traitement d'images (programmes)
	public static final int COLOR_MISSING = new Color(0, 0, 0).getRGB();	
	
	// Options courtes
	public static final String nomOptP = "-p";
	public static final String nomOptI = "-i";
	public static final String nomOptO = "-o";
	
	// Options longues
	public static final String nomOptRewrite = "--rewrite";
	public static final String nomOptCheck = "--check";
	public static final String nomOptTranslate = "--translate";
	public static final String nomOptTrace = "--trace";
	public static final String nomOptConvert = "--convert";
	
	// Constantes pour l'UI
	public static final String languageC = "C";
	public static final String languagePHP = "PHP";
	public static final String annul = "ANNULER";
}
