package test;

import org.junit.Before;
import org.junit.Test;
import brainfuck.Instructions;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 07/12/2016.
 */
public class InstructionsTest {


    @Test ()
    public void getLongSyntax() throws Exception {
        assertEquals("BACK",Instructions.BACK.getLongSyntax());
    }

    @Test
    public void getShortSyntax() throws Exception {
        assertEquals(']',Instructions.BACK.getShortSyntax());
    }

    @Test
    public void getRGB() throws Exception {
        assertEquals(Color.RED.getRGB(),Instructions.BACK.getRGB());
    }

}