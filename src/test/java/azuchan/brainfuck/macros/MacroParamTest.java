package azuchan.brainfuck.macros;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests pour la classe MacroParam
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.0
 */
public class MacroParamTest {

	@Test
	public void testMultiIncr() {
		assertEquals("MULTI_INCR", MacrosParam.MULTI_INCR.name());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "INCR\r\n";
		}
		
		assertEquals("", MacrosParam.MULTI_INCR.getEffect(param1));
		
		assertEquals("", MacrosParam.MULTI_INCR.getEffect(param2));
		
		assertEquals(s, MacrosParam.MULTI_INCR.getEffect(param3));
	}
	
	@Test
	public void testMultiDecr() {
		assertEquals("MULTI_DECR", MacrosParam.MULTI_DECR.name());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "DECR\r\n";
		}
		
		assertEquals("", MacrosParam.MULTI_DECR.getEffect(param1));
		
		assertEquals("", MacrosParam.MULTI_DECR.getEffect(param2));
		
		assertEquals(s, MacrosParam.MULTI_DECR.getEffect(param3));
	}
	
	@Test
	public void testMultiLeft() {
		assertEquals("MULTI_LEFT", MacrosParam.MULTI_LEFT.name());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "LEFT\r\n";
		}
		
		assertEquals("", MacrosParam.MULTI_LEFT.getEffect(param1));
		
		assertEquals("", MacrosParam.MULTI_LEFT.getEffect(param2));
		
		assertEquals(s, MacrosParam.MULTI_LEFT.getEffect(param3));
	}
	
	@Test
	public void testMultiRight() {
		assertEquals("MULTI_RIGHT", MacrosParam.MULTI_RIGHT.name());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "RIGHT\r\n";
		}
		
		assertEquals("", MacrosParam.MULTI_RIGHT.getEffect(param1));
		
		assertEquals("", MacrosParam.MULTI_RIGHT.getEffect(param2));
		
		assertEquals(s, MacrosParam.MULTI_RIGHT.getEffect(param3));
	}

}
