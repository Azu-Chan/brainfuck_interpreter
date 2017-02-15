package azuchan.brainfuck.core.services;

import java.io.*;

import azuchan.brainfuck.Instructions;
import azuchan.brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * Transforme du code brainfuck en C
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.1
 */
public class CConverter extends Converter {
	private File CFile;
	private BufferedWriter CBuff;

	/**
	 * Constructeur...
	 * 
	 * @param prog
	 * @param name
	 */
	public CConverter(String prog, String name) {
		super(prog, name);
	}

	/**
	 * Création et initialisation du fichier C
	 */
	@Override
	protected void initialize() throws IOException {
		CFile = new File(nameProg + ".c");
		if(CFile.exists()) CFile.delete();

        CFile.createNewFile();
		CBuff = new BufferedWriter(new FileWriter(CFile, true));
	}

	/**
	 * Ecriture de l'entête du fichier C
	 */
	@Override
	protected void writeEntete() throws IOException {
		String line;
		
		line = "#include <stdio.h> \r\n"
				+ " \r\n";
		
        CBuff.write(line);
	}

	/**
	 * Ecriture du corps du fichier C
	 */
	@Override
	protected void writeCorpse() throws IOException, IsNotBrainfuckInstructionException {
		String line;
		
		line = "int main(){ \r\n"
				+ " \r\n"
				+ "\tunsigned char mem[30000] = {}; \r\n"
				+ "\tint pointer = 0; \r\n"
				+ " \r\n";
		
		CBuff.write(line);
		
		int iterator = 0;
		char oldChar = '\0';
		int countFact = 1;
		
		// Concaténation d'instructions élémentaires
		while(iterator < mainProgram.length()){
			if((oldChar == Instructions.INCR.getShortSyntax() || oldChar == Instructions.DECR.getShortSyntax() || 
					oldChar == Instructions.LEFT.getShortSyntax() || oldChar == Instructions.RIGHT.getShortSyntax()) &&
					oldChar == mainProgram.charAt(iterator)){
				countFact++;
			}
			else{
				if(oldChar != '\0'){
					line = getLine(oldChar, countFact);
					CBuff.write(line);
					countFact = 1;
				}
			}
			oldChar = mainProgram.charAt(iterator);
			iterator++;
		}
		
		line = getLine(oldChar, countFact);
		CBuff.write(line);
	}

	/**
	 * Ecriture de la fin du fichier C
	 */
	@Override
	protected void writeEnd() throws IOException {
		String line;
		
		line = " \r\n"
				+ "\treturn 0; \r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "\r\n";
		
		CBuff.write(line);
		
		CBuff.close();
		
	}
	
}
