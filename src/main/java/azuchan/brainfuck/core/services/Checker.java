package azuchan.brainfuck.core.services;

import azuchan.brainfuck.Instructions;
import azuchan.brainfuck.exceptions.SyntaxErrorException;

/**
 * Checker est la classe nécessaire lorsque le mot clé '--check'
 * est lu dans les arguments du 'main' ou que le programme doit 
 * être exécuté. Elle indique si les instructions JUMP et BACK 
 * d'un programme sont valides syntaxiquement.
 * 
 * @author Dylan Ritrovato
 * 
 * @version 2.2
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
	 * Effectue la vérification des parenthèses
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
    				break;
    			}
    		}
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
