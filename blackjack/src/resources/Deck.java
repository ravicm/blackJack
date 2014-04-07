package resources;

import java.util.ArrayList;
import util.utilities;


public class Deck {
	private ArrayList<Card> deck= new ArrayList<Card>();
	
	
	public Deck(){
		this(1);
	}
	
	public Deck(int decks){
		for(int i=0;i<decks;i++){
			for(int j=0;j<52;j++){
				deck.add(new Card(j));
			}
		}
	}
	
	//return next card in the deck/Shoe
	public Card draw(){
		if(deck.size()==0){
			utilities.print("Deck is empty. Cannot draw cards anymore.");
			System.exit(2);
		}
		int random=utilities.randInt(deck.size()-1);
		Card c=deck.get(random);
		deck.remove(random);
		return c;
	}
	
	public String toString(){
		String q="Deck: ";
		for(int i=0;i<deck.size();i++){
			q+=deck.get(i).toString()+",";
		}
		return q;
	}

}
