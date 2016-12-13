package test.exceptions;

import brainfuck.core.services.DataCompute;
import brainfuck.exceptions.OutOfMemoryException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 13/12/2016.
 */
public class OutOfMemoryExceptionTest {
    DataCompute dataCompute = new DataCompute();
    @Test
    public void getErrorCodeTest() throws Exception {
        try{
            dataCompute.pointerLeft();

            }catch (OutOfMemoryException out){
                assertEquals(out.getErrorCode(), 2);
            }
    }

}