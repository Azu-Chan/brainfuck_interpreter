package Azu_Chan.brainfuck.launcher;

import Azu_Chan.brainfuck.core.services.*;
import Azu_Chan.brainfuck.exceptions.GestionnaireExceptions;
import static Azu_Chan.brainfuck.GlobalConstantes.*;
import Azu_Chan.brainfuck.core.reader.*;
import Azu_Chan.brainfuck.files.FileCreator;
import Azu_Chan.brainfuck.parser.Parser;

import java.util.Scanner;

import Azu_Chan.brainfuck.Metrics;

/**
 * Classe dont le but est de faire se dérouler le programme
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class ProgramStarter {
	private String[] argsMain;
	
	/**
	 * Constructeur
	 * 
	 * @param argsMain
	 */
	public ProgramStarter(String[] argsMain){
		this.argsMain = argsMain;
	}
	
	/**
	 * Lance l'exécution du prtogramme principal
	 */
	public void start(){
		try{
			Parser p = new Parser(argsMain);
			p.parser();
			
			FileCreator f = new FileCreator(p);
			f.openFiles();
			
			ReaderBF r;
			if(f.getProgFile().getName().endsWith(".bmp") || f.getProgFile().getName().endsWith(".BMP")){
				r = new ReaderImage(f.getProgFile());
			}
			else{
				r = new MacroReplacer(f.getProgFile());
				r.readFile();
				r = new ReaderText(((MacroReplacer) r).getProgTmpFile());
			}
			r.readFile();
			
			Metrics.setProgSize(r.getShortSyntax().length()); // UPDATE METRIC
			
			executeServices(p, f, r.getShortSyntax());
		}
		catch(Exception e){
			GestionnaireExceptions g = new GestionnaireExceptions(e);
			g.traiterException();
		}
	}
	
	/**
	 * Exécute les différents services en fonction des arguments
	 * la shortSyntax passée en argument est FORCEMENT correcte
	 * suite à sa vérification plus tôt (readFile)
	 * 
	 * @param p
	 * @param f
	 * @param shortSyntax
	 */
	public void executeServices(Parser p, FileCreator f, String shortSyntax){
		try{
			if(!p.getOption(nomOptRewrite).getPresent() && !p.getOption(nomOptCheck).getPresent() && !p.getOption(nomOptTranslate).getPresent()
					&& !p.getOption(nomOptConvert).getPresent()){
				Checker c = new Checker(shortSyntax);
				c.verify();
				if(c.isWellFormed()){
					Executor ex;
					TraceLog t = null;
					if(p.getOption(nomOptTrace).getPresent()){
						t = new TraceLog(f.getLogFile());
						t.initializeLogFile();
						ex = new Executor(shortSyntax, f.getInFile(), f.getOutFile(), t);
					}
					else{
						ex = new Executor(shortSyntax, f.getInFile(), f.getOutFile());
					}
					ex.executeProg();
					
					Printer pr = new Printer(ex.getMemory());
					
					if(t != null){
						t.writeSnapMemory(pr.consolePrintCellForLog());
						t.writeMetrics(pr.getMetricsFormated());
						t.writeEndOfLog();
		            	System.out.println("Log généré : " + f.getLogFile().getName());
					}
					
					pr.consolePrintCell();
					System.out.println(pr.getMetricsFormated().toString());
				}
			}
			else{
				if(p.getOption(nomOptCheck).getPresent()){
					Checker c = new Checker(shortSyntax);
					c.verify();
					
					if(c.isWellFormed()){
						System.out.println("Programme correct syntaxiquement.");
					}
				}
				if(p.getOption(nomOptRewrite).getPresent()){
					System.out.println(shortSyntax.toString());
				}
				if(p.getOption(nomOptTranslate).getPresent()){
					ImageTranslator i = new ImageTranslator(shortSyntax);
					i.createImageProg();
					
					i.createImage(f.getProgFile().getName());
					System.out.println("Fichier d'image programme généré : " + f.getProgFile().getName() + ".bmp");
				}
				if(p.getOption(nomOptConvert).getPresent()){
					Converter c;
					
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(System.in);
					// Invite de saisie
		            System.out.print("\nSaisir le langage voulu (" + languageC + " " + languagePHP + ") ou " + annul + " pour annuler\n> ");
		            String word;
		            do{
		            	word = scan.next();
		            	scan.nextLine();
		            }while (word == null || (!word.equals(languageC) && !word.equals(languagePHP) && !word.equals(annul)));
		            
		            if(word.equals(languageC)){
		            	c = new CConverter(shortSyntax, p.getOption(nomOptP).getArgument());
		            	c.launchProcedure();
		            	System.out.println("Fichier converti généré (C) : " + f.getProgFile().getName() + ".c");
		            }
		            if(word.equals(languagePHP)){
		            	c = new PHPConverter(shortSyntax, p.getOption(nomOptP).getArgument());
		            	c.launchProcedure();
		            	System.out.println("Fichier converti généré (PHP) : " + f.getProgFile().getName() + ".php");
		            }
				}
			}
		}
		catch(Exception e){
			GestionnaireExceptions g = new GestionnaireExceptions(e);
			g.traiterException();
		}
	}
	
}
