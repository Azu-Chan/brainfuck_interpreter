package brainfuck.core.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import brainfuck.exceptions.ImageInterpretationErrorException;
import brainfuck.exceptions.IsNotBrainfuckInstructionException;
import brainfuck.macros.*;

/**
 * MacroReplacer est la classe qui va lire le fichier brainfuck passé avec "-p"
 * et uniquement remplacer les macros détectées par leurs valeurs respectives.
 * 
 * Un fichier temporaire assaini est créé à la fin du traitement. Ce fichier sera ensuite utilisé par le readerText.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class MacroReplacer extends ReaderBF {
	private FileInputStream prog; // prog bf
	private File progTmp; // prog bf sans macros
	
	private BufferedWriter bufOutTmp; // pour écrire sur le tmp
	
	private ArrayList<MacroDefine> diesDefine = new ArrayList<MacroDefine>(); // les define
	
	/**
	 * Constructeur...
	 * 
	 * @param p
	 * 
	 * @throws IOException
	 */
	public MacroReplacer(File p) throws IOException{
		super();
		prog = new FileInputStream(p);
		progTmp = File.createTempFile(p.getName(), ".tmp");
		
		bufOutTmp = new BufferedWriter(new FileWriter(progTmp, true));
		
		// Supprimer le fichier à la fin du programme
	    progTmp.deleteOnExit();
	}

	/**
	 * Lis le fichier ligne par ligne pour effectuer le traitement
	 * 
	 * @throws IOException
	 * @throws ImageInterpretationErrorException
	 * @throws IsNotBrainfuckInstructionException
	 */
	@Override
	public void readFile() throws IsNotBrainfuckInstructionException, ImageInterpretationErrorException, IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(prog));
		
		String line = buffer.readLine();
		
		while(line != null){
			traitementLigne(line);
			line = buffer.readLine();
		}
		
		bufOutTmp.close();
		buffer.close();
		prog.close();
	}
	
	/**
	 * Traite la ligne actuellement lue et écris le résultat sur le fichier temporaire
	 * 
	 * @param ligne
	 * 
	 * @throws IOException
	 */
	protected void traitementLigne(String ligne) throws IOException {
		
		String tLigne = ligne.replaceAll("\t", " ");
		String[] explodeLigne = tLigne.split(" ");
		
		// Mise en mémoire des define
		if(explodeLigne[0].equals(MacroDefine.NAME_MACRO)){
			if(explodeLigne.length == 3){
				diesDefine.add(new MacroDefine(explodeLigne[1], explodeLigne[2]));
			}
			return ;
		}
		
		tLigne = ligne.replaceAll("\t", "");
		explodeLigne = tLigne.split(" ");
		
		// traitement des define en memoire
		for(MacroDefine m : diesDefine){
			ligne = ligne.replace(m.getParam(), m.getEffect());
		}
		
		// traitement macros sans param
		for(MacrosSimple m : MacrosSimple.values()){
			if(explodeLigne.length == 1 && explodeLigne[0].equals(m.getName())){
				ligne = ligne.replace(explodeLigne[0], m.getEffect());
			}
		}
		
		// traitement macro avec param
		for(MacrosParam m : MacrosParam.values()){
			if(explodeLigne.length == 2 && explodeLigne[0].equals(m.getName())){
				m.setParam(explodeLigne[1]);
				ligne = ligne.replace(explodeLigne[1], "");
				ligne = ligne.replace(explodeLigne[0], m.getEffect());
			}
		}

		bufOutTmp.write(ligne + "\r\n");
	}
	
	/**
	 * Getter sur le fichier temporaire
	 * 
	 * @return le fichier temp sans macros
	 */
	public File getProgTmpFile(){
		return progTmp;
	}

}
