package brainfuck;

import java.util.ArrayList;
import java.util.List;

import brainfuck.exceptions.UnknowProcedureException;
import brainfuck.procedure.Function;
import brainfuck.procedure.Procedure;

public class ProgramStructure {
	private String mainInstructions;
	private int programLenght = 0;
	private List<Procedure> procedures;
	
	public ProgramStructure(){
		mainInstructions = new String("");
		procedures = new ArrayList<Procedure>();
	}
	
	public String getProgram(){
		return mainInstructions;
	}
	
	public int getProgramLenght(){
		return programLenght;
	}
	
	public void incrementProgramLenght(){
		programLenght++;
	}
	
	public void addInstruction(String instr){
		mainInstructions += instr;
		programLenght++;
	}

	public List<Procedure> getProcedures(){
		return procedures;
	}
	
	public Procedure getSpecificProcedure(String name) throws UnknowProcedureException{
		for(Procedure p : procedures){
			if(p.getName().equals(name)){
				return p;
			}
		}
		throw new UnknowProcedureException(name);
	}
	
	public void addProcedure(String name, String corps, List<Integer> arguments){
		procedures.add(new Procedure(name, corps, arguments));
	}
	
	public void addFunction(String name, String corps, List<Integer> arguments){
		procedures.add(new Function(name, corps, arguments));
	}
}
