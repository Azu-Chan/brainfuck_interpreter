package azuchan.brainfuck;

/**
 * Classe qui stocke les métriques...
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.0
 */
public abstract class Metrics{
    private static long progSize = 0;
    private static long execTime = 0;
    private static long execMove = 0;
    private static long dataMove = 0;
    private static long dataWrite = 0;
    private static long dataRead = 0;
    
    private static long debExecTime = 0;

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
    	Metrics.progSize = progSize;
    }
    
    /**
 	* Getter pour la taille en instructions du programme brainfuck
 	* 
 	* @return taille du programme
 	*/
    public static long getProgSize(){
    	return progSize;
    }
    
    /**
 	* Setter pour le début du temps d'exécution du programme brainfuck (calculé au lancement de la méthode)
 	*/
    public static void setExecTimeDeb(){
    	debExecTime = System.nanoTime();
    }
    
    /**
 	* Setter pour le temps d'exécution du programme brainfuck (calculé au lancement de la méthode)
 	*/
    public static void setExecTime(){
    	execTime = System.nanoTime() - debExecTime;
    }
   
    /**
 	* Getter pour le temps d'exécution du programme brainfuck
 	* 
 	* @return temps d'exécution du programme
 	*/
    public static long getExecTime(){
    	return execTime;
    }
    
    /**
 	* Incrémente l'exec move de 1
 	*/
    public static void incrementExecMove(){
    	execMove++;
    }
    
    /**
 	* Getter sur l'exec move
 	* 
 	* @return taille du programme
 	*/
    public static long getExecMove(){
    	return execMove;
    }
    
    /**
 	* Incrémente le data move de 1
 	*/
    public static void incrementDataMove(){
    	dataMove++;
    }
    
    /**
 	* Getter sur le data move
 	* 
 	* @return taille du programme
 	*/
    public static long getDataMove(){
    	return dataMove;
    }
    
    /**
 	* Incrémente le data write de 1
 	*/
    public static void incrementDataWrite(){
    	dataWrite++;
    }
    
    /**
 	* Getter sur le data write
 	* 
 	* @return taille du programme
 	*/
    public static long getDataWrite(){
    	return dataWrite;
    }
    
    /**
 	* Incrémente le data read de 1
 	*/
    public static void incrementDataRead(){
    	dataRead++;
    }
    
    /**
 	* Getter sur le data read
 	* 
 	* @return taille du programme
 	*/
    public static long getDataRead(){
    	return dataRead;
    }
}
