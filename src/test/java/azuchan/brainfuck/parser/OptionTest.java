package azuchan.brainfuck.parser;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests pour la classe Option
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.0
 */
public class OptionTest {

	@Test
	public void testOptions() {
		Option o1 = new Option("aaa", true, true);
		Option o2 = new Option("bbb", false, false);
		
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
