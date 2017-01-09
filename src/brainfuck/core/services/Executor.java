package brainfuck.core.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import brainfuck.Instructions;
import brainfuck.Metrics;
import brainfuck.ProgramStructure;
import brainfuck.exceptions.InputMissingFileException;
import brainfuck.exceptions.IsNotBrainfuckInstructionException;
import brainfuck.exceptions.OutOfMemoryException;
import brainfuck.exceptions.OverflowException;
import brainfuck.exceptions.UnknowProcedureException;
import brainfuck.exceptions.WrongProcedureDeclarationException;

@SuppressWarnings("unused")

/**
 * Classe qui va interpréter et exécuter le programme brainfuck
 * déjà traduit en syntaxe courte.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 4.1
 */
public class Executor{
	private DataCompute memory;
	private ProgramStructure prog;
	private FileInputStream in = null;
	private FileOutputStream out = null;
	
	private int execPointer;
	
	// Ajout de la JumpTable pour "optimiser" les JUMP et BACK
	private JumpTable jumpTable;
	
	private TraceLog traceLog = null;

    /**
	 * Constructeur d'un Executor.
	 * 
	 * @param p
	 * @param streamIn
	 * @param streamOut
	 * 
     * @throws InputMissingFileException 
     * @throws FileNotFoundException 
	 */
	public Executor(ProgramStructure p, File streamIn, File streamOut) throws InputMissingFileException, FileNotFoundException{
		prog = p;
		if(streamIn != null){
			try {
				in = new FileInputStream(streamIn); // null si entrée standart
			} catch (FileNotFoundException e) {
				throw new InputMissingFileException(streamIn.getName());
			} 
		}
		if(streamOut != null){
			out = new FileOutputStream(streamOut); // null si sortie standart
		}
		memory = new DataCompute();
		jumpTable = new JumpTable();
		
		execPointer = 0;
	}
	
	/**
	 * Constructeur d'un Executor.
	 * 
	 * @param p
	 * @param streamIn
	 * @param streamOut
	 * @param traceLog
	 * 
     * @throws InputMissingFileException 
     * @throws FileNotFoundException 
	 */
	public Executor(ProgramStructure p, File streamIn, File streamOut, TraceLog traceLog) throws InputMissingFileException, FileNotFoundException{
		this(p, streamIn, streamOut);
		this.traceLog = traceLog;
	}
	
	/**
	 * cette methode va exécuter la chaîne de caractères contenant
	 * le programme brainfuck
	 * 
	 * @throws IOException 
	 * @throws OutOfMemoryException 
	 * @throws OverflowException 
	 * @throws IsNotBrainfuckInstructionException 
	 * @throws UnknowProcedureException 
	 * @throws WrongProcedureDeclarationException 
	 */
	public void executeProg() throws IOException, OverflowException, OutOfMemoryException, IsNotBrainfuckInstructionException, UnknowProcedureException, WrongProcedureDeclarationException{
		fillJumpTable();
		
		if(traceLog != null){
			traceLog.createTableLog();
		}
		
		Metrics.setExecTimeDeb(); // UPDATE METRIC
		
		for(execPointer = 0; execPointer < prog.getProgram().length(); execPointer++){
			Metrics.incrementExecMove(); // UPDATE METRIC
			
    		executeInstruction(prog.getProgram().charAt(execPointer));
    		
    		if(traceLog != null){
    			traceLog.writeLineTableLog(execPointer, memory.getPointerPos(), (byte)(memory.getPointedValue()+128));
    		}
    	}
		
		Metrics.setExecTime(); // UPDATE METRIC
		
		if(in != null){
			in.close();
		}
		if(out != null){
			out.close();
		}
	}

	/**
	 * Cette méthode va executer l'instruction passée en paramètre
	 *
	 * @param instr
	 * 
	 * @throws IOException 
	 * @throws OverflowException 
	 * @throws OutOfMemoryException 
	 * @throws IsNotBrainfuckInstructionException 
	 * @throws UnknowProcedureException 
	 * @throws WrongProcedureDeclarationException 
	 */

