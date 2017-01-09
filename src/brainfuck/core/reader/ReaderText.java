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
import brainfuck.exceptions.WrongProcedureDeclarationException;

/**
 * ReaderText est la classe qui va lire le fichier brainfuck passé avec "-p" après application des macros
 * et le transformer en une chaine de caractères uniquement constituée de shortcuts.
 * 
 * Cette chaine est ensuite utilisée pour tous les traitements disponibles dans
 * cet interpréteur de brainfuck.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 2.3
 */
public class ReaderText extends ReaderBF{
	private FileInputStream prog;
	private boolean inTheMain = false;
	private String currentProc = null;
	private String currentProcContent = "";
	ArrayList<Integer> currentParams = null;
	

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
	 * @throws WrongProcedureDeclarationException 
	 */
	@Override
	public void readFile() throws IOException, IsNotBrainfuckInstructionException, WrongProcedureDeclarationException{
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
	 * Gére désormais l'indentation et les commentaires (++++ #Ceci est un com)
	 * 
	 * @param ligne
	 * 
	 * @throws IsNotBrainfuckInstructionException
	 * @throws WrongProcedureDeclarationException 
	 */
	protected void traitementLigne(String ligne) throws IsNotBrainfuckInstructionException, WrongProcedureDeclarationException{
		if(ligne.equals("#")){
			ligne = " "; // pour éviter un split qui ne marcherait pas...
		}
		//         On vire le com   // On vire l'indentation
		String l = ligne.split("#")[0].replaceAll("\t| ", "");
		
		if(isLigneShortcut(l)){
			String instr = "";
			for(int i = 0; i < l.length(); i++){
				instr += (l.charAt(i));
				if(currentProc == null){
					inTheMain = true;
					memProg.addInstruction(instr);
					memProg.incrementProgramLenght(); //UPDATE METRIC
				}
				else{
					currentProcContent += instr;
				}
				instr = "";
			}
		}
		else{
			constructionWithOther(l);
		}
	}
	
	/**
	 * cette methode va transformer l'instruction en raccourci puis
	 * l'injecter dans la chaîne à l'aide de constructionWithShortcut(String s)
	 * Gere désormais les créations et appels de fonction
	 * 
	 * @param instr
	 * 
	 * @throws IsNotBrainfuckInstructionException
	 * @throws WrongProcedureDeclarationException 
	 */
	private void constructionWithOther(String instr) throws IsNotBrainfuckInstructionException, WrongProcedureDeclarationException{
		// Instruction normale
		if(isInstruction(instr)){
			for(Instructions i : Instructions.values()){
				if(i.getLongSyntax().equals(instr)){
					if(currentProc == null){
						inTheMain = true;
						memProg.addInstruction(""+i.getShortSyntax());
						memProg.incrementProgramLenght(); //UPDATE METRIC
					}
					else{
						currentProcContent += instr;
					}
				}
			}
			return ;
		}
		
		// Entrée procédure ou déclaration
		if(instr.charAt(0) == '@'){
			String tempName = new String();
			ArrayList<Integer >tempParams = new ArrayList<Integer>();
			
			int i = 0;
				
			for(i = 1; i < instr.length() || instr.charAt(i) == '('; i++){
				tempName += instr;
			}
			if(instr.charAt(i) != '(' || !validFoncName(tempName)){
				throw new WrongProcedureDeclarationException(instr);
			}
			
			String tempParam = "";
			for(/* EMPTY */; i < instr.length() || instr.charAt(i) == ')'; i++){
				if(instr.charAt(i) == ';'){
					if(tempParam.equals("")){
						throw new WrongProcedureDeclarationException(instr);
					}
					else{
						tempParams.add(Integer.valueOf(tempParam));
						tempParam = "";
					}
				}
				else if(instr.charAt(i) >= '0' && instr.charAt(i) <= '9'){
					tempParam += instr.charAt(i);
				}
				else{
					throw new WrongProcedureDeclarationException(instr);
				}
			}
			if(instr.charAt(i) != ')' || instr.charAt(i-1) == ';'){
				throw new WrongProcedureDeclarationException(instr);
			}
			
			boolean declaration = false;
			if(instr.length() == i+2){
				if(instr.charAt(i+1) == '{'){
					declaration = true;
				}
			}
			
			if(declaration == true){
				if(inTheMain == false && currentProc == null){
					currentProc = new String(tempName);
					currentParams = tempParams;
				}
				else{
					throw new WrongProcedureDeclarationException(tempName);
				}
			}
			else{
				String tmpDeclar = "";
				tmpDeclar += "@" + tempName + "(";
				for(int z = 0; z < tempParams.size(); z++){
					tmpDeclar += String.valueOf(tempParams.get(i));
					if(z != tempParams.size()-1){
						tmpDeclar += ";";
					}
				}
				tmpDeclar += ")";
				if(currentProc != null){
					currentProcContent += tmpDeclar;
				}
				else{
					memProg.addInstruction(tmpDeclar);
					memProg.incrementProgramLenght(); //UPDATE METRIC
					inTheMain = true;
				}
			}
			
			return ;
		}
		
		// Sortie procedure
		if(instr.equals("}")){
			if(currentProcContent.contains("$")){
				memProg.addFunction(currentProc, currentProcContent, currentParams);
			}
			else{
				memProg.addProcedure(currentProc, currentProcContent, currentParams);
			}
			currentProcContent = "";
			currentProc = null;
			currentParams = null;
			return ;
		}
		
		// Procedure to function
		if(instr.equals("$") && currentProc != null){
			currentProcContent += instr;
			return ;
		}
			
		throw new IsNotBrainfuckInstructionException(instr);
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
	 * indique si la chaîne passée en paramètre est unne instruction longue
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
	
	/**
	 * indique la chaine de caractères est un nom de fonction valide
	 * 
	 * @param s
	 * 
	 * @return booleen
	 */
	private boolean validFoncName(String s){
		for(int i = 0; i < s.length(); i++){
			if(!(s.charAt(i) >= '0' && s.charAt(i) <= '9') && !(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') &&
					!(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') && s.charAt(i) != '_') return false;
		}
		return true;
	}
}
