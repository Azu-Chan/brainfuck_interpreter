package azuchan.brainfuck;

/**
 * Classe qui stocke les métriques...
 * 
 * @author Dylan Ritrovato
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

	/**
 	* Privé pour cacher la visibilité
 	*/
    private Metrics(){
    	/* EMPTY */
    }

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
 	* Setter pour le début du temps d'exécution du programme brainfuck (calculé au lancement de la méthode)
 	*/
    public static void setExecTimeDeb(){
    	TEMPS_EXEC_DEB = System.nanoTime();
    }
    
    /**
 	* Setter pour le temps d'exécution du programme brainfuck (calculé au lancement de la méthode)
 	*/
    public static void setExecTime(){
    	EXEC_TIME = System.nanoTime() - TEMPS_EXEC_DEB;
    }
   
    /**
 	* Getter pour le temps d'exécution du programme brainfuck
 	* 
 	* @return temps d'exécution du programme
 	*/
    public static long getExecTime(){
    	return EXEC_TIME;
    }
    
    /**
 	* Incrémente l'exec move de 1
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
 	* Incrémente le data move de 1
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
 	* Incrémente le data write de 1
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
 	* Incrémente le data read de 1
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
