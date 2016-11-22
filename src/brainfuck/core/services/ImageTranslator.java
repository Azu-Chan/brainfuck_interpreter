package brainfuck.core.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import brainfuck.ImageInclude;

/**
 * Classe qui va transformer un programme brainfuck textuel
 * en une version image...
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.3
 */
public class ImageTranslator implements ImageInclude{	
	private String prog;
	private BufferedImage img;
	
	// dimension de l'image (dim * dim) car carr�e
	private int dim;
	
	/**
	 * Constructeur d'un ImageTranslator.
	 * 
	 * @param p
	 */
	public ImageTranslator(String p){
		prog = p;
		dim = (int) Math.ceil(Math.sqrt(prog.length()));
		if(prog.length() > 0){
			img = new BufferedImage(dim * PIXEL_LENGTH, dim * PIXEL_LENGTH, BufferedImage.TYPE_INT_RGB);
		}
		else{
			img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		}
	}
	
	/**
	 * cette methode va lire la cha�ne de caract�res contenant
	 * le programme brainfuck et cr�er en m�moire l'image
	 * de ce programme
	 */
	public void createImageProg(){
		if(prog.length() <= 0){
			img.setRGB(0, 0, intRGBinstr(' '));
			return ;
		}
		
		int xDeb = 0;
		int yDeb = 0;
		
		int currentColor;
		
		// pour chaque instruction du programme
		for(int i = 0; i < prog.length(); i++){
			currentColor = intRGBinstr(prog.charAt(i));
			// coord. x
			for(int j = 0; j < PIXEL_LENGTH; j++){
				// coord. y
				for(int k = 0; k < PIXEL_LENGTH; k++){
					// coloriage pixel
					img.setRGB(xDeb + j, yDeb + k, currentColor);
				}
			}
			// on passe � la colonne suivante
			xDeb += PIXEL_LENGTH;
			
			// si la ligne est compl�te, on passe � la ligne suivante et on revient � la premi�re colonne
			if(xDeb >= dim * PIXEL_LENGTH){
				xDeb = 0;
				yDeb += PIXEL_LENGTH;
			}
		}
	}
	
	/**
	 * Renvoie un code RGB interpr�table par {@link ImageTranslator#createImageProg()}
	 * pour pouvoir invoquer {@link BufferedImage#setRGB(int, int, int)}
	 * 
	 * @param instr
	 * 
	 * @return le code RGB en int
	 */
	private int intRGBinstr(char instr){
		int instrColor;
		
		switch(instr){
			case '+' :
				instrColor = COLOR_INCR;
				break;
			case '-' :
				instrColor = COLOR_DECR;
				break;
			case '<' :
				instrColor = COLOR_LEFT;
				break;
			case '>' :
				instrColor =COLOR_RIGHT;
				break;
			case '.' :
				instrColor = COLOR_OUT;
				break;
			case ',' :
				instrColor = COLOR_IN;
				break;
			case '[' :
				instrColor = COLOR_JUMP;
				break;
			case ']' :
				instrColor = COLOR_BACK;
				break;
			default : instrColor = COLOR_MISSING;
		}
		
		return instrColor;
	}
	
	/**
	 * Cr�er un fichier .bmp dans le r�pertoire courant � partir de l'image
	 * en m�moire
	 * 
	 * @param name
	 * 
	 * @throws IOException 
	 */
	public void createImage(String name) throws IOException{
		// on veux un fichier .bmp
		File outputFile = new File(name + ".bmp");
		// java peut cr�er des images png, du coup conversion implicite en .bmp
		ImageIO.write(img, "png", outputFile);
	}
}
