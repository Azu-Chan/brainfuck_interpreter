package test.exceptions;

import brainfuck.core.services.DataCompute;
import brainfuck.exceptions.OverflowException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 13/12/2016.
 */
public class OverflowExceptionTest {
    DataCompute dataCompute = new DataCompute();
    @Test
    public void getErrorCodeTest() throws Exception {
        try{
            dataCompute.decrement();
            dataCompute.decrement();
        }catch (OverflowException over){
           assertEquals(over.getErrorCode(), 1);
        }
    }

}