	private void executeInstruction(char instr) throws IOException, OverflowException, OutOfMemoryException, IsNotBrainfuckInstructionException, UnknowProcedureException, WrongProcedureDeclarationException {
		if(instr == Instructions.INCR.getShortSyntax()){
			Metrics.incrementDataWrite(); // UPDATE METRIC
			memory.increment();
			return ;
		}
		if(instr == Instructions.DECR.getShortSyntax()){
			Metrics.incrementDataWrite(); // UPDATE METRIC
			memory.decrement();
			return ;
		}
		if(instr == Instructions.LEFT.getShortSyntax()){
			Metrics.incrementDataMove(); // UPDATE METRIC
			memory.pointerLeft();
			return ;
		}
		if(instr == Instructions.RIGHT.getShortSyntax()){
			Metrics.incrementDataMove(); // UPDATE METRIC
			memory.pointerRight();
			return ;
		}
		if(instr == Instructions.OUT.getShortSyntax()){
			Metrics.incrementDataRead(); // UPDATE METRIC
			memory.output(out);
			return ;
		}
		if(instr == Instructions.IN.getShortSyntax()){
			Metrics.incrementDataWrite(); // UPDATE METRIC
			memory.input(in);
			return ;
		}
		if(instr == Instructions.JUMP.getShortSyntax()){
			Metrics.incrementDataRead(); // UPDATE METRIC
			bound('[');
			return ;
		}
		if(instr == Instructions.BACK.getShortSyntax()){
			Metrics.incrementDataRead(); // UPDATE METRIC
			bound(']');
			return ;
		}
		if(instr == '@'){
			executeFonction();
		}
		throw new IsNotBrainfuckInstructionException(""+instr);
	}
	
	private void executeFonction() throws UnknowProcedureException, WrongProcedureDeclarationException, IOException, OverflowException, OutOfMemoryException, IsNotBrainfuckInstructionException {
		class ExecutorFunctions{
			DataCompute mem;
			String functionCorpse;
			int pointer;
			Object container;
			
			ExecutorFunctions(String instructions, Object container){
				memory = new DataCompute();
				functionCorpse = instructions;
				pointer = 0;
				this.container = container;
			}
			
			void executeFonc() throws IOException, OverflowException, OutOfMemoryException, IsNotBrainfuckInstructionException, UnknowProcedureException, WrongProcedureDeclarationException{
				for(pointer = 0; pointer < functionCorpse.length(); pointer++){
					executeInstruction(functionCorpse.charAt(pointer));
				}
			}
			
			void executeInstruction(char instr) throws IOException, OverflowException, OutOfMemoryException, IsNotBrainfuckInstructionException, UnknowProcedureException, WrongProcedureDeclarationException {
				if(instr == Instructions.INCR.getShortSyntax()){
					mem.increment();
					return ;
				}
				if(instr == Instructions.DECR.getShortSyntax()){
					mem.decrement();
					return ;
				}
				if(instr == Instructions.LEFT.getShortSyntax()){
					mem.pointerLeft();
					return ;
				}
				if(instr == Instructions.RIGHT.getShortSyntax()){
					mem.pointerRight();
					return ;
				}
				if(instr == Instructions.OUT.getShortSyntax()){
					mem.output(out);
					return ;
				}
				if(instr == Instructions.IN.getShortSyntax()){
					mem.input(in);
					return ;
				}
				if(instr == Instructions.JUMP.getShortSyntax()){
					jump(mem, functionCorpse, pointer);
					return ;
				}
				if(instr == Instructions.BACK.getShortSyntax()){
					back(mem, functionCorpse, pointer);
					return ;
				}
				if(instr == '@'){
					String nameF = "";
					ArrayList<Integer> paramsF = new ArrayList<Integer>();
					pointer++;
					while(functionCorpse.charAt(execPointer) != '('){
						nameF += functionCorpse.charAt(execPointer);
						pointer++;
					}
					String tmp = "";
					while(functionCorpse.charAt(pointer) != ')'){
						if(functionCorpse.charAt(pointer) == ';'){
							paramsF.add(Integer.valueOf(tmp));
							tmp = "";
						}
						else{
							tmp += functionCorpse.charAt(pointer);
						}
						pointer++;
					}
					paramsF.add(Integer.valueOf(tmp));
					tmp = "";
					
					if(paramsF.size() == prog.getSpecificProcedure(nameF).getNbParameter()){
						ExecutorFunctions ex = new ExecutorFunctions(prog.getSpecificProcedure(nameF).getContents(), this);
						for(int k = 0; k < paramsF.size(); k++){
							ex.mem.setValue(prog.getSpecificProcedure(nameF).getParameter(k), this.mem.getValue(paramsF.get(k)));
						}
						ex.executeFonc();
					}
					else{
						throw new WrongProcedureDeclarationException(nameF);
					}
				}
				if(instr == '$'){
					if(container.getClass().equals(ExecutorFunctions.class)){
						((ExecutorFunctions)container).mem.setValue(((ExecutorFunctions)container).pointer, mem.getPointedValue());
						pointer = functionCorpse.length()-1;
					}
					else{
						((Executor)container).memory.setValue(((Executor)container).execPointer, mem.getPointedValue());
						pointer = functionCorpse.length()-1;
					}
				}
				throw new IsNotBrainfuckInstructionException(""+instr);
			}
			
		}
		
		String nameF = "";
		ArrayList<Integer> paramsF = new ArrayList<Integer>();
		execPointer++;
		while(prog.getProgram().charAt(execPointer) != '('){
			nameF += prog.getProgram().charAt(execPointer);
			execPointer++;
		}
		String tmp = "";
		while(prog.getProgram().charAt(execPointer) != ')'){
			if(prog.getProgram().charAt(execPointer) == ';'){
				paramsF.add(Integer.valueOf(tmp));
				tmp = "";
			}
			else{
				tmp += prog.getProgram().charAt(execPointer);
			}
			execPointer++;
		}
		paramsF.add(Integer.valueOf(tmp));
		tmp = "";
		
		if(paramsF.size() == prog.getSpecificProcedure(nameF).getNbParameter()){
			ExecutorFunctions ex = new ExecutorFunctions(prog.getSpecificProcedure(nameF).getContents(), this);
			for(int k = 0; k < paramsF.size(); k++){
				ex.mem.setValue(prog.getSpecificProcedure(nameF).getParameter(k), this.memory.getValue(paramsF.get(k)));
			}
			ex.executeFonc();
		}
		else{
			throw new WrongProcedureDeclarationException(nameF);
		}
	}

