package azuchan.brainfuck.core.reader;

import java.io.IOException;

import azuchan.brainfuck.exceptions.*;

/**
 * ReaderBF est la superclasse nécessaire à ReaderImage et ReaderText
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.1
 */
public abstract class ReaderBF{
	private String shortSyntax;

	/**
	 * Constructeur d'un ReaderBF non instanciable
	 */
	public ReaderBF() {
		shortSyntax = "";
	}
	
	/**
	 * Simple getteur de la chaine de caractères contanant la syntaxe courte.
	 * 
	 * @return Une chaine de caractères étant la forme raccourcie du programme
	 * brainfuck écrit dans le fichier passé en paramètre.
	 */
	public String getShortSyntax(){
		return shortSyntax;
	}
	
	/**
	 * cette methode va recopier le raccourci dans la chaîne finale
	 * 
	 * @param instr
	 */
	protected void constructionWithShortcut(String instr){
		shortSyntax += instr;
	}
	
	/**
	 * Pour rendre l'Override obligatoire.
	 * 
	 * @throws IOException 
	 * @throws IsNotBrainfuckInstructionException 
	 * @throws ImageInterpretationErrorException 
	 */
	public abstract void readFile() throws IOException, IsNotBrainfuckInstructionException, ImageInterpretationErrorException;

}
