package test.parser;

import static org.junit.Assert.*;

import brainfuck.core.services.DataCompute;
import org.junit.Before;
import org.junit.Test;

import brainfuck.OptionInclude;
import brainfuck.exceptions.DuplicateOptionException;
import brainfuck.exceptions.ObligatoryOptionMissingException;
import brainfuck.exceptions.WrongNumberArgumentsException;
import brainfuck.parser.Parser;

public class ParserTest {
	Parser parser;


	@Test
	public void testDoubleArgumentException() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException{
		try{
		    String[] argsMain={"-p", "-p", "test.bf"};
            parser = new Parser(argsMain);
            parser.parser();
        }catch (DuplicateOptionException du){
            assertEquals(du.getErrorCode(),5);
        }

	}
	
	@Test
	public void testObligatoryOptionMissingException() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException{
		try{
		    String[] argsMain={"-i", "test.bf"};
            parser = new Parser(argsMain);
		    parser.parser();
        }catch (ObligatoryOptionMissingException ob){
            assertEquals(ob.getErrorCode(),6);
        }
	}
	
	@Test
	public void testWrongNumberArgumentsExceptionn() throws DuplicateOptionException, ObligatoryOptionMissingException, WrongNumberArgumentsException{
		try {
            String[] argsMain = {"test.bf", "-p"};
            parser = new Parser(argsMain);
            parser.parser();
        }catch (WrongNumberArgumentsException wr){
            assertEquals(wr.getErrorCode(),7);
        }
	}

}
