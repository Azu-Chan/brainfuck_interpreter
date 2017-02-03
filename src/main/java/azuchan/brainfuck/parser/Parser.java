package azuchan.brainfuck.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static azuchan.brainfuck.GlobalConstantes.*;
import azuchan.brainfuck.exceptions.*;

/**
 * Parseur pour les arguments de la ligne de commande, les options
 * sont ajoutées au constructeur au fur et à mesure de l'avancée du projet.
 * 
 * @author Dylan Ritrovato
 * 
 * @version 2.1
 */
public class Parser {
	private List<Option> options = new ArrayList<Option>();
	private List<String> argsMain;
	
	/**
	 * Constructeur, la liste des options est
	 * crée statiquement ici.
	 * 
	 * @param args
	 */
	public Parser(String[] args){
		argsMain = Arrays.asList(args);
		
		options.add(new Option(nomOptP, true, true));
		options.add(new Option(nomOptI, false, true));
		options.add(new Option(nomOptO, false, true));
		options.add(new Option(nomOptRewrite, false, false));
		options.add(new Option(nomOptCheck, false, false));
		options.add(new Option(nomOptTranslate, false, false));
		options.add(new Option(nomOptTrace, false, false));
		options.add(new Option(nomOptConvert, false, false));
	}
	
	/**
	 * Parse la ligne de commande et stock dans la liste d'options
	 * les commandes reconnues avec les arguments, les différents 
	 * tests coupent le programme si les arguments et options sont incorrects
	 * 
	 * @throws DuplicateOptionException 
	 * @throws ObligatoryOptionMissingException 
	 * @throws WrongNumberArgumentsException 
	 * @throws ObligatoryArgumentException 
	 */
	public void parser() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException{
		testDoublons();
		testObligatoire();
		testArguments();
		
		remplir();
	}
	
	/**
	 * Remplit la liste d'option
	 */
	private void remplir(){
		int i = 0;
		while(i < argsMain.size()){
			if(contiensOption(argsMain.get(i))){
				Option op = getOption(argsMain.get(i));
				op.optionIsHere();
				if(op.argNecessary()){
					i++;
					op.setArgument(argsMain.get(i));
				}
			}
			i++;
		}
	}
	
	/**
	 * Retourne la liste d'options
	 * 
	 * @return la liste
	 */
	public List<Option> getOptionList(){
		return options;
	}
	
	/**
	 * Teste si les options ont le bon nombre d'arguments
	 * 
	 * @throws WrongNumberArgumentsException 
	 */
	private void testArguments() throws WrongNumberArgumentsException{
		for(int i = 0; i < argsMain.size(); i++){
			if(!contiensOption(argsMain.get(i))){
				System.out.println("Attention : L'argument " + argsMain.get(i) +" est mal placé, il sera donc ignoré.");
			}
			else{
				Option op = getOption(argsMain.get(i));
				if((i + 1) >= argsMain.size() && op.argNecessary()){
					throw new WrongNumberArgumentsException(op.getNom());
				}
				if(op.argNecessary()){
					i++;
					if(contiensOption(argsMain.get(i))){
						throw new WrongNumberArgumentsException(op.getNom());
					}
				}
			}
		}
	}
	
	/**
	 * Teste si les options n'ont pas de doublons
	 * 
	 * @throws DuplicateOptionException 
	 */
	private void testDoublons() throws DuplicateOptionException{
		for(Option o : options){
			int i = 0;
			for(String arg : argsMain){
				if(o.isThisOption(arg)){
					i++;
				}
			}
			if(i > 1){
				throw new DuplicateOptionException(o.getNom());
			}
		}
	}
	
	/**
	 * Teste si les arguments obligatoires sont présentes
	 * 
	 * @throws ObligatoryOptionMissingException 
	 */
	private void testObligatoire() throws ObligatoryOptionMissingException {
		for(Option o : options){
			if(o.estObligatoire()){
				if(!argsMain.contains(o.getNom())){
					throw new ObligatoryOptionMissingException(o.getNom());
				}
			}
		}
	}
	
	/**
	 * Indique si le nom en paramètre est une option existante
	 * 
	 * @param nom
	 * 
	 * @return vrai ou faux
	 */
	public boolean contiensOption(String nom){
		boolean val = false;
		for(Option o : options){
			if((o.getNom()).equals(nom)){
				val = true;
			}
		}
		return val;
	}
	
	/**
	 * Retourne une référence sur l'option si elle existe.
	 * 
	 * @param nom
	 * 
	 * @return l'option
	 */
	public Option getOption(String nom){
		for(Option o : options){
			if(o.isThisOption(nom)){
				return o;
			}
		}
		return null;
	}
}
