package Azu_Chan.brainfuck.macros;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests pour la classe MacroDefine
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class MacroDefineTest {

	@Test
	public void testMacroDefine() {
		MacroDefine macro1 = new MacroDefine("aa", "vvv");
		MacroDefine macro2 = new MacroDefine("7hvjhvjggdxfY", "vvvmnbhkvjcdtdf");
		
		assertEquals("DEFINE", macro1.getName());
		assertEquals("DEFINE", macro2.getName());
		assertEquals("aa", macro1.getParam());
		assertEquals("7hvjhvjggdxfY", macro2.getParam());
		assertEquals("vvv", macro1.getEffect());
		assertEquals("vvvmnbhkvjcdtdf", macro2.getEffect());
	}

}
