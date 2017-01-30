package Azu_Chan.brainfuck.core.services;

import java.io.*;

import Azu_Chan.brainfuck.Instructions;
import Azu_Chan.brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * Transforme du code brainfuck en PHP
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class PHPConverter extends Converter {
	private File PHPFile;
	private BufferedWriter PHPBuff;

	/**
	 * Constructeur...
	 * 
	 * @param prog
	 * @param name
	 */
	public PHPConverter(String prog, String name) {
		super(prog, name);
	}

	/**
	 * Création et initialisation du fichier C
	 */
	@Override
	protected void initialize() throws IOException {
		PHPFile = new File(nameProg + ".php");
		if(PHPFile.exists()) PHPFile.delete();

        PHPFile.createNewFile();
		PHPBuff = new BufferedWriter(new FileWriter(PHPFile, true));
	}

	/**
	 * Ecriture de l'entête du fichier C
	 */
	@Override
	protected void writeEntete() throws IOException {
		String line;
		
		line = "<?php \r\n"
				+ "$STDIN = fopen('php://stdin', 'r'); \r\n"
				+ " \r\n";
		
        PHPBuff.write(line);
	}

	/**
	 * Ecriture du corps du fichier PHP
	 */
	@Override
	protected void writeCorpse() throws IOException, IsNotBrainfuckInstructionException {
		String line;
		
		line = "\t for($i=0;$i<30000;$i++){ \r\n"
				+ "\t\t$mem[$i] = 0; \r\n"
				+ "\t} \r\n"
				+ "\t$pointer = 0; \r\n"
				+ " \r\n";
		
		PHPBuff.write(line);
		
		int iterator = 0;
		char oldChar = '\0';
		int countFact = 1;
		
		while(iterator < mainProgram.length()){
			if((oldChar == Instructions.INCR.getShortSyntax() || oldChar == Instructions.DECR.getShortSyntax() || 
					oldChar == Instructions.LEFT.getShortSyntax() || oldChar == Instructions.RIGHT.getShortSyntax()) &&
					oldChar == mainProgram.charAt(iterator)){
				countFact++;
			}
			else{
				if(oldChar != '\0'){
					line = getLine(oldChar, countFact);
					PHPBuff.write(line);
					countFact = 1;
				}
			}
			oldChar = mainProgram.charAt(iterator);
			iterator++;
		}
		
		line = getLine(oldChar, countFact);
		PHPBuff.write(line);
	}
	
	/**
	 * transformation de l'instruction brainfuck en PHP
	 * 
	 * @param currentInstr
	 * @param nbOcc
	 * 
	 * @return ligne de PHP à écrire dans le fichier
	 */
	private String getLine(char currentInstr, int nbOcc) throws IsNotBrainfuckInstructionException{
		if(currentInstr == Instructions.INCR.getShortSyntax()){
			return "\t$mem[$pointer] += " + nbOcc + "; \r\n";
		}
		if(currentInstr == Instructions.DECR.getShortSyntax()){
			return "\t$mem[$pointer] -= " + nbOcc + "; \r\n";
		}
		if(currentInstr == Instructions.LEFT.getShortSyntax()){
			return "\t$pointer -= " + nbOcc + "; \r\n";
		}
		if(currentInstr == Instructions.RIGHT.getShortSyntax()){
			return "\t$pointer += " + nbOcc + "; \r\n";
		}
		if(currentInstr == Instructions.OUT.getShortSyntax()){
			return "\techo chr($mem[$pointer]); \r\n";
		}
		if(currentInstr == Instructions.IN.getShortSyntax()){
			return "\t$mem[$pointer] = substr(fgets($STDIN), $pointer, 1); \r\n";
		}
		if(currentInstr == Instructions.JUMP.getShortSyntax()){
			return "\twhile($mem[$pointer] != 0){ \r\n";
		}
		if(currentInstr == Instructions.BACK.getShortSyntax()){
			return "\t} \r\n";
		}
		throw new IsNotBrainfuckInstructionException(""+currentInstr);
	}

	/**
	 * Ecriture de la fin du fichier PHP
	 */
	@Override
	protected void writeEnd() throws IOException {
		String line;
		
		line = "?> \r\n"
				+ "\r\n";
		
		PHPBuff.write(line);
		
		PHPBuff.close();
		
	}
	
}
