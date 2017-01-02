package brainfuck.core.services;

import java.io.*;
import java.util.Calendar;

/**
 * Classe permettant la génération d'un fichier log
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 2.1
 */
public class TraceLog {
	private File logFile;
	private long nbStep = 0;
	private BufferedWriter logBuff;
	
	/**
	 * Constructeur...
	 * 
	 * @param logFile
	 */
	public TraceLog(File logFile){
		this.logFile = logFile;
	}
	
	/**
	 * Initialise le fichier de log
	 * 
	 * @throw IOException
	 */
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
	
	/**
	 * Créé la table de traçage de l'execution 
	 * 
	 * @throw IOException
	 */
	public void createTableLog() throws IOException{
		String line;
		
		line = "\r\n\r\n\r\n__________________________________________________________________________\r\n"
				+ "| EX. STEP | LOC. EXEC. POINTER | LOC. DATA POINTER | DATA POINTED VALUE |\r\n"
				+ "+----------+--------------------+-------------------+--------------------+\r\n";
		
        logBuff.write(line);
	}
	
	/**
	 * Remplis la table de tra�age de l'execution 
	 * 
	 * @param execPointer
	 * @param dataPointer
	 * @param dataVal
	 * 
	 * @throw IOException
	 */
	public void writeLineTableLog(int execPointer, int dataPointer, byte dataVal) throws IOException{
		String line;
		
		line = String.format("|%1$9d |    %2$12d    |    %3$11d    |    %4$13d   |\r\n", (int)nbStep, execPointer, dataPointer, (int)dataVal) +
				"+----------+--------------------+-------------------+--------------------+\r\n";

		logBuff.write(line);
		
        nbStep++;
	}
	
	/**
	 * Ecrit le snap de la m�moire dans le log
	 * 
	 * @param snapMem
	 * 
	 * @throw IOException
	 */
	public void writeSnapMemory(String snapMem) throws IOException{
		String line;
		
		line = "\r\n\r\n" + snapMem;

		logBuff.write(line);
	}
	
	/**
	 * Ecrit le snap ds m�triques dans le log
	 * 
	 * @param metrics
	 * 
	 * @throw IOException
	 */
	public void writeMetrics(String metrics) throws IOException{
		String line;
		
		line = metrics.replace("\n", "\r\n");

		logBuff.write(line);
	}
	
	/**
	 * Ecrit la fin du log log
	 * 
	 * @throw IOException
	 */
	public void writeEndOfLog() throws IOException{
		String line;
		
		line = "\r\n\r\n\r\nEND OF LOG FILE\r\n";

		logBuff.write(line);
		
		logBuff.close();
	}
}
