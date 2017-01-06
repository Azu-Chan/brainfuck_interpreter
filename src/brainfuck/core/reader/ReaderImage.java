package brainfuck.core.reader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import brainfuck.ImageInclude;
import brainfuck.Instructions;
import brainfuck.exceptions.ImageInterpretationErrorException;
import brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * ReaderImage est la classe qui va lire le fichier brainfuck passé avec "-p"
 * et le transformer en une chaine de caractères uniquement constituée de shortcuts.
 * UNIQUEMENT SI CE FICHIER EST UN fICHIER .bmp !
 * 
 * Cette chaine est ensuite utilisée pour tous les traitements disponibles dans
 * cet interpréteur de brainfuck.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.5
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
		
		// pour chaque instruction présumée du programme
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
			
			// on passe à la colonne suivante
			xDeb += PIXEL_LENGTH;
			
			// si la ligne est compléte, on passe à la ligne suivante et on revient à la première colonne
			if(xDeb >= largeur){
				xDeb = 0;
				yDeb += PIXEL_LENGTH;
			}
			
			i = 0;
		}
	}
	
	/**
	 * cette methode va transformer la couleur récupérée en raccourci puis
	 * l'injecter dans la chaîne à l'aide de constructionWithShortcut(String s)
	 * 
	 * @param rgb
	 * 
	 * @throws IsNotBrainfuckInstructionException
	 */
	private void constructionWithPix(int rgb) throws IsNotBrainfuckInstructionException{
		for(Instructions i : Instructions.values()){
			if(rgb == i.getRGB()){
				memProg.addInstruction(""+i.getShortSyntax());
				return ;
			}
		}
		if(rgb == COLOR_MISSING){
			return ;
		}
		throw new IsNotBrainfuckInstructionException(new Integer(rgb).toString());
	}

}
