package brainfuck.launcher;

import brainfuck.core.services.*;
import brainfuck.exceptions.GestionnaireExceptions;
import brainfuck.OptionInclude;
import brainfuck.core.reader.*;
import brainfuck.files.FileCreator;
import brainfuck.parser.Parser;

import java.util.Scanner;

import brainfuck.Metrics;

/**
 * Classe dont le but est de faire se d�rouler le programme
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
			
			Metrics.setProgSize(r.getShortSyntax().length()); // UPDATE METRIC
			
			executeServices(p, f, r.getShortSyntax());
		}
		catch(Exception e){
			GestionnaireExceptions g = new GestionnaireExceptions(e);
			g.traiterException();
		}
	}
	
	/**
	 * Ex�cute les diff�rents services en fonction des arguments
	 * la shortSyntax pass�e en argument est FORCEMENT correcte
	 * suite � sa v�rification plus t�t (readFile)
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
					
					i.createImage("translated_" + f.getProgFile().getName());
				}
				if(p.getOption(nomOptConvert).getPresent()){
					Converter c;
					
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(System.in);
					// Invite de saisie
		            System.out.print("\nSaisir le langage voulu (C PHP)\n> ");
		            String word;
		            do{
		            	word = scan.next();
		            	scan.nextLine();
		            }while (word == null || (!word.equals(languageC) && !word.equals(languagePHP)));
		            
		            if(word.equals(languageC)){
		            	c = new CConverter(shortSyntax, p.getOption(nomOptP).getArgument());
		            	c.launchProcedure();
		            }
		            if(word.equals(languagePHP)){
		            	c = new PHPConverter(shortSyntax, p.getOption(nomOptP).getArgument());
		            	c.launchProcedure();
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
