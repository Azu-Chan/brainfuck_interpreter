package azuchan.brainfuck.launcher;

/**
 * Launcher contenant la méthode main
 * 
 * @author Dylan Ritrovato
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