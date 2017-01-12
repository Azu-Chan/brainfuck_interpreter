package test.parser;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests pour la classe Option
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class Option {

	@Test
	public void testOptions() {
		brainfuck.parser.Option o1 = new brainfuck.parser.Option("aaa", true, true);
		brainfuck.parser.Option o2 = new brainfuck.parser.Option("bbb", false, false);
		
		assertEquals("aaa", o1.getNom());
		assertEquals("bbb", o2.getNom());
		
		assertEquals(true, o1.estObligatoire());
		assertEquals(false, o2.estObligatoire());
		
		assertEquals(true, o1.argNecessary());
		assertEquals(false, o2.argNecessary());
		
		assertEquals(null, o1.getArgument());
		o1.setArgument("aar");
		assertEquals("aar", o1.getArgument());
		
		assertEquals(false, o1.getPresent());
		o1.optionIsHere();
		assertEquals(true, o1.getPresent());
		
		assertEquals(false, o1.isThisOption(""));
		assertEquals(false, o1.isThisOption("bbb"));
		assertEquals(true, o1.isThisOption("aaa"));
	}

}
