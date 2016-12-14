package test.macros;

import static org.junit.Assert.*;

import org.junit.Test;

import brainfuck.Instructions;
import brainfuck.macros.MacrosSimple;

public class MacrosSimpleTest {
	String response="";

	@Test
	public void testGetName() {
		assertEquals("TO_DIGIT",MacrosSimple.TO_DIGIT.getName());
	}

	@Test
	public void testGetEffectToDigit() {
		for(int i = 0; i < 48; i++){
			response += Instructions.DECR.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosSimple.TO_DIGIT.getEffect());
	}

	@Test
	public void testGetEffectFromDigit() {
		for(int i = 0; i < 48; i++){
			response += Instructions.INCR.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosSimple.FROM_DIGIT.getEffect());
	}
	
	@Test
	public void testGetEffectToAlpha() {
		for(int i = 0; i < 96; i++){
			response += Instructions.DECR.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosSimple.TO_ALPHA.getEffect());
	}

	@Test
	public void testGetEffectFromAlpha() {
		for(int i = 0; i < 96; i++){
			response += Instructions.INCR.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosSimple.FROM_ALPHA.getEffect());
	}

	@Test
	public void testGetEffectToAlphaCap() {
		for(int i = 0; i < 64; i++){
			response += Instructions.DECR.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosSimple.TO_ALPHA_CAP.getEffect());
	}

	@Test
	public void testGetEffectFromAlphaCap() {
		for(int i = 0; i < 64; i++){
			response += Instructions.INCR.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosSimple.FROM_ALPHA_CAP.getEffect());
	}
	
}
