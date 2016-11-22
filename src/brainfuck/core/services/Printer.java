package brainfuck.core.services;

/**
 * Printer est la classe n�cessaire pour afficher l'�tat de la grille
 * sur la sortie standard selon plusieurs modes de vue.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.2
 */
public class Printer {
	private byte[] grille;
	
	/**
	 * Constructeur du printer.
	 * @param memoire
	 */
	public Printer(byte[] memoire){
		grille = memoire;
	}
	
	/**
	 * Affiche c�te a c�te toutes les valeurs
	 * (de 0 � 255) de la grille s�par�es par
	 * des espaces.
	 * exemple :
	 * 0 0 0 0 0 ...
	 * 
	 * @deprecated
	 */
	public void consolePrintByte(){
		System.out.println();
		for (int i = 0; i < grille.length; i++) {
			System.out.print((grille[i] + 128) + " ");
		}
	}
	
	/**
	 * Affiche les valeurs non nulles (de 1 � 255)
	 * converties en char de la grille c�te � c�te.
	 * exemple :
	 * Hello, World !
	 * 
	 * @deprecated
	 */
	public void consolePrintChar(){
		System.out.println();
		for (int i = 0; i < grille.length; i++) {
			if(grille[i] != -128){
				System.out.print((char)(grille[i] + 128));
			}
		}
	}
	
	/**
	 * Affiche les valeurs non nulles (de 1 � 255)
	 * sur la sortie standart
	 * de la grille de cette forme par exemple :
	 * c0 : 1
	 * c3 : 78
	 * c999 : 5
	 * ...
	 */
	public void consolePrintCell(){
		System.out.println();
		for (int i = 0; i < grille.length; i++) {
			if(grille[i] != -128){
				System.out.println("c" + i + " : " + (grille[i] + 128) + " ");
			}
		}
	}
	
	/**
	 * Construit le snapshot de la rille pour le logFile.
	 */
	public String consolePrintCellForLog(){
		String s = "";
		for (int i = 0; i < grille.length; i++) {
			if(grille[i] != -128){
				s += "c" + i + " : " + (grille[i] + 128) + "\r\n";
			}
		}
		return s;
	}

}
