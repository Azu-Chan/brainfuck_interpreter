package brainfuck.files;

import java.io.File;

import brainfuck.OptionInclude;
import brainfuck.parser.Parser;

/**
 * Classe dont le but est de cr�er les r�f�rences 
 * vers les diff�rents fichiers qui seront utilis�s
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class FileCreator implements OptionInclude {
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
	 * Cr�e/ouvre les fichiers sp�cifi�s
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
	 * Ouvre le fichier d'entr�e si indiqu�
	 */
	private void openInFile(){
		if(parser.getOption(nomOptI).getPresent()){
			in = new File(parser.getOption(nomOptI).getArgument());
		}
	}
	
	/**
	 * Ouvre le fichier de sortie si indiqu�
	 */
	private void openOutFile(){
		if(parser.getOption(nomOptO).getPresent()){
			out = new File(parser.getOption(nomOptO).getArgument());
		}
	}
	
	/**
	 * Ouvre le fichier log si indiqu�
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
