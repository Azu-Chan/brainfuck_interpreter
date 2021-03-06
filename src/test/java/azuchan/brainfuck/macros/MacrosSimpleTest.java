package azuchan.brainfuck.macros;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests pour la classe MacrosSimple
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.0
 */
public class MacrosSimpleTest {

	@Test
	public void testToDigit() {
		assertEquals("TO_DIGIT", MacrosSimple.TO_DIGIT.name());
		
		String s = "";
		for(int i=0; i<48; i++){
			s += "DECR\r\n";
		}
		
		assertEquals(s, MacrosSimple.TO_DIGIT.getEffect());
	}
	
	@Test
	public void testFromDigit() {
		assertEquals("FROM_DIGIT", MacrosSimple.FROM_DIGIT.name());
		
		String s = "";
		for(int i=0; i<48; i++){
			s += "INCR\r\n";
		}
		
		assertEquals(s, MacrosSimple.FROM_DIGIT.getEffect());
	}
	
	@Test
	public void testToAplha() {
		assertEquals("TO_ALPHA", MacrosSimple.TO_ALPHA.name());
		
		String s = "";
		for(int i=0; i<96; i++){
			s += "DECR\r\n";
		}
		
		assertEquals(s, MacrosSimple.TO_ALPHA.getEffect());
	}
	
	@Test
	public void testFromAlpha() {
		assertEquals("FROM_ALPHA", MacrosSimple.FROM_ALPHA.name());
		
		String s = "";
		for(int i=0; i<96; i++){
			s += "INCR\r\n";
		}
		
		assertEquals(s, MacrosSimple.FROM_ALPHA.getEffect());
	}
	
	@Test
	public void testToAplhaCap() {
		assertEquals("TO_ALPHA_CAP", MacrosSimple.TO_ALPHA_CAP.name());
		
		String s = "";
		for(int i=0; i<64; i++){
			s += "DECR\r\n";
		}
		
		assertEquals(s, MacrosSimple.TO_ALPHA_CAP.getEffect());
	}
	
	@Test
	public void testFromAlphaCap() {
		assertEquals("FROM_ALPHA_CAP", MacrosSimple.FROM_ALPHA_CAP.name());
		
		String s = "";
		for(int i=0; i<64; i++){
			s += "INCR\r\n";
		}
		
		assertEquals(s, MacrosSimple.FROM_ALPHA_CAP.getEffect());
	}

}
