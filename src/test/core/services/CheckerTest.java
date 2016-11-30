package test.core.services;

import brainfuck.core.services.Checker;
import brainfuck.exceptions.SyntaxErrorException;
import org.junit.Test;


import static org.junit.Assert.*;


/**
 * Created by user on 29/11/2016.
 */
public class CheckerTest {
    Checker checker;

    @Test
    public void notWellFormed1() throws Exception {
        String nwF1="++[+++[+-[++[--]--++]+]";
        checker=new Checker(nwF1);
        try {
            checker.verify();
            checker.isWellFormed();
        }catch (SyntaxErrorException syn){
            assertTrue(syn.getErrorCode()==4);
        }
    }

    @Test
    public void notWellFormed2() throws Exception {
        String nwF2="++[++]-]++[++]";
        checker=new Checker(nwF2);
        try {
            checker.verify();
            checker.isWellFormed();
        }catch (SyntaxErrorException syn){
            assertTrue(syn.getErrorCode()==4);
        }
    }

    @Test
    public void wellFormed2()throws Exception{
        String wF2="++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
        checker=new Checker(wF2);
        checker.verify();
        assertTrue(checker.isWellFormed());
    }

}