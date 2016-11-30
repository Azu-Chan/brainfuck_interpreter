package brainfuck.core.reader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import brainfuck.ImageInclude;
import brainfuck.exceptions.ImageInterpretationErrorException;
import brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * ReaderImage est la classe qui va lire le fichier brainfuck pass� avec "-p"
 * et le transformer en une chaine de caract�res uniquement constitu�e de shortcuts.
 * UNIQUEMENT SI CE FICHIER EST UN fICHIER .bmp !
 * 
 * Cette chaine est ensuite utilis�e pour tous les traitements disponibles dans
 * cet interpr�teur de brainfuck.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.3
 */
public class ReaderImage extends ReaderBF implements ImageInclude{
	private BufferedImage prog;
	
	private int largeur;
	private int longueur;

	/**
	 * Constructeur d'un ReaderBF.
	 * 
	 * @param progIm
	 * @throws IOException 
	 */
	public ReaderImage(File progIm) throws IOException {
		super();
		prog = ImageIO.read(progIm);
		largeur = prog.getWidth();
		longueur = prog.getHeight();
	}

	/**
	 * cette methode va analyser l'image (le programme) 
	 * et essayer de transformer chaque case d'instruction 
	 * en shortcut.
	 * 
	 * @throws ImageInterpretationErrorException
	 * @throws IsNotBrainfuckInstructionException 
	 */
	@Override
	public void readFile() throws ImageInterpretationErrorException, IsNotBrainfuckInstructionException {
		if(largeur <= 0 || longueur <= 0){
			return ;
		}
		
		int xDeb = 0;
		int yDeb = 0;
		
		int[] rgbArray;
		int i = 0;
		
		// pour chaque instruction pr�sum�e du programme
		while(yDeb < longueur){
			if(xDeb + PIXEL_LENGTH > largeur || yDeb + PIXEL_LENGTH > longueur){
				throw new ImageInterpretationErrorException();
			}
			rgbArray = new int[PIXEL_LENGTH * PIXEL_LENGTH];
			// coord. x
			for(int j = 0; j < PIXEL_LENGTH; j++){
				// coord. y
				for(int k = 0; k < PIXEL_LENGTH; k++){
					// lecture pixel
					rgbArray[i] = prog.getRGB(xDeb + j, yDeb + k);
					i++;
				}
			}
			
			for(int l = 1; l < rgbArray.length; l++){
				if(rgbArray[l-1] != rgbArray[l]){
					throw new ImageInterpretationErrorException();
				}
			}
			
			constructionWithPix(rgbArray[0]);
			
			// on passe � la colonne suivante
			xDeb += PIXEL_LENGTH;
			
			// si la ligne est compl�te, on passe � la ligne suivante et on revient � la premi�re colonne
			if(xDeb >= largeur){
				xDeb = 0;
				yDeb += PIXEL_LENGTH;
			}
			
			i = 0;
		}
	}
	
	/**
	 * cette methode va transformer la couleur r�cup�r�e en raccourci puis
	 * l'injecter dans la cha�ne � l'aide de constructionWithShortcut(String s)
	 * 
	 * @param rgb
	 * 
	 * @throws IsNotBrainfuckInstructionException
	 */
	private void constructionWithPix(int rgb) throws IsNotBrainfuckInstructionException{
		if(rgb == COLOR_INCR){
			constructionWithShortcut("+");
			return ;
		}
		if(rgb == COLOR_DECR){
			constructionWithShortcut("-");
			return ;
		}
		if(rgb == COLOR_LEFT){
			constructionWithShortcut("<");
			return ;
		}
		if(rgb == COLOR_RIGHT){
			constructionWithShortcut(">");
			return ;
		}
		if(rgb == COLOR_OUT){
			constructionWithShortcut(".");
			return ;
		}
		if(rgb == COLOR_IN){
			constructionWithShortcut(",");
			return ;
		}
		if(rgb == COLOR_JUMP){
			constructionWithShortcut("[");
			return ;
		}
		if(rgb == COLOR_BACK){
			constructionWithShortcut("]");
			return ;
		}
		if(rgb == COLOR_MISSING){
			return ;
		}
		throw new IsNotBrainfuckInstructionException(new Integer(rgb).toString());
	}

}