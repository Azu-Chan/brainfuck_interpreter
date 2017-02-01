package Azu_Chan.brainfuck.core.reader;

import java.io.*;

import Azu_Chan.brainfuck.Instructions;
import Azu_Chan.brainfuck.exceptions.*;;

/**
 * ReaderText est la classe qui va lire le fichier brainfuck passé avec "-p" après application des macros
 * et le transformer en une chaine de caractères uniquement constituée de shortcuts.
 * 
 * Cette chaine est ensuite utilisée pour tous les traitements disponibles dans
 * cet interpréteur de brainfuck.
 * 
 * @author Dylan Ritrovato
 * 
 * @version 2.4
 */
public class ReaderText extends ReaderBF{
	private FileInputStream prog;

	/**
	 * Constructeur d'un ReaderBF.
	 * 
	 * @param streamProg
	 * @throws FileNotFoundException 
	 */
	public ReaderText(File streamProg) throws FileNotFoundException {
		super();
		prog = new FileInputStream(streamProg);
	}
	
	/**
	 * cette methode va lire les lignes du programme par le biais
	 * du BufferReader, chaque ligne sera ensuite traitée séparément
	 * d'abord par le préprocesseur puis par le traducteur.
	 * 
	 * @throws IOException 
	 * @throws IsNotBrainfuckInstructionException 
	 */
	@Override
	public void readFile() throws IOException, IsNotBrainfuckInstructionException{
		BufferedReader buffer = new BufferedReader(new InputStreamReader(prog));
		String line = buffer.readLine();
		
		while(line != null){
			traitementLigne(line);
			line = buffer.readLine();
		}
		
		buffer.close();
		prog.close();
	}

	/**
	 * cette methode va transformer un raccourci de la ligne passée en paramètre.
	 * si un shortcut est détecté, la ligne est simplement ajoutée sur la chaîne finale.
	 * Gère désormais l'indentation et les commentaires (++++ #Ceci est un com)
	 * 
	 * @param ligne
	 * 
	 * @throws IsNotBrainfuckInstructionException
	 */
	protected void traitementLigne(String ligne) throws IsNotBrainfuckInstructionException{
		if(ligne.equals("#")){
			ligne = " "; // pour éviter un split qui ne marcherait pas...
		}
		//         On vire le com   // On vire l'indentation
		String l = ligne.split("#")[0].replaceAll("\t| ", "");
		
		if(isLigneShortcut(l)){
			String instr = "";
			for(int i = 0; i < l.length(); i++){
				instr += (l.charAt(i));
				constructionWithShortcut(instr);
				instr = "";
			}
		}
		else{
			constructionWithInstr(l);
		}
	}
	
	/**
	 * cette methode va transformer l'instruction en raccourci puis
	 * l'injecter dans la chaîne à l'aide de constructionWithShortcut(String s)
	 * 
	 * @param instr
	 * 
	 * @throws IsNotBrainfuckInstructionException
	 */
	private void constructionWithInstr(String instr) throws IsNotBrainfuckInstructionException{
		if(isInstruction(instr)){
			for(Instructions i : Instructions.values()){
				if(i.getLongSyntax().equals(instr)){
					constructionWithShortcut(String.valueOf(i.getShortSyntax()));
				}
			}
		}
		else{
			throw new IsNotBrainfuckInstructionException(instr);
		}
	}
	
	/**
	 * indique si le caractère passé en paramètre est un raccourci
	 * 
	 * @param c
	 * 
	 * @return booleen
	 */
	private boolean isShortcut(char c){
		for(Instructions i : Instructions.values()){
			if(i.getShortSyntax() == c){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * indique si la chaîne passée en paramètre est une instruction longue
	 * 
	 * @param s
	 * 
	 * @return booleen
	 */
	private boolean isInstruction(String s){
		for(Instructions i : Instructions.values()){
			if(i.getLongSyntax().equals(s)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * indique si la ligne passée en paramètre est une ligne de shortcuts
	 * 
	 * @param l
	 * 
	 * @return booleen
	 */
	private boolean isLigneShortcut(String l){
		for(int i = 0; i < l.length(); i++){
			if(!(isShortcut(l.charAt(i)) || l.charAt(i) == ' ' || l.charAt(i) == '\t')) return false;
		}
		return true;
	}
}
