package brainfuck.core.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * ReaderText est la classe qui va lire le fichier brainfuck passé avec "-p"
 * et le transformer en une chaine de caractères uniquement constituée de shortcuts.
 * 
 * Cette chaine est ensuite utilisée pour tous les traitements disponibles dans
 * cet interpréteur de brainfuck.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 2.2
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
		switch(instr){
			case "INCR" :
				constructionWithShortcut("+");
				break;
			case "DECR" :
				constructionWithShortcut("-");
				break;
			case "LEFT" :
				constructionWithShortcut("<");
				break;
			case "RIGHT" :
				constructionWithShortcut(">");
				break;
			case "OUT" :
				constructionWithShortcut(".");
				break;
			case "IN" :
				constructionWithShortcut(",");
				break;
			case "JUMP" :
				constructionWithShortcut("[");
				break;
			case "BACK" :
				constructionWithShortcut("]");
				break;
			case "" :
				break;
			default : 
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
		return c == '+' || c == '-' || c == '<' || c == '>' || c == ',' || c == '.' || c == '[' || c == ']';
	}
	
	/**
	 * indique si la ligne passée en paramètre est une ligne de shortcut
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
