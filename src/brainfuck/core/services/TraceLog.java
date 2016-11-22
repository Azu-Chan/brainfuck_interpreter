package brainfuck.core.services;

import brainfuck.Metrics;

import java.io.*;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Classe permettant la génération d'un fichier log
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 2.0
 */
public class TraceLog {
	private File logFile;
	private long nbStep = 0;
	private BufferedWriter logBuff;
	
	public TraceLog(File logFile){
		this.logFile = logFile;
	}
	
	public void initializeLogFile() throws IOException {
		if(logFile.exists()) logFile.delete();

        logFile.createNewFile();
		logBuff = new BufferedWriter(new FileWriter(logFile, true));
        String line;
        // INITIALISATION DU LOG...
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        
        line = "TRACE LOG - " + calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) +
        		" " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
        
        logBuff.write(line); logBuff.write("\r\n");
        // FIN...
    }
	
	public void createTableLog() throws IOException{
		String line;
		
		line = "\r\n\r\n\r\n__________________________________________________________________________\r\n"
				+ "| EX. STEP | LOC. EXEC. POINTER | LOC. DATA POINTER | DATA POINTED VALUE |\r\n"
				+ "+----------+--------------------+-------------------+--------------------+\r\n";
		
        logBuff.write(line);
	}
	
	public void writeLineTableLog(int execPointer, int dataPointer, byte dataVal) throws IOException{
		String line;
		
		line = String.format("|%1$9d |    %2$12d    |    %3$11d    |    %4$13d   |\r\n", (int)nbStep, execPointer, dataPointer, (int)dataVal) +
				"+----------+--------------------+-------------------+--------------------+\r\n";

		logBuff.write(line);
		
        nbStep++;
	}
	
	public void writeSnapMemory(String snapMem) throws IOException{
		String line;
		
		line = "\r\n\r\n" + snapMem;

		logBuff.write(line);
	}
	
	public void writeMetrics() throws IOException{
		String line;
		
		line = "\r\n\r\n\r\nPROGRAM METRICS ARE: \r\n" +
				"PROG_SIZE  = " + Metrics.getProgSize() + "\r\n" +
				"EXEC_TIME  = " + TimeUnit.MILLISECONDS.convert(Metrics.getExecTime(), TimeUnit.NANOSECONDS) + " milliseconds\r\n" +
				"EXEC_MOVE  = " + Metrics.getExecMove() + "\r\n" +
				"DATA_MOVE  = " + Metrics.getDataMove() + "\r\n" +
				"DATA_WRITE = " + Metrics.getDataWrite() + "\r\n" +
				"DATA_READ  = " + Metrics.getDataRead() + "\r\n";

		logBuff.write(line);
	}
	
	public void writeEndOfLog() throws IOException{
		String line;
		
		line = "\r\n\r\n\r\nEND OF LOG FILE";

		logBuff.write(line);
		
		logBuff.close();
	}
}
