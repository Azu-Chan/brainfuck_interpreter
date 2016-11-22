package brainfuck.core.reader;

import java.io.IOException;

import brainfuck.exceptions.ImageInterpretationErrorException;
import brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * ReaderBF est la superclasse n�cessaire � ReaderImage et ReaderText
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
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
	 * Simple getteur de la chaine de caract�res contanant la syntaxe courte.
	 * 
	 * @return Une chaine de caract�res �tant la forme raccourcie du programme
	 * brainfuck �crit dans le fichier pass� en param�tre.
	 */
	public String getShortSyntax(){
		return shortSyntax;
	}
	
	/**
	 * cette methode va recopier le raccourci dans la cha�ne finale
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
