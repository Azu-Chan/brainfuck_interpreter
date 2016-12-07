package brainfuck.core.services;

import brainfuck.Instructions;
import brainfuck.exceptions.SyntaxErrorException;

/**
 * Checker est la classe n�cessaire lorsque le mot cl� '--check'
 * est lu dans les arguments du 'main' ou que le programme doit 
 * �tre ex�cut�. Elle indique si les instructions JUMP et BACK 
 * d'un programme sont valides syntaxiquement.
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 2.1
 */
public class Checker {
private String prog;
private int nbOfParenthese;
private boolean isWellFormed;
	
	/**
 	* Constructeur d'un Rewriter.
 	* 
 	* @param p
 	*/
    public Checker(String p){
        nbOfParenthese = 0;
        prog = p;
        isWellFormed = true;
    }

    /**
	 * Effectue la v�rification des parenth�ses
	 */
    public void verify() {
    	for(int i = 0; i < prog.length(); i++){
    		if(prog.charAt(i) == Instructions.JUMP.getShortSyntax()){
    			nbOfParenthese++;
    		}
    		if(prog.charAt(i) == Instructions.BACK.getShortSyntax()){
    			nbOfParenthese--;
    			if(nbOfParenthese < 0){
    				isWellFormed = false;
    			}
    		}
    		if(isWellFormed == false){
    			break;
    		}
    	}
    	
    	if(nbOfParenthese != 0){
    		isWellFormed = false;
    	}
    }
    
    /**
	 * Indique si le programme est bien form�.
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
