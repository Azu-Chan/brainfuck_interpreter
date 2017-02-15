package azuchan.brainfuck.launcher;

import azuchan.brainfuck.core.services.*;
import azuchan.brainfuck.exceptions.*;
import static azuchan.brainfuck.GlobalConstantes.*;
import azuchan.brainfuck.core.reader.*;
import azuchan.brainfuck.files.FileCreator;
import azuchan.brainfuck.parser.Parser;

import java.util.Scanner;

import azuchan.brainfuck.Metrics;

/**
 * Classe dont le but est de faire se dérouler le programme
 * 
 * @author Dylan Ritrovato
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
			if(f.getProgFile().getName().toLowerCase().endsWith(".bmp")){
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
			if(!p.getOption(NOM_OPT_REWRITE).getPresent() && !p.getOption(NOM_OPT_CHECK).getPresent() && 
					!p.getOption(NOM_OPT_TRANSLATE).getPresent() && !p.getOption(NOM_OPT_CONVERT).getPresent()){
				Checker c = new Checker(shortSyntax);
				c.verify();
				if(c.isWellFormed()){
					Executor ex;
					TraceLog t = null;
					if(p.getOption(NOM_OPT_TRACE).getPresent()){
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
				if(p.getOption(NOM_OPT_CHECK).getPresent()){
					Checker c = new Checker(shortSyntax);
					c.verify();
					
					if(c.isWellFormed()){
						System.out.println("Programme correct syntaxiquement.");
					}
				}
				if(p.getOption(NOM_OPT_REWRITE).getPresent()){
					System.out.println(shortSyntax.toString());
				}
				if(p.getOption(NOM_OPT_TRANSLATE).getPresent()){
					ImageTranslator i = new ImageTranslator(shortSyntax);
					i.createImageProg();
					
					i.createImage(f.getProgFile().getName());
					System.out.println("Fichier d'image programme généré : " + f.getProgFile().getName() + ".bmp");
				}
				if(p.getOption(NOM_OPT_CONVERT).getPresent()){
					Converter c;
					
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(System.in);
					// Invite de saisie
		            System.out.print("\nSaisir le langage voulu (" + LANGAGE_C + " " + LANGAGE_PHP + ") ou " + ANNUL + " pour ANNULer\n> ");
		            String word;
		            do{
		            	word = scan.next();
		            	scan.nextLine();
		            }while (word == null || (!word.equals(LANGAGE_C) && !word.equals(LANGAGE_PHP) && !word.equals(ANNUL)));
		            
		            if(word.equals(LANGAGE_C)){
		            	c = new CConverter(shortSyntax, p.getOption(NOM_OPT_P).getArgument());
		            	c.launchProcedure();
		            	System.out.println("Fichier converti généré (C) : " + f.getProgFile().getName() + ".c");
		            }
		            if(word.equals(LANGAGE_PHP)){
		            	c = new PHPConverter(shortSyntax, p.getOption(NOM_OPT_P).getArgument());
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
