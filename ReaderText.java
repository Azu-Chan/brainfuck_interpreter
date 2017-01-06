package brainfuck.core.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import brainfuck.Instructions;
import brainfuck.exceptions.IsNotBrainfuckInstructionException;

/**
 * ReaderText est la classe qui va lire le fichier brainfuck pass� avec "-p" apr�s application des macros
 * et le transformer en une chaine de caract�res uniquement constitu�e de shortcuts.
 * 
 * Cette chaine est ensuite utilis�e pour tous les traitements disponibles dans
 * cet interpr�teur de brainfuck.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 2.3
 */
public class ReaderText extends ReaderBF{
	private FileInputStream prog;
	private ArrayList<String> function = new ArrayList<String>();
	private ArrayList<String> contents = new ArrayList<String>();

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
	 * du BufferReader, chaque ligne sera ensuite trait�e s�par�ment
	 * d'abord par le pr�processeur puis par le traducteur.
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
	 * cette methode va transformer un raccourci de la ligne pass�e en param�tre.
	 * si un shortcut est d�tect�, la ligne est simplement ajout�e sur la cha�ne finale.
	 * G�re d�sormais l'indentation et les commentaires (++++ #Ceci est un com)
	 * 
	 * @param ligne
	 * 
	 * @throws IsNotBrainfuckInstructionException
	 */
	protected void traitementLigne(String ligne) throws IsNotBrainfuckInstructionException{
		//quand il d�tecte "function_nomFunction(){"
		if(ligne.contains("{")){
			String instr = "";
			//mettre le nom de la fonction dans la liste function
			function.add(ligne.substring(0, ligne.indexOf('{')));
			String l = ligne.split("{")[0].replaceAll("\t| ", "");
			//mettre le contenu de la fonction dans la liste contents
			contents.add(ligne.substring(0, ligne.indexOf('}')));
		} 
		//quand il d�tecte "function_nomFunction()"
		if(ligne.contains("function_")){
			String line = ligne.substring(0, ligne.indexOf('(')-1);
			if(function.contains(line)){
				for(int i=0; i<function.length; i++){
					if(function[i] == line){
						
					}
				}
			}
		}
		
		//         On vire le com   // On vire l'indentation
		if(ligne.equals("#")){
			ligne = " "; // pour �viter un split qui ne marcherait pas...
		}
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
	 * l'injecter dans la cha�ne � l'aide de constructionWithShortcut(String s)
	 * 
	 * @param instr
	 * 
	 * @throws IsNotBrainfuckInstructionException
	 */
	private void constructionWithInstr(String instr) throws IsNotBrainfuckInstructionException{
		if(isInstruction(instr)){
			for(Instructions i : Instructions.values()){
				if(i.getLongSyntax().equals(instr)){
					constructionWithShortcut(""+i.getShortSyntax());
				}
			}
		}
		else{
			throw new IsNotBrainfuckInstructionException(instr);
		}
	}
	
	/**
	 * indique si le caract�re pass� en param�tre est un raccourci
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
	 * indique si la cha�ne pass�e en param�tre est unne instruction longue
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
	 * indique si la ligne pass�e en param�tre est une ligne de shortcut
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