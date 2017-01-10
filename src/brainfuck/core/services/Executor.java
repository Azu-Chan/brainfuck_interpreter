package brainfuck.core.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import brainfuck.Instructions;
import brainfuck.Metrics;

import brainfuck.exceptions.InputMissingFileException;
import brainfuck.exceptions.IsNotBrainfuckInstructionException;
import brainfuck.exceptions.OutOfMemoryException;
import brainfuck.exceptions.OverflowException;

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
	private String prog;
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
	public Executor(String p, File streamIn, File streamOut) throws InputMissingFileException, FileNotFoundException{
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
	public Executor(String p, File streamIn, File streamOut, TraceLog traceLog) throws InputMissingFileException, FileNotFoundException{
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
	 */
	public void executeProg() throws IOException, OverflowException, OutOfMemoryException, IsNotBrainfuckInstructionException{
		fillJumpTable();
		
		if(traceLog != null){
			traceLog.createTableLog();
		}
		
		Metrics.setExecTimeDeb(); // UPDATE METRIC
		
		for(execPointer = 0; execPointer < prog.length(); execPointer++){
			Metrics.incrementExecMove(); // UPDATE METRIC
			
    		executeInstruction(prog.charAt(execPointer));
    		
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
	 */

	private void executeInstruction(char instr) throws IOException, OverflowException, OutOfMemoryException, IsNotBrainfuckInstructionException {
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
		throw new IsNotBrainfuckInstructionException(""+instr);
	}
	
	/**
	 * Pour lancer la procédure de remplissage de la jumpTable
	 * Passage en complexité N au lieu de N^n
	 */
	private void fillJumpTable(){
		Stack<Integer> stockageTemp = new Stack<Integer>();
		
		for(execPointer = 0; execPointer < prog.length(); execPointer++){
			if(prog.charAt(execPointer) == '['){
				stockageTemp.push(new Integer(execPointer));
			}
			if(prog.charAt(execPointer) == ']'){
				jumpTable.createBridge(stockageTemp.pop(), execPointer);
			}
		}
	}
	
	/**
	 * Permet d'effectuer un jump
	 * 
	 * @deprecated
	 */
	private void jump(){
		if(memory.getPointedValue() == Byte.MIN_VALUE){
			int parentheseCount = 0;
			while(execPointer < prog.length()){
				execPointer++;
				if(prog.charAt(execPointer) == '['){
					parentheseCount++;
				}
				if(prog.charAt(execPointer) == ']' && (parentheseCount == 0)){
					return ;
				}
				if(prog.charAt(execPointer) == ']' && (parentheseCount != 0)){
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
	private void back(){
		if(memory.getPointedValue() != Byte.MIN_VALUE){
			int parentheseCount = 0;
			while(execPointer >= 0){
				execPointer--;
				if(prog.charAt(execPointer) == ']'){
					parentheseCount++;
				}
				if(prog.charAt(execPointer) == '[' && (parentheseCount == 0)){
					return ;
				}
				if(prog.charAt(execPointer) == '[' && (parentheseCount != 0)){
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
