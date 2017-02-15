package azuchan.brainfuck;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * Tests pour la classe Metrics
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.0
 */
public class MetricsTest {

	// Tests pour progSize
	@Test
	public void testProgSize() {
		Metrics.setProgSize(66);
		assertEquals(66, Metrics.getProgSize());
		
		Metrics.setProgSize(0);
		assertEquals(0, Metrics.getProgSize());
		
		Metrics.setProgSize("kkkkk".length());
		assertEquals(5, Metrics.getProgSize());
	}
	
	// Tests pour execTime
	@Test
	public void testExecTime() throws InterruptedException {
		Metrics.setExecTimeDeb();
		Thread.sleep(1000);
		Metrics.setExecTime();
		assertTrue(TimeUnit.MILLISECONDS.convert(Metrics.getExecTime(), TimeUnit.NANOSECONDS) >= 1000);
	}
	
	// Tests pour execMove
	@Test
	public void testExecMove() {
		assertEquals(0, Metrics.getExecMove());
		
		Metrics.incrementExecMove();
		Metrics.incrementExecMove();
		Metrics.incrementExecMove();
		Metrics.incrementExecMove();
		
		assertEquals(4, Metrics.getExecMove());
	}
	
	// Tests pour dataMove
	@Test
	public void testDataMove() {
		assertEquals(0, Metrics.getDataMove());
		
		Metrics.incrementDataMove();
		Metrics.incrementDataMove();
		Metrics.incrementDataMove();
		Metrics.incrementDataMove();
		
		assertEquals(4, Metrics.getDataMove());
	}
	
	// Tests pour dataWrite
	@Test
	public void testDataWrite() {
		assertEquals(0, Metrics.getDataWrite());
		
		Metrics.incrementDataWrite();
		Metrics.incrementDataWrite();
		Metrics.incrementDataWrite();
		Metrics.incrementDataWrite();
		
		assertEquals(4, Metrics.getDataWrite());
	}
	
	// Tests pour dataRead
	@Test
	public void testDataRead() {
		assertEquals(0, Metrics.getDataRead());
		
		Metrics.incrementDataRead();
		Metrics.incrementDataRead();
		Metrics.incrementDataRead();
		Metrics.incrementDataRead();
		
		assertEquals(4, Metrics.getDataRead());
	}

}
