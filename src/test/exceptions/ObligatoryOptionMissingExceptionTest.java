package test.exceptions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import brainfuck.exceptions.ObligatoryOptionMissingException;
import brainfuck.parser.Parser;

public class ObligatoryOptionMissingExceptionTest {
	Parser parser;

	@Before
	public void setUp() {
		String[] argsMain={"-i", "test.bf"};
		parser = new Parser(argsMain);
	}

	@Test
	public void testGetErrorCode() throws Exception {
		try{
			parser.parser();
		}catch (ObligatoryOptionMissingException out){
			assertEquals(out.getErrorCode(), 6);
		}
	}

}
