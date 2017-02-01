package Azu_Chan.brainfuck.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import Azu_Chan.brainfuck.exceptions.*;

/**
 * Tests pour la classe Parser
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.0
 */
public class ParserTest {

	@Test(expected = ObligatoryOptionMissingException.class)
	public void testObligatoire() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"-i", "file.c"};
		Parser p = new Parser(args);
		p.parser();
	}
	
	@Test(expected = DuplicateOptionException.class)
	public void testDoublons() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"-p", "file.c", "-p", "a.c"};
		Parser p = new Parser(args);
		p.parser();
	}
	
	@Test(expected = WrongNumberArgumentsException.class)
	public void testArgumentMauvais() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"-p", "-o", "a.c"};
		Parser p = new Parser(args);
		p.parser();
	}
	
	@Test(expected = WrongNumberArgumentsException.class)
	public void testArgumentMauvais2() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"-p"};
		Parser p = new Parser(args);
		p.parser();
	}
	
	@Test
	public void testGetOption() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException {
		String[] args = {"--check", "-p", "a.c", "bb.bf"};
		Parser p = new Parser(args);
		p.parser(); // avertissement
		
		assertEquals(false, p.contiensOption("-z"));
		assertEquals(true, p.contiensOption("-p"));
		
		assertEquals(null, p.getOption("-z"));
		assertEquals("-p", p.getOption("-p").getNom());
	}

}
