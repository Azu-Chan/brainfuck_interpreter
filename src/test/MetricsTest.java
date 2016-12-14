package test;

import org.junit.Test;
import brainfuck.Metrics;

import static org.junit.Assert.*;

/**
 * Created by user on 14/12/2016.
 */
public class MetricsTest {

    @Test
    public void ProgSizeTest(){
        Metrics.setProgSize(1244324);
        assertEquals(Metrics.getProgSize(),1244324);
    }

    @Test
    public void ExecTimeTest(){
        Metrics.setExecTimeDeb();
        Metrics.setExecTime();
        assertTrue(Metrics.getExecTime()!=0);
    }

    @Test
    public void ExecMoveTest(){
        Metrics.incrementExecMove();
        Metrics.incrementExecMove();
        assertEquals(Metrics.getExecMove(),2);
    }

    @Test
    public void DataMoveTest(){
        Metrics.incrementDataMove();
        assertEquals(Metrics.getDataMove(),1);
    }

    @Test
    public void DataWriteTest(){
        Metrics.incrementDataWrite();
        Metrics.incrementDataWrite();
        Metrics.incrementDataWrite();
        assertEquals(Metrics.getDataWrite(),3);
    }

    @Test
    public void DataRead(){
        Metrics.incrementDataRead();
        assertEquals(Metrics.getDataRead(),1);
    }

}