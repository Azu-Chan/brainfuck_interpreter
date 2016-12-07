package brainfuck;

import java.awt.Color;

/**
 * Classe qui stocke certaines valeurs constantes 
 * utiles pour la gestion des .bmp
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.1
 */
public interface ImageInclude {
	// taille en pixel du coté d'un carré pour représenter une instruction brainfuck 
	public static final int PIXEL_LENGTH = 3;
		
	// RGB de la couleur manquante (noire) pour le traitement d'images (programmes)
	public static final int COLOR_MISSING = new Color(0, 0, 0).getRGB();	
}
