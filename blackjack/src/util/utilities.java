package util;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import resources.Card;
import extensible.Simulator;

public class utilities {
	public static Simulator simulator=null;
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static int randInt(int max) {
		return randInt(1,max);
	}

	public static void print(String x) {
		System.out.println(x);
	}
	
	public static boolean sayYes(){
		if(simulator!=null)
			return simulator.sayYes();
		Scanner reader = new Scanner(System.in);
		char c1=reader.next(".").charAt(0);
		if(c1=='y' || c1=='Y')
			return true;
		return false;
		
	}

	public static boolean sayYes(ArrayList<Card> hand, Card dealerCard) {
		if(simulator==null)
			return sayYes();
		return simulator.sayYes(hand,dealerCard);
	} 
}
