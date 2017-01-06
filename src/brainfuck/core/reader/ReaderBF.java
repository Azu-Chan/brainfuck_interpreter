package brainfuck.core.reader;

import java.io.IOException;

import brainfuck.ProgramStructure;
import brainfuck.exceptions.ImageInterpretationErrorException;
import brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * ReaderBF est la superclasse nécessaire à ReaderImage et ReaderText
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.2
 */
public abstract class ReaderBF{
	protected ProgramStructure memProg;

	/**
	 * Constructeur d'un ReaderBF non instanciable
	 */
	public ReaderBF() {
		memProg = new ProgramStructure();
	}
	
	/**
	 * Simple getteur pour le programme et les fonctions
	 * 
	 * @return La structure comprenant le programme et les éventuelles fonctions
	 */
	public ProgramStructure getProgStruct(){
		return memProg;
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
