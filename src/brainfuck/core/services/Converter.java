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
	
	/**
	 * Constructeur non instanciable
	 * 
	 * @param prog
	 * @param name
	 */
	protected Converter(String prog, String name){
		mainProgram = prog;
		nameProg = name;
	}
	
	/**
	 * Prototype de l'initialisation du fichier
	 */
	protected abstract void initialize() throws IOException;
	
	/**
	 * Prototype de l'écriture de l'entête du fichier
	 */
	protected abstract void writeEntete() throws IOException;
	
	//protected abstract void writeFunct();
	
	/**
	 * Prototype de l'écriture du corps du fichier
	 */
	protected abstract void writeCorpse() throws IOException, IsNotBrainfuckInstructionException;
	
	/**
	 * Prototype de l'écriture de la fin du fichier
	 */
	protected abstract void writeEnd() throws IOException;
	
	/**
	 * Exécute toutes les étapes de la création du fichier
	 */
	public void launchProcedure() throws IOException, IsNotBrainfuckInstructionException{
		initialize();
		writeEntete();
		writeCorpse();
		writeEnd();
	}
}
