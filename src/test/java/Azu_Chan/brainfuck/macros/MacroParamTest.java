package Azu_Chan.brainfuck.macros;

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
		assertEquals("MULTI_INCR", MacrosParam.MULTI_INCR.getName());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "INCR\r\n";
		}
		
		MacrosParam.MULTI_INCR.setParam(param1);
		assertEquals(param1, MacrosParam.MULTI_INCR.getParam());
		assertEquals("", MacrosParam.MULTI_INCR.getEffect());
		
		MacrosParam.MULTI_INCR.setParam(param2);
		assertEquals(param2, MacrosParam.MULTI_INCR.getParam());
		assertEquals("", MacrosParam.MULTI_INCR.getEffect());
		
		MacrosParam.MULTI_INCR.setParam(param3);
		assertEquals(param3, MacrosParam.MULTI_INCR.getParam());
		assertEquals(s, MacrosParam.MULTI_INCR.getEffect());
	}
	
	@Test
	public void testMultiDecr() {
		assertEquals("MULTI_DECR", MacrosParam.MULTI_DECR.getName());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "DECR\r\n";
		}
		
		MacrosParam.MULTI_DECR.setParam(param1);
		assertEquals(param1, MacrosParam.MULTI_DECR.getParam());
		assertEquals("", MacrosParam.MULTI_DECR.getEffect());
		
		MacrosParam.MULTI_DECR.setParam(param2);
		assertEquals(param2, MacrosParam.MULTI_DECR.getParam());
		assertEquals("", MacrosParam.MULTI_DECR.getEffect());
		
		MacrosParam.MULTI_DECR.setParam(param3);
		assertEquals(param3, MacrosParam.MULTI_DECR.getParam());
		assertEquals(s, MacrosParam.MULTI_DECR.getEffect());
	}
	
	@Test
	public void testMultiLeft() {
		assertEquals("MULTI_LEFT", MacrosParam.MULTI_LEFT.getName());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "LEFT\r\n";
		}
		
		MacrosParam.MULTI_LEFT.setParam(param1);
		assertEquals(param1, MacrosParam.MULTI_LEFT.getParam());
		assertEquals("", MacrosParam.MULTI_LEFT.getEffect());
		
		MacrosParam.MULTI_LEFT.setParam(param2);
		assertEquals(param2, MacrosParam.MULTI_LEFT.getParam());
		assertEquals("", MacrosParam.MULTI_LEFT.getEffect());
		
		MacrosParam.MULTI_LEFT.setParam(param3);
		assertEquals(param3, MacrosParam.MULTI_LEFT.getParam());
		assertEquals(s, MacrosParam.MULTI_LEFT.getEffect());
	}
	
	@Test
	public void testMultiRight() {
		assertEquals("MULTI_RIGHT", MacrosParam.MULTI_RIGHT.getName());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "RIGHT\r\n";
		}
		
		MacrosParam.MULTI_RIGHT.setParam(param1);
		assertEquals(param1, MacrosParam.MULTI_RIGHT.getParam());
		assertEquals("", MacrosParam.MULTI_RIGHT.getEffect());
		
		MacrosParam.MULTI_RIGHT.setParam(param2);
		assertEquals(param2, MacrosParam.MULTI_RIGHT.getParam());
		assertEquals("", MacrosParam.MULTI_RIGHT.getEffect());
		
		MacrosParam.MULTI_RIGHT.setParam(param3);
		assertEquals(param3, MacrosParam.MULTI_RIGHT.getParam());
		assertEquals(s, MacrosParam.MULTI_RIGHT.getEffect());
	}

}
