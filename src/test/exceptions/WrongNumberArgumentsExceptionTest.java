package test.exceptions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import brainfuck.exceptions.WrongNumberArgumentsException;
import brainfuck.parser.Parser;

public class WrongNumberArgumentsExceptionTest {
	Parser parser;

	@Before
	public void setUp(){
		String[] argsMain={ "test.bf", "-p"};
		parser = new Parser(argsMain);
	}

	@Test
	public void testGetErrorCode() throws Exception {
		try{
			parser.parser();
		}catch (WrongNumberArgumentsException out){
			assertEquals(out.getErrorCode(), 7);
		}
	}

}
