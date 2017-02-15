package azuchan.brainfuck.core.services;

import java.io.IOException;

import azuchan.brainfuck.Instructions;
import azuchan.brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * Superclasse pour transformer du code brainfuck 
 * en un autre langage de programmation
 * 
 * @author Dylan Ritrovato
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
	
	/**
	 * Prototype de l'écriture du corps du fichier
	 */
	protected abstract void writeCorpse() throws IOException, IsNotBrainfuckInstructionException;
	
	/**
	 * transformation de l'instruction brainfuck un
	 * autre langage
	 * 
	 * @param currentInstr
	 * @param nbOcc
	 * 
	 * @return ligne de code à écrire dans le fichier
	 */
	protected String getLine(char currentInstr, int nbOcc) throws IsNotBrainfuckInstructionException{
		Instructions instr = null;
		String s = "";
		
		for(Instructions i : Instructions.values()){
			if(i.getShortSyntax() == currentInstr){
				instr = i;
				break;
			}
		}
		
		if(instr == null){
			throw new IsNotBrainfuckInstructionException(Character.toString(currentInstr));
		}
		
		s += wrtLine(instr, nbOcc);
		
		return s;
	}
	
	private String wrtLine(Instructions instr, int nbOcc){
		String s = "";
		if(this.getClass().equals(CConverter.class)){
			s += instr.getCEquivalent();
		}
		else{
			s += instr.getPhpEquivalent();
		}
		
		if(instr == Instructions.INCR || instr == Instructions.DECR || instr == Instructions.LEFT ||
				instr == Instructions.RIGHT){
			s += Integer.toString(nbOcc);
		}
		
		if(instr != Instructions.JUMP && instr != Instructions.BACK){
			s += ";";
		}
		
		s += " \r\n";
		
		return s;
	}
	
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
