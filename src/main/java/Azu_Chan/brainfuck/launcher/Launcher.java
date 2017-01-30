package Azu_Chan.brainfuck.launcher;

/**
 * Launcher contenant la méthode main
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 5.0
 */
public class Launcher {

	/**
	 * Simple main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ProgramStarter start = new ProgramStarter(args);
		start.start();
		
		System.exit(0); // Aucune erreur
	}

}