package test.macros;

import static org.junit.Assert.*;

import org.junit.Test;

import brainfuck.Instructions;
import brainfuck.macros.MacrosParam;
import brainfuck.macros.MacrosSimple;

public class MacrosParamTest {
	MacrosParam macrosParam;
	String response = "";
	String param = "0";


	@Test
	public void testGetName() {
		assertEquals("MULTI_INCR",MacrosParam.MULTI_INCR.getName());
	}
	
	@Test
	public void testGetEffectMultiIncr() {
		macrosParam = MacrosParam.MULTI_INCR;
		macrosParam.setParam("48");
		param = macrosParam.getParam();
		int n = Integer.valueOf(param);
		for(int i = 0; i < n; i++){
			response += Instructions.INCR.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosParam.MULTI_INCR.getEffect());
	}
	
	@Test
	public void testGetEffectMultiDecr() {
		macrosParam = MacrosParam.MULTI_DECR;
		macrosParam.setParam("48");
		param = macrosParam.getParam();
		int n = Integer.valueOf(param);
		for(int i = 0; i < n; i++){
			response += Instructions.DECR.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosParam.MULTI_DECR.getEffect());
	}
	
	@Test
	public void testGetEffectMultiLeft() {
		macrosParam = MacrosParam.MULTI_LEFT;
		macrosParam.setParam("48");
		param = macrosParam.getParam();
		int n = Integer.valueOf(param);
		for(int i = 0; i < n; i++){
			response += Instructions.LEFT.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosParam.MULTI_LEFT.getEffect());
	}
	
	@Test
	public void testGetEffectMultiRight() {
		macrosParam = MacrosParam.MULTI_RIGHT;
		macrosParam.setParam("48");
		param = macrosParam.getParam();
		int n = Integer.valueOf(param);
		for(int i = 0; i < n; i++){
			response += Instructions.RIGHT.getLongSyntax() + "\r\n";
		}
		assertEquals(response,MacrosParam.MULTI_RIGHT.getEffect());
	}

}
