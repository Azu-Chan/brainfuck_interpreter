package test.core.services;

import brainfuck.core.services.Executor;
import brainfuck.exceptions.InputMissingFileException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by user on 07/12/2016.
 */
public class ExecutorTest {
    Executor executor;
    String helloworld="++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";

    @Before
    public void setUp() throws FileNotFoundException, InputMissingFileException {
        executor=new Executor(helloworld,null,null);
    }

    @Test
    public void executeTest() throws Exception {

        byte[] bytes = new byte[30000];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = Byte.MIN_VALUE;
        }
        bytes[1]+=87;
        bytes[2]+=100;
        bytes[3]+=33;
        bytes[4]+=10;
        executor.executeProg();
        assertArrayEquals(bytes,executor.getMemory());
    }

}