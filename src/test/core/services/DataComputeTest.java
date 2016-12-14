package test.core.services;

import brainfuck.core.services.DataCompute;
import brainfuck.exceptions.OutOfMemoryException;
import brainfuck.exceptions.OverflowException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by user on 30/11/2016.
 */
public class DataComputeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    DataCompute dataCompute = new DataCompute();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void getGrille() throws Exception {

        byte[] bytes = new byte[30000];
        int start = 0;
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = Byte.MIN_VALUE;
        }
        assertArrayEquals(bytes,dataCompute.getGrille());
    }

    @Test
    public void getPointerPos() throws Exception {
        assertTrue(dataCompute.getPointerPos()==0);
        dataCompute.pointerRight();
        dataCompute.pointerRight();
        assertTrue(dataCompute.getPointerPos()==2);
        dataCompute.pointerLeft();
        assertTrue(dataCompute.getPointerPos()==1);
    }

    @Test
    public void getPointedValue() throws Exception {
        assertTrue(dataCompute.getPointedValue()==Byte.MIN_VALUE);
        dataCompute.increment();
        dataCompute.increment();
        assertTrue(dataCompute.getPointedValue()==Byte.MIN_VALUE+2);
        dataCompute.decrement();
        assertTrue(dataCompute.getPointedValue()==Byte.MIN_VALUE+1);

    }

    @Test(expected = OverflowException.class)
    public void incrementOverflow() throws Exception {
        dataCompute.increment();
        for (int i = 0; i < 256; i++)
            dataCompute.increment();

    }

    @Test (expected = OverflowException.class)
    public void decrementOverflow() throws Exception {
        dataCompute.increment();
        dataCompute.decrement();
        dataCompute.decrement();

    }

    @Test (expected = OutOfMemoryException.class)
    public void pointerRightOut() throws Exception {
        dataCompute.pointerRight();
        dataCompute.pointerRight();
        for (int i = 2; i < 30000; i++)
            dataCompute.pointerRight();

    }

    @Test (expected = OutOfMemoryException.class)
    public void pointerLeftOut() throws Exception {
        dataCompute.pointerRight();
        dataCompute.pointerLeft();
        dataCompute.pointerLeft();

    }

    @Test
    public void getErrorOutCodeTest() throws Exception {
        try{
            dataCompute.pointerLeft();
        }catch (OutOfMemoryException out){
            assertEquals(out.getErrorCode(), 2);
        }
    }

    @Test
    public void getErrorOverCodeTest() throws Exception {
        try{
            dataCompute.decrement();
            dataCompute.decrement();
        }catch (OverflowException over){
            assertEquals(over.getErrorCode(), 1);
        }
    }
}