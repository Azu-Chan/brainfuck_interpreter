package test.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import brainfuck.OptionInclude;
import brainfuck.exceptions.DuplicateOptionException;
import brainfuck.exceptions.ObligatoryOptionMissingException;
import brainfuck.exceptions.WrongNumberArgumentsException;
import brainfuck.parser.Parser;

public class ParserTest {
	Parser parser;


	@Test(expected = DuplicateOptionException.class)
	public void testDoubleArgumentException() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException{
		String[] argsMain={"-p", "-p", "test.bf"};
		parser = new Parser(argsMain);
		parser.parser();
	}
	
	@Test(expected = ObligatoryOptionMissingException.class)
	public void testObligatoryOptionMissingException() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException{
		String[] argsMain={"-i", "test.bf"};
		parser = new Parser(argsMain);
		parser.parser();
	}
	
	@Test(expected = WrongNumberArgumentsException.class)
	public void testWrongNumberArgumentsExceptionn() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException{
		String[] argsMain={ "test.bf", "-p"};
		parser = new Parser(argsMain);
		parser.parser();
	}

}
