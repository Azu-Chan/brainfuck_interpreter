package brainfuck.core.services;

import java.io.IOException;

import brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * Superclasse pour transformer du code brainfuck en un autre langage de programmation
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public abstract class Converter {
	protected String mainProgram;
	protected String nameProg;
	
	protected Converter(String prog, String name){
		mainProgram = prog;
		nameProg = name;
	}
	
	protected abstract void initialize() throws IOException;
	
	protected abstract void writeEntete() throws IOException;
	
	//protected abstract void writeFunct();
	
	protected abstract void writeCorpse() throws IOException, IsNotBrainfuckInstructionException;
	
	protected abstract void writeEnd() throws IOException;
	
	public void launchProcedure() throws IOException, IsNotBrainfuckInstructionException{
		initialize();
		writeEntete();
		writeCorpse();
		writeEnd();
	}
}
