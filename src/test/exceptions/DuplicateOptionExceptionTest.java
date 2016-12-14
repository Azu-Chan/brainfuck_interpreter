package test.exceptions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import brainfuck.exceptions.DuplicateOptionException;
import brainfuck.parser.Parser;

public class DuplicateOptionExceptionTest {
	Parser parser;

	@Before
	public void setUp(){
		String[] argsMain={"-p", "-p", "test.bf"};
		parser = new Parser(argsMain);
	}

	@Test
	public void testGetErrorCode() throws Exception {
		try{
			parser.parser();
		}catch (DuplicateOptionException out){
			assertEquals(out.getErrorCode(), 5);
		}
	}

}
