package test.macros;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests pour la classe MacroParam
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class MacroParam {

	@Test
	public void testMultiIncr() {
		assertEquals("MULTI_INCR", brainfuck.macros.MacrosParam.MULTI_INCR.getName());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "INCR\r\n";
		}
		
		brainfuck.macros.MacrosParam.MULTI_INCR.setParam(param1);
		assertEquals(param1, brainfuck.macros.MacrosParam.MULTI_INCR.getParam());
		assertEquals("", brainfuck.macros.MacrosParam.MULTI_INCR.getEffect());
		
		brainfuck.macros.MacrosParam.MULTI_INCR.setParam(param2);
		assertEquals(param2, brainfuck.macros.MacrosParam.MULTI_INCR.getParam());
		assertEquals("", brainfuck.macros.MacrosParam.MULTI_INCR.getEffect());
		
		brainfuck.macros.MacrosParam.MULTI_INCR.setParam(param3);
		assertEquals(param3, brainfuck.macros.MacrosParam.MULTI_INCR.getParam());
		assertEquals(s, brainfuck.macros.MacrosParam.MULTI_INCR.getEffect());
	}
	
	@Test
	public void testMultiDecr() {
		assertEquals("MULTI_DECR", brainfuck.macros.MacrosParam.MULTI_DECR.getName());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "DECR\r\n";
		}
		
		brainfuck.macros.MacrosParam.MULTI_DECR.setParam(param1);
		assertEquals(param1, brainfuck.macros.MacrosParam.MULTI_DECR.getParam());
		assertEquals("", brainfuck.macros.MacrosParam.MULTI_DECR.getEffect());
		
		brainfuck.macros.MacrosParam.MULTI_DECR.setParam(param2);
		assertEquals(param2, brainfuck.macros.MacrosParam.MULTI_DECR.getParam());
		assertEquals("", brainfuck.macros.MacrosParam.MULTI_DECR.getEffect());
		
		brainfuck.macros.MacrosParam.MULTI_DECR.setParam(param3);
		assertEquals(param3, brainfuck.macros.MacrosParam.MULTI_DECR.getParam());
		assertEquals(s, brainfuck.macros.MacrosParam.MULTI_DECR.getEffect());
	}
	
	@Test
	public void testMultiLeft() {
		assertEquals("MULTI_LEFT", brainfuck.macros.MacrosParam.MULTI_LEFT.getName());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "LEFT\r\n";
		}
		
		brainfuck.macros.MacrosParam.MULTI_LEFT.setParam(param1);
		assertEquals(param1, brainfuck.macros.MacrosParam.MULTI_LEFT.getParam());
		assertEquals("", brainfuck.macros.MacrosParam.MULTI_LEFT.getEffect());
		
		brainfuck.macros.MacrosParam.MULTI_LEFT.setParam(param2);
		assertEquals(param2, brainfuck.macros.MacrosParam.MULTI_LEFT.getParam());
		assertEquals("", brainfuck.macros.MacrosParam.MULTI_LEFT.getEffect());
		
		brainfuck.macros.MacrosParam.MULTI_LEFT.setParam(param3);
		assertEquals(param3, brainfuck.macros.MacrosParam.MULTI_LEFT.getParam());
		assertEquals(s, brainfuck.macros.MacrosParam.MULTI_LEFT.getEffect());
	}
	
	@Test
	public void testMultiRight() {
		assertEquals("MULTI_RIGHT", brainfuck.macros.MacrosParam.MULTI_RIGHT.getName());
		
		String param1 = "0";
		String param2 = "-1";
		String param3 = "12";
		
		String s = "";
		for(int i=0; i<12; i++){
			s += "RIGHT\r\n";
		}
		
		brainfuck.macros.MacrosParam.MULTI_RIGHT.setParam(param1);
		assertEquals(param1, brainfuck.macros.MacrosParam.MULTI_RIGHT.getParam());
		assertEquals("", brainfuck.macros.MacrosParam.MULTI_RIGHT.getEffect());
		
		brainfuck.macros.MacrosParam.MULTI_RIGHT.setParam(param2);
		assertEquals(param2, brainfuck.macros.MacrosParam.MULTI_RIGHT.getParam());
		assertEquals("", brainfuck.macros.MacrosParam.MULTI_RIGHT.getEffect());
		
		brainfuck.macros.MacrosParam.MULTI_RIGHT.setParam(param3);
		assertEquals(param3, brainfuck.macros.MacrosParam.MULTI_RIGHT.getParam());
		assertEquals(s, brainfuck.macros.MacrosParam.MULTI_RIGHT.getEffect());
	}

}
