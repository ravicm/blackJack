package participants;

import resources.Card;
import extensible.BettingStrategy;


public class Player extends Participant {
	private int defaultPlayerChips=100;
	private int ace=1;
	public Player(){
		this.chips=defaultPlayerChips;
	}
	
	public Player(int chips,BettingStrategy bs){
		this.bs=bs;
		this.chips=chips;
	}
	
	public Player(int chips){
		this.chips=chips;
	}
	
	public Player(BettingStrategy bs){
		this.bs=bs;
		this.chips=100;
	}

	public String toString() {
		return "Player: "+super.toString();
	}
	
	public boolean canSplit(){
		//Split is when both cards have same value
		//Split not allowed on ace
		return (hand.get(0).getFaceValue()!=ace )&& (hand.get(0).getFaceValue()==hand.get(1).getFaceValue());
	}
	
	public Card splitCards(){
		handValue-=hand.get(1).getValue();
		Card c=hand.get(1);
		hand.remove(1);
		return c;
	}
}
