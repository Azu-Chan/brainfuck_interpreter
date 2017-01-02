package brainfuck.core.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import brainfuck.Instructions;
import brainfuck.exceptions.IsNotBrainfuckInstructionException;

public class CConverter extends Converter {
	private File CFile;
	private BufferedWriter CBuff;

	public CConverter(String prog, String name) {
		super(prog, name);
	}

	@Override
	protected void initialize() throws IOException {
		CFile = new File(nameProg + ".c");
		if(CFile.exists()) CFile.delete();

        CFile.createNewFile();
		CBuff = new BufferedWriter(new FileWriter(CFile, true));
	}

	@Override
	protected void writeEntete() throws IOException {
		String line;
		
		line = "#include <stdio.h> \r\n"
				+ " \r\n";
		
        CBuff.write(line);
	}

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
	
	private String getLine(char currentInstr, int nbOcc) throws IsNotBrainfuckInstructionException{
		if(currentInstr == Instructions.INCR.getShortSyntax()){
			return "\tmem[pointer] += " + nbOcc + "; \r\n";
		}
		if(currentInstr == Instructions.DECR.getShortSyntax()){
			return "\tmem[pointer] -= " + nbOcc + "; \r\n";
		}
		if(currentInstr == Instructions.LEFT.getShortSyntax()){
			return "\tpointer -= " + nbOcc + "; \r\n";
		}
		if(currentInstr == Instructions.RIGHT.getShortSyntax()){
			return "\tpointer += " + nbOcc + "; \r\n";
		}
		if(currentInstr == Instructions.OUT.getShortSyntax()){
			return "\tputchar(mem[pointer]); \r\n";
		}
		if(currentInstr == Instructions.IN.getShortSyntax()){
			return "\tmem[pointer] = getchar(); \r\n";
		}
		if(currentInstr == Instructions.JUMP.getShortSyntax()){
			return "\twhile(mem[pointer] != 0){ \r\n";
		}
		if(currentInstr == Instructions.BACK.getShortSyntax()){
			return "\t} \r\n";
		}
		throw new IsNotBrainfuckInstructionException(""+currentInstr);
	}

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
