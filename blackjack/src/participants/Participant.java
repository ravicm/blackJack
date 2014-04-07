package participants;

import java.util.ArrayList;
import extensible.BettingStrategy;
import extensible.SimpleBettingStrategy;
import resources.Card;


public abstract class Participant implements actions {
	
	private int blackJack=21;
	private int aceValueDiff=10;
	protected int handValue=0;
	protected int chips=0;
	protected ArrayList<Card> hand=new ArrayList<Card>();
	protected int aces=0;
	protected int betAmount=0;
	protected BettingStrategy bs=new SimpleBettingStrategy();
	
	public void hit(Card c){
		this.hand.add(c);
		this.handValue+=c.getValue();
		if(c.getValue()==1){
			this.aces++;
			this.handValue+=aceValueDiff;
		}
		if(this.handValue>blackJack && this.aces>0){
			this.handValue-=aceValueDiff;
			this.aces--;
		}
	}
	
	public int getHandValue(){
		return handValue;
	}
	
	public void stand(){
		
	}
	
	public boolean canBet(){
		return chips>0?true:false;
	}
	
	public int placeBet(){
		return placeBet(bs.getBetAmount(chips));
	}
	
	public int placeBet(int bet){
		giveChips(bet);
		betAmount+=bet;
		return bet;
	}
	
	public void collectBet(int chips){
		takeChips(chips);
	}

	public int getBetAmount(){
		return betAmount;
	}
	
	private int giveChips(int betAmount) {
		this.chips-=betAmount;
		return betAmount;
	}
	
	private void takeChips(int betAmount) {
		this.chips+=betAmount;
	}
	
	public String toString(){
		String q="Remaining Chips:"+this.chips+" Handvalue:"+getHandValue()+"; Hand:";
		for(Card c: this.hand){
			q+=c.toString()+",";
		}
		return q;
	}
	
	public boolean isBlackJack(){
		return getHandValue()==blackJack;
	}
	public boolean isBusted(){
		return getHandValue()>blackJack;
	}
	
	public boolean isBlackJackOrBusted(){
		return isBlackJack() || isBusted();
	}
	
	public void clearHand(){
		hand=new ArrayList<Card>();
		handValue=0;
		aces=0;
		betAmount=0;
	}
	
	public ArrayList<Card> getHand(){
		return this.hand;
	}
	
}

