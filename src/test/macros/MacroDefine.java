package test.macros;

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
public class MacroDefine {

	@Test
	public void testMacroDefine() {
		brainfuck.macros.MacroDefine macro1 = new brainfuck.macros.MacroDefine("aa", "vvv");
		brainfuck.macros.MacroDefine macro2 = new brainfuck.macros.MacroDefine("7hvjhvjggdxfY", "vvvmnbhkvjcdtdf");
		
		assertEquals("DEFINE", macro1.getName());
		assertEquals("DEFINE", macro2.getName());
		assertEquals("aa", macro1.getParam());
		assertEquals("7hvjhvjggdxfY", macro2.getParam());
		assertEquals("vvv", macro1.getEffect());
		assertEquals("vvvmnbhkvjcdtdf", macro2.getEffect());
	}

}
