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
 * @version 1.0
 */
public interface ImageInclude {
	// taille en pixel du coté d'un carré pour représenter une instruction brainfuck 
		public static final int PIXEL_LENGTH = 3;
		
		// RGB des différentes couleurs utilisés pour la représentation des instructions brainfuck
		public static final int COLOR_INCR = new Color(255, 255, 255).getRGB();
		public static final int COLOR_DECR = new Color(75, 0, 130).getRGB();
		public static final int COLOR_LEFT = new Color(148, 0, 211).getRGB();
		public static final int COLOR_RIGHT = new Color(0, 0, 255).getRGB();
		public static final int COLOR_OUT = new Color(0, 255, 0).getRGB();
		public static final int COLOR_IN = new Color(255, 255, 0).getRGB();
		public static final int COLOR_JUMP = new Color(255, 127, 255).getRGB();
		public static final int COLOR_BACK = new Color(255, 0, 0).getRGB();
		public static final int COLOR_MISSING = new Color(0, 0, 0).getRGB();
		
}
