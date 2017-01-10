package brainfuck.files;

import java.io.File;

import static brainfuck.GlobalConstantes.*;
import brainfuck.parser.Parser;

/**
 * Classe dont le but est de créer les références 
 * vers les différents fichiers qui seront utilisés
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class FileCreator {
	private Parser parser;
	
	private File prog = null;
	private File in = null;
	private File out = null;
	private File log = null;
	
	/**
	 * Constructeur
	 * 
	 * @param p
	 */
	public FileCreator(Parser p){
		this.parser = p;
	}
	
	/**
	 * Crée/ouvre les fichiers spécifiés
	 */
	public void openFiles(){
		openProgFile();
		openInFile();
		openOutFile();
		openLogFile();
	}
	
	/**
	 * Ouvre le fichier programme brainfuck
	 */
	private void openProgFile(){
		prog = new File(parser.getOption(nomOptP).getArgument());
	}
	
	/**
	 * Ouvre le fichier d'entrée si indiqué
	 */
	private void openInFile(){
		if(parser.getOption(nomOptI).getPresent()){
			in = new File(parser.getOption(nomOptI).getArgument());
		}
	}
	
	/**
	 * Ouvre le fichier de sortie si indiqué
	 */
	private void openOutFile(){
		if(parser.getOption(nomOptO).getPresent()){
			out = new File(parser.getOption(nomOptO).getArgument());
		}
	}
	
	/**
	 * Ouvre le fichier log si indiqué
	 */
	private void openLogFile(){
		if(parser.getOption(nomOptTrace).getPresent()){
			log = new File(parser.getOption("-p").getArgument() + ".log");
		}
	}
	
	/**
	 * getter du fichier programme
	 * 
	 * @return fichier programme
	 */
	public File getProgFile(){
		return prog;
	}
	
	/**
	 * getter du fichier in
	 * 
	 * @return fichier in
	 */
	public File getInFile(){
		return in;
	}
	
	/**
	 * getter du fichier out
	 * 
	 * @return fichier out
	 */
	public File getOutFile(){
		return out;
	}
	
	/**
	 * getter du fichier log
	 * 
	 * @return fichier log
	 */
	public File getLogFile(){
		return log;
	}

}
