package brainfuck.launcher;

import brainfuck.core.services.*;
import brainfuck.exceptions.GestionnaireExceptions;
import brainfuck.OptionInclude;
import brainfuck.ProgramStructure;
import brainfuck.core.reader.*;
import brainfuck.files.FileCreator;
import brainfuck.parser.Parser;

import java.util.Scanner;

import brainfuck.Metrics;

/**
 * Classe dont le but est de faire se dérouler le programme
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class ProgramStarter implements OptionInclude {
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
	 * Lance l'ex�cution du prtogramme principal
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
			
			Metrics.setProgSize(r.getProgStruct().getProgramLenght()); // UPDATE METRIC
			
			executeServices(p, f, r.getProgStruct());
		}
		catch(Exception e){
			GestionnaireExceptions g = new GestionnaireExceptions(e);
			g.traiterException();
		}
	}
	
	/**
	 * Exécute les différents services en fonction des arguments
	 * la structure du programme passée en argument est FORCEMENT correcte
	 * suite à sa vérification plus tôt (readFile)
	 * 
	 * @param p
	 * @param f
	 * @param shortSyntax
	 */
	public void executeServices(Parser p, FileCreator f, ProgramStructure leProgramme){
		try{
			if(!p.getOption(nomOptRewrite).getPresent() && !p.getOption(nomOptCheck).getPresent() && !p.getOption(nomOptTranslate).getPresent()
					&& !p.getOption(nomOptConvert).getPresent()){
				Checker c = new Checker(leProgramme);
				c.verify();
				if(c.isWellFormed()){
					Executor ex;
					TraceLog t = null;
					if(p.getOption(nomOptTrace).getPresent()){
						t = new TraceLog(f.getLogFile());
						t.initializeLogFile();
						ex = new Executor(leProgramme, f.getInFile(), f.getOutFile(), t);
					}
					else{
						ex = new Executor(leProgramme, f.getInFile(), f.getOutFile());
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
					Checker c = new Checker(leProgramme);
					c.verify();
					
					if(c.isWellFormed()){
						System.out.println("Programme correct syntaxiquement.");
					}
				}
				if(p.getOption(nomOptRewrite).getPresent()){
					System.out.println(leProgramme.getProgram().toString());
				}
				if(p.getOption(nomOptTranslate).getPresent()){
					ImageTranslator i = new ImageTranslator(leProgramme);
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
		            	c = new CConverter(leProgramme, p.getOption(nomOptP).getArgument());
		            	c.launchProcedure();
		            	System.out.println("Fichier converti généré (C) : " + f.getProgFile().getName() + ".c");
		            }
		            if(word.equals(languagePHP)){
		            	c = new PHPConverter(leProgramme, p.getOption(nomOptP).getArgument());
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
