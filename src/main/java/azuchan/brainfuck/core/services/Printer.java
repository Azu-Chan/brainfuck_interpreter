package azuchan.brainfuck.core.services;

import java.util.concurrent.TimeUnit;

import azuchan.brainfuck.Metrics;

/**
 * Printer est la classe nécessaire pour afficher l'état de la grille
 * sur la sortie standard selon plusieurs modes de vue.
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.3
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
	 * Affiche côte a côte toutes les valeurs
	 * (de 0 à 255) de la grille séparées par
	 * des espaces.
	 * exemple :
	 * 0 0 0 0 0 ...
	 * 
	 * @deprecated (Old)
	 */
	@Deprecated
	public void consolePrintByte(){
		System.out.println();
		for (int i = 0; i < grille.length; i++) {
			System.out.print((grille[i] + 128) + " ");
		}
	}
	
	/**
	 * Affiche les valeurs non nulles (de 1 à 255)
	 * converties en char de la grille côte à côte.
	 * exemple :
	 * Hello, World !
	 * 
	 * @deprecated (old)
	 */
	@Deprecated
	public void consolePrintChar(){
		System.out.println();
		for (int i = 0; i < grille.length; i++) {
			if(grille[i] != -128){
				System.out.print((char)(grille[i] + 128));
			}
		}
	}
	
	/**
	 * Affiche les valeurs non nulles (de 1 à 255)
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
	 * Rend un joli String pour les metrics
	 * 
	 * @return les metrics formatées
	 */
	public String getMetricsFormated(){	
		return "\n\nPROGRAM METRICS ARE: \n" +
				"progSize  = " + Metrics.getProgSize() + "\n" +
				"execTime  = " + TimeUnit.MILLISECONDS.convert(Metrics.getExecTime(), TimeUnit.NANOSECONDS) + " milliseconds\n" +
				"execMove  = " + Metrics.getExecMove() + "\n" +
				"dataMove  = " + Metrics.getDataMove() + "\n" +
				"dataWrite = " + Metrics.getDataWrite() + "\n" +
				"dataRead  = " + Metrics.getDataRead() + "\n";
	}
	
	/**
	 * Construit le snapshot de la grille pour le logFile.
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
