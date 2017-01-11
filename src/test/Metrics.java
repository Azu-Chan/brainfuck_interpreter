package test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * Tests pour la classe Metrics
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public class Metrics {

	// Tests pour PROG_SIZE
	@Test
	public void testProgSize() {
		brainfuck.Metrics.setProgSize(66);
		assertEquals(66, brainfuck.Metrics.getProgSize());
		
		brainfuck.Metrics.setProgSize(0);
		assertEquals(0, brainfuck.Metrics.getProgSize());
		
		brainfuck.Metrics.setProgSize("kkkkk".length());
		assertEquals(5, brainfuck.Metrics.getProgSize());
	}
	
	// Tests pour EXEC_TIME
	@Test
	public void testExecTime() throws InterruptedException {
		brainfuck.Metrics.setExecTimeDeb();
		Thread.sleep(1000);
		brainfuck.Metrics.setExecTime();
		assertTrue(TimeUnit.MILLISECONDS.convert(brainfuck.Metrics.getExecTime(), TimeUnit.NANOSECONDS) >= 1000);
	}
	
	// Tests pour EXEC_MOVE
	@Test
	public void testExecMove() {
		assertEquals(0, brainfuck.Metrics.getExecMove());
		
		brainfuck.Metrics.incrementExecMove();
		brainfuck.Metrics.incrementExecMove();
		brainfuck.Metrics.incrementExecMove();
		brainfuck.Metrics.incrementExecMove();
		
		assertEquals(4, brainfuck.Metrics.getExecMove());
	}
	
	// Tests pour DATA_MOVE
	@Test
	public void testDataMove() {
		assertEquals(0, brainfuck.Metrics.getDataMove());
		
		brainfuck.Metrics.incrementDataMove();
		brainfuck.Metrics.incrementDataMove();
		brainfuck.Metrics.incrementDataMove();
		brainfuck.Metrics.incrementDataMove();
		
		assertEquals(4, brainfuck.Metrics.getDataMove());
	}
	
	// Tests pour DATA_WRITE
	@Test
	public void testDataWrite() {
		assertEquals(0, brainfuck.Metrics.getDataWrite());
		
		brainfuck.Metrics.incrementDataWrite();
		brainfuck.Metrics.incrementDataWrite();
		brainfuck.Metrics.incrementDataWrite();
		brainfuck.Metrics.incrementDataWrite();
		
		assertEquals(4, brainfuck.Metrics.getDataWrite());
	}
	
	// Tests pour DATA_READ
	@Test
	public void testDataRead() {
		assertEquals(0, brainfuck.Metrics.getDataRead());
		
		brainfuck.Metrics.incrementDataRead();
		brainfuck.Metrics.incrementDataRead();
		brainfuck.Metrics.incrementDataRead();
		brainfuck.Metrics.incrementDataRead();
		
		assertEquals(4, brainfuck.Metrics.getDataRead());
	}

}
