package test.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import brainfuck.exceptions.DuplicateOptionException;
import brainfuck.exceptions.ObligatoryOptionMissingException;
import brainfuck.exceptions.WrongNumberArgumentsException;

/**
 * Tests pour la classe Parser
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class Parser {

	@Test(expected = ObligatoryOptionMissingException.class)
	public void testObligatoire() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"-i", "file.c"};
		brainfuck.parser.Parser p = new brainfuck.parser.Parser(args);
		p.parser();
	}
	
	@Test(expected = DuplicateOptionException.class)
	public void testDoublons() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"-p", "file.c", "-p", "a.c"};
		brainfuck.parser.Parser p = new brainfuck.parser.Parser(args);
		p.parser();
	}
	
	@Test(expected = WrongNumberArgumentsException.class)
	public void testArgumentMauvais() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"-p", "-o", "a.c"};
		brainfuck.parser.Parser p = new brainfuck.parser.Parser(args);
		p.parser();
	}
	
	@Test(expected = WrongNumberArgumentsException.class)
	public void testArgumentMauvais2() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"-p"};
		brainfuck.parser.Parser p = new brainfuck.parser.Parser(args);
		p.parser();
	}
	
	@Test
	public void testGetOption() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"--check", "-p", "a.c", "bb.bf"};
		brainfuck.parser.Parser p = new brainfuck.parser.Parser(args);
		p.parser(); // avertissement
		
		assertEquals(false, p.contiensOption("-z"));
		assertEquals(true, p.contiensOption("-p"));
		
		assertEquals(null, p.getOption("-z"));
		assertEquals("-p", p.getOption("-p").getNom());
	}

}
