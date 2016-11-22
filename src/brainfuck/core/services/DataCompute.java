package brainfuck.core.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import brainfuck.exceptions.OutOfMemoryException;
import brainfuck.exceptions.OverflowException;

/**
 * DataCompute est la classe n�cessaire � l'ex�cution
 * d'un programme brainfuck (grille + pointeur) 
 * 
 * @author Dylan Ritrovato
 * @author Yijie Wang
 * @author Mohd Nijab
 * 
 * @version 1.6
 */
public class DataCompute {
	private byte[] grille;
	private int pointer;

	/**
	 * Constructeur
	 * Le tableau de m�moire a 30000 cases m�moires
	 * Chaque case contient un byte [-128,127] et commence par -128 car byte 
	 * est comme �a en java, il est pass� en exc�dent
	 * 128 � la sortie comme � l'entr�e afin de simuler un environnement 
	 * [0,255]
	 */
	public DataCompute(){
		grille = new byte[30000];
		pointer = 0;
		for (int i = 0; i < grille.length; i++) {
			grille[i] = Byte.MIN_VALUE;
		}
	}

	/**
	 * getter de la grille en m�moire
	 * 
	 * @return La m�moire brainfuck.
	 */
	public byte[] getGrille(){
		return grille;
	}

	/**
	 * getter de la position pointeur
	 * 
	 * @return La valeur du pointer.
	 */
	public int getPointerPos(){
		return pointer;
	}
	
	/**
	 * getter de la valeur point�e par le pointeur
	 * 
	 * @return La valeur de la case point�e par le pointeur.
	 */
	public byte getPointedValue(){
		return grille[pointer];
	}

	/**
	 * Augmente la valeur de la case m�moire actuellement cibl�e
	 * par le pointeur de 1
	 * 
	 * @throws OverflowException
	 */
	public void increment() throws OverflowException{
		if(grille[pointer] == 127){
			throw new OverflowException(pointer, 256);
		}
		grille[pointer]++;
	}

	/**
	 * Diminue la valeur de la case m�moire actuellement cibl�e
	 * par le pointeur de 1
	 * 
	 * @throws OverflowException
	 */
	public void decrement() throws OverflowException{
		if(grille[pointer] == -128){
			throw new OverflowException(pointer, -1);
		}
		grille[pointer]--;
	}

	/**
	 * Fait avancer le pointeur � la case m�moire suivante
	 * 
	 * @throws OutOfMemoryException
	 */
	public void pointerRight() throws OutOfMemoryException{
		if(pointer == 29999){
			throw new OutOfMemoryException(30000);
		}
		pointer++;
	}

	/**
	 * Fait reculer le pointeur � la case m�moire pr�c�dente
	 * 
	 * @throws OutOfMemoryException
	 */
	public void pointerLeft() throws OutOfMemoryException{
		if(pointer == 0){
			throw new OutOfMemoryException(-1);
		}
		pointer--;
	}

	/**
	 * Ecrit sur la sortie utilis�e le caract�re correspondant au
	 * code ascii de la valeur de la case actuellement point�e.
	 * 
	 * @param out
	 * 
	 * @throws IOException 
	 */
	public void output(FileOutputStream out) throws IOException{
		char ch = (char) (grille[pointer]+128);
		// Si out null, sortie standard
		if (out == null){
			System.out.print((char)ch);
		}
		else{
			out.write(ch);
		}
	}
	
	/**
	 * Injecte dans la case m�moire actuellement point�e la valeur
	 * num�rique du prochain caract�re saisi sur l'entr�e utilis�e
	 * 
	 * @param in
	 * 
	 * @throws IOException 
	 */
	public void input(FileInputStream in) throws IOException{
		// Si in null, entr�e standard
		if(in == null) {
			char c;
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			// Invite de saisie
            System.out.print("\nSaisir un caract�re pour la case m�moire c" + pointer + "\n> ");
            String word;
            do{
            	word = scan.next();
            	scan.nextLine();
            }while (word == null);
            c = word.charAt(0);
			grille[pointer] = (byte)(c - 128);
		}
		else{
			grille[pointer] = (byte) (in.read() - 128);
		}
	}
}
