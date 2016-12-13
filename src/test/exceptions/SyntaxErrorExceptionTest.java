package test.exceptions;

import brainfuck.core.services.Checker;
import brainfuck.exceptions.SyntaxErrorException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 13/12/2016.
 */
public class SyntaxErrorExceptionTest {
    Checker checker;
    @Test
    public void getErrorCodeTest() throws Exception {
        String nwF2="++[++]-]++[++]";
        checker=new Checker(nwF2);
        try {
                checker.verify();
                checker.isWellFormed();
            }catch (SyntaxErrorException syn){
                assertTrue(syn.getErrorCode()==4);
            }
    }

}