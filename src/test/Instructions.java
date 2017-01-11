package test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * Tests pour la classe Instructions
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class Instructions {

	// Test des méthodes pour l'instruction INCR
	@Test
	public void testIncr() {
		assertEquals("INCR", brainfuck.Instructions.INCR.getLongSyntax());
		assertEquals('+', brainfuck.Instructions.INCR.getShortSyntax());
		assertEquals(Color.WHITE.getRGB(), brainfuck.Instructions.INCR.getRGB());
	}
	
	// Test des méthodes pour l'instruction IN
	@Test
	public void testIn() {
		assertEquals("IN", brainfuck.Instructions.IN.getLongSyntax());
		assertEquals(',', brainfuck.Instructions.IN.getShortSyntax());
		assertEquals(Color.YELLOW.getRGB(), brainfuck.Instructions.IN.getRGB());
	}
	
	// Test des méthodes pour l'instruction BACK
	@Test
	public void testBack() {
		assertEquals("BACK", brainfuck.Instructions.BACK.getLongSyntax());
		assertEquals(']', brainfuck.Instructions.BACK.getShortSyntax());
		assertEquals(Color.RED.getRGB(), brainfuck.Instructions.BACK.getRGB());
	}

}
