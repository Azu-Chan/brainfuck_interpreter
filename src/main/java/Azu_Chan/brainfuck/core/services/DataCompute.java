package Azu_Chan.brainfuck.core.services;

import java.io.*;
import java.util.Scanner;

import Azu_Chan.brainfuck.exceptions.*;

/**
 * DataCompute est la classe nécessaire à l'exécution
 * d'un programme brainfuck (grille + pointeur) 
 * 
 * @author Dylan Ritrovato
 * 
 * @version 1.6
 */
public class DataCompute {
	private byte[] grille;
	private int pointer;

	/**
	 * Constructeur
	 * Le tableau de mémoire a 30000 cases mémoires
	 * Chaque case contient un byte [-128,127] et commence par -128 car byte 
	 * est comme ça en java, il est passé en excédent
	 * 128 à la sortie comme à l'entrée afin de simuler un environnement 
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
	 * getter de la grille en mémoire
	 * 
	 * @return La mémoire brainfuck.
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
	 * getter de la valeur pointée par le pointeur
	 * 
	 * @return La valeur de la case pointée par le pointeur.
	 */
	public byte getPointedValue(){
		return grille[pointer];
	}

	/**
	 * Augmente la valeur de la case mémoire actuellement ciblée
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
	 * Diminue la valeur de la case mémoire actuellement ciblée
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
	 * Fait avancer le pointeur à la case mémoire suivante
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
	 * Fait reculer le pointeur à la case mémoire précédente
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
	 * Ecrit sur la sortie utilisée le caractère correspondant au
	 * code ascii de la valeur de la case actuellement pointée.
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
	 * Injecte dans la case mémoire actuellement pointée la valeur
	 * numérique du prochain caractère saisi sur l'entrée utilisée
	 * 
	 * @param in
	 * 
	 * @throws IOException 
	 */
	public void input(FileInputStream in) throws IOException{
		// Si in null, entrée standard
		if(in == null) {
			char c;
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			// Invite de saisie
            System.out.print("\nSaisir un caractère pour la case mémoire c" + pointer + "\n> ");
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
