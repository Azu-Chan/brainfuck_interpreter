package azuchan.brainfuck;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * Tests pour la classe Instructions
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.0
 */
public class InstructionsTest {

	// Test des méthodes pour l'instruction INCR
	@Test
	public void testIncr() {
		assertEquals("INCR", Instructions.INCR.getLongSyntax());
		assertEquals('+', Instructions.INCR.getShortSyntax());
		assertEquals(Color.WHITE.getRGB(), Instructions.INCR.getRGB());
	}
	
	// Test des méthodes pour l'instruction IN
	@Test
	public void testIn() {
		assertEquals("IN", Instructions.IN.getLongSyntax());
		assertEquals(',', Instructions.IN.getShortSyntax());
		assertEquals(Color.YELLOW.getRGB(), Instructions.IN.getRGB());
	}
	
	// Test des méthodes pour l'instruction BACK
	@Test
	public void testBack() {
		assertEquals("BACK", Instructions.BACK.getLongSyntax());
		assertEquals(']', Instructions.BACK.getShortSyntax());
		assertEquals(Color.RED.getRGB(), Instructions.BACK.getRGB());
	}

}
