package test.core.services;

import brainfuck.core.services.DataCompute;
import brainfuck.core.services.Printer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 30/11/2016.
 */
public class PrinterTest {

    DataCompute dataCompute= new DataCompute();

    @Before
    public void setUp() throws Exception {
        dataCompute.increment();
        dataCompute.increment();
        dataCompute.pointerRight();
        dataCompute.increment();

    }

    @Test
    public void consolePrintCellForLog() throws Exception {
        Printer printer=new Printer(dataCompute.getGrille());
        String s = "";
        s+= "c" + 0 + " : " + 2 + "\r\n";
        s+= "c" + 1 + " : " + 1 + "\r\n";
        assertEquals(s,printer.consolePrintCellForLog());
    }

}