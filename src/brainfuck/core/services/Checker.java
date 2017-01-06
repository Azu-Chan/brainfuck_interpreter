package brainfuck.core.services;

import brainfuck.Instructions;
import brainfuck.ProgramStructure;
import brainfuck.exceptions.SyntaxErrorException;

/**
 * Checker est la classe nécessaire lorsque le mot clé '--check'
 * est lu dans les arguments du 'main' ou que le programme doit 
 * être exécuté. Elle indique si les instructions JUMP et BACK 
 * d'un programme sont valides syntaxiquement.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 2.1
 */
public class Checker {
private ProgramStructure prog;
private int nbOfParenthese;
private boolean isWellFormed;
	
	/**
 	* Constructeur d'un Rewriter.
 	* 
 	* @param p
 	*/
    public Checker(ProgramStructure p){
        nbOfParenthese = 0;
        prog = p;
        isWellFormed = true;
    }

    /**
	* Effectue la vérification des parenthèses
	 */
    public void verify() {
    	for(int i = 0; i < prog.getProgram().length(); i++){
    		if(prog.getProgram().charAt(i) == Instructions.JUMP.getShortSyntax()){
    			nbOfParenthese++;
    		}
    		if(prog.getProgram().charAt(i) == Instructions.BACK.getShortSyntax()){
    			nbOfParenthese--;
    			if(nbOfParenthese < 0){
    				isWellFormed = false;
    			}
    		}
    		if(isWellFormed == false){
    			break;
    		}
    	}
    	
    	for(Procedure p : prog.getProcedures()){
    		
    	}
    	
    	if(nbOfParenthese != 0){
    		isWellFormed = false;
    	}
    }
    
    /**
	 * Indique si le programme est bien formé.
	 * 
     * @throws SyntaxErrorException 
	 */
    public boolean isWellFormed() throws SyntaxErrorException{
        if(!isWellFormed) {
        	throw new SyntaxErrorException();
        }
        return true;
    }

}
