package brainfuck;

/**
 * Classe qui stocke les m�triques...
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.0
 */
public abstract class Metrics{
    private static long PROG_SIZE = 0;
    private static long EXEC_TIME = 0;
    private static long EXEC_MOVE = 0;
    private static long DATA_MOVE = 0;
    private static long DATA_WRITE = 0;
    private static long DATA_READ = 0;
    
    private static long TEMPS_EXEC_DEB = 0;
    
    public static boolean HAS_LOG_FILE = false;

    /**
 	* Setter pour la taille en instructions du programme brainfuck
 	* 
 	* @param progSize
 	*/
    public static void setProgSize(long progSize){
    	PROG_SIZE = progSize;
    }
    
    /**
 	* Getter pour la taille en instructions du programme brainfuck
 	* 
 	* @return taille du programme
 	*/
    public static long getProgSize(){
    	return PROG_SIZE;
    }
    
    /**
 	* Setter pour le d�but du temps d'ex�cution du programme brainfuck (calcul� au lancement de la m�thode)
 	*/
    public static void setExecTimeDeb(){
    	TEMPS_EXEC_DEB = System.nanoTime();
    }
    
    /**
 	* Setter pour le temps d'ex�cution du programme brainfuck (calcul� au lancement de la m�thode)
 	*/
    public static void setExecTime(){
    	EXEC_TIME = System.nanoTime() - TEMPS_EXEC_DEB;
    }
   
    /**
 	* Getter pour le temps d'ex�cution du programme brainfuck
 	* 
 	* @return temps d'ex�cution du programme
 	*/
    public static long getExecTime(){
    	return EXEC_TIME;
    }
    
    /**
 	* Incr�mente l'exec move de 1
 	*/
    public static void incrementExecMove(){
    	EXEC_MOVE++;
    }
    
    /**
 	* Getter sur l'exec move
 	* 
 	* @return taille du programme
 	*/
    public static long getExecMove(){
    	return EXEC_MOVE;
    }
    
    /**
 	* Incr�mente le data move de 1
 	*/
    public static void incrementDataMove(){
    	DATA_MOVE++;
    }
    
    /**
 	* Getter sur le data move
 	* 
 	* @return taille du programme
 	*/
    public static long getDataMove(){
    	return DATA_MOVE;
    }
    
    /**
 	* Incr�mente le data write de 1
 	*/
    public static void incrementDataWrite(){
    	DATA_WRITE++;
    }
    
    /**
 	* Getter sur le data write
 	* 
 	* @return taille du programme
 	*/
    public static long getDataWrite(){
    	return DATA_WRITE;
    }
    
    /**
 	* Incr�mente le data read de 1
 	*/
    public static void incrementDataRead(){
    	DATA_READ++;
    }
    
    /**
 	* Getter sur le data read
 	* 
 	* @return taille du programme
 	*/
    public static long getDataRead(){
    	return DATA_READ;
    }
}
