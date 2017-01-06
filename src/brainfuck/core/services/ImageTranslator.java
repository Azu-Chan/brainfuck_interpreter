package brainfuck.core.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import brainfuck.ImageInclude;
import brainfuck.Instructions;

/**
 * Classe qui va transformer un programme brainfuck textuel
 * en une version image...
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.4
 */
public class ImageTranslator implements ImageInclude{	
	private String prog;
	private BufferedImage img;
	
	// dimension de l'image (dim * dim) car carrée
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
	 * cette methode va lire la chaîne de caractères contenant
	 * le programme brainfuck et créer en mémoire l'image
	 * de ce programme
	 */
	public void createImageProg(){
		if(prog.length() <= 0){
			img.setRGB(0, 0, COLOR_MISSING);
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
			
			// si la ligne est complète, on passe à la ligne suivante et on revient à la première colonne
			if(xDeb >= dim * PIXEL_LENGTH){
				xDeb = 0;
				yDeb += PIXEL_LENGTH;
			}
		}
	}
	
	/**
	 * Getter le l'image créée
	 * 
	 * @return l'image
	 */
	public BufferedImage getImg(){
		return img;
	}
	
	/**
	 * Renvoie un code RGB interprétable par {@link ImageTranslator#createImageProg()}
	 * pour pouvoir invoquer {@link BufferedImage#setRGB(int, int, int)}
	 * 
	 * @param instr
	 * 
	 * @return le code RGB en int
	 */
	private int intRGBinstr(char instr){
		for(Instructions i : Instructions.values()){
			if(instr == i.getShortSyntax()){
				return i.getRGB();
			}
		}
		return COLOR_MISSING;
	}
	
	/**
	 * Créer un fichier .bmp dans le répertoire courant à partir de l'image
	 * en mémoire
	 * 
	 * @param name
	 * 
	 * @throws IOException 
	 */
	public void createImage(String name) throws IOException{
		// on veux un fichier .bmp
		File outputFile = new File(name + ".bmp");
		// java peut créer des images png, du coup conversion implicite en .bmp
		ImageIO.write(img, "png", outputFile);
	}
}