	/**
	 * Pour lancer la procédure de remplissage de la jumpTable
	 * PASSAGE en complexité N au lieu de N^n
	 */
	private void fillJumpTable(){
		Stack<Integer> stockageTemp = new Stack<Integer>();
		
		for(execPointer = 0; execPointer < prog.getProgram().length(); execPointer++){
			if(prog.getProgram().charAt(execPointer) == '['){
				stockageTemp.push(new Integer(execPointer));
			}
			if(prog.getProgram().charAt(execPointer) == ']'){
				jumpTable.createBridge(stockageTemp.pop(), execPointer);
			}
		}
	}
	
	/**
	 * Permet d'effectuer un jump
	 * 
	 * @deprecated
	 */
	private void jump(DataCompute me, String pr, int ptr){
		if(me.getPointedValue() == Byte.MIN_VALUE){
			int parentheseCount = 0;
			while(ptr < pr.length()){
				ptr++;
				if(pr.charAt(ptr) == '['){
					parentheseCount++;
				}
				if(pr.charAt(ptr) == ']' && (parentheseCount == 0)){
					return ;
				}
				if(pr.charAt(ptr) == ']' && (parentheseCount != 0)){
					parentheseCount--;
				}
			}
		}
	}
	
	/**
	 * Permet d'effectuer un back
	 * 
	 * @deprecated
	 */
	private void back(DataCompute me, String pr, int ptr){
		if(me.getPointedValue() != Byte.MIN_VALUE){
			int parentheseCount = 0;
			while(ptr >= pr.length()){
				ptr--;
				if(pr.charAt(ptr) == ']'){
					parentheseCount++;
				}
				if(pr.charAt(ptr) == '[' && (parentheseCount == 0)){
					return ;
				}
				if(pr.charAt(ptr) == '[' && (parentheseCount != 0)){
					parentheseCount--;
				}
			}
		}
	}
	
	/**
	 * Permet d'effectuer un saut général d'instruction
	 * (combine le jump et le back)
	 * 
	 * @param instr
	 */
	private void bound(char instr){
		if((memory.getPointedValue() == Byte.MIN_VALUE && instr == '[') || (memory.getPointedValue() != Byte.MIN_VALUE && instr == ']')){
			execPointer = jumpTable.getNewExecPointerValue(execPointer);
		}
	}
	
	/**
	 * Renvoie une référence sur la grille, utilisé à des fins
	 * d'affichage.
	 * 
	 * @return Référence sur la mémoire brainfuck de l'exécution courante.
	 */
	public byte[] getMemory(){
		return memory.getGrille();
	}
	
	/**
	 * Classe interne Bridge qui a pour nécessité de représenter les sauts
	 * effectués via les JUMP et les BACK
	 * 
	 * @author Dylan Ritrovato
	 * @author Yijie Wang
	 * @author Mohd Nijab
	 * 
	 * @version 1.0
	 */
	private static class JumpTable{
		private HashMap<Integer, Integer> bridgeAgregat;
		
		/**
		 * Constructeur de la JumpTable
		 */
		private JumpTable(){
			bridgeAgregat = new HashMap<Integer, Integer>();
		}
		
		/**
		 * Créé un bridge pour sauter des instructions
		 * 
		 * @param positionJump
		 * @param positionBack
		 */
		private void createBridge(int positionJump, int positionBack){
			bridgeAgregat.put(new Integer(positionJump), new Integer(positionBack));
			bridgeAgregat.put(new Integer(positionBack), new Integer(positionJump));
		}
		
		/**
		 * Renvoie la nouvelle valeur de l'execPointer suite à son saut
		 * 
		 * @param currentPos
		 * 
		 * @return la position à atteindre
		 */
		private int getNewExecPointerValue(int currentPos){
			return bridgeAgregat.get(currentPos);
		}
		
		/**
		 * Affiche tous les Bridge
		 * 
		 * POUR DES FINS DE DEBUG
		 */
		private void affich(){
			bridgeAgregat.forEach((k,v)-> 
				System.out.println(k+" , "+v+"\n")
			);
		}
	}
}
