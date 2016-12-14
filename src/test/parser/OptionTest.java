package test.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import brainfuck.parser.Option;

public class OptionTest {
	Option option;
	
	@Before
	public void setUp(){
		option = new Option(null, false, false);	
	}

	@Test
	public void testOption() {
		assertEquals(option.getNom(),null);
		assertEquals(option.getArgument(),null);
		assertEquals(option.estObligatoire(),false);
		assertEquals(option.argNecessary(),false);
		assertEquals(option.getArgument(),null);
		option.setArgument("-p");
		assertEquals(option.getArgument(),"-p");
		assertEquals(option.getPresent(),false);
		option.optionIsHere();
		assertEquals(option.getPresent(),true);
		
	}

}
