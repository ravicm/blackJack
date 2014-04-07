package participants;
import resources.Card;

public interface actions {
	
	//Every participant should have following things known:
	//Should have money to bet
	public boolean canBet();
	
	/*
	//Should know how to bet and how much to bet
	public int placeBet(int chips);
	*/
	
	//Should know how to collect bet if wins
	public void collectBet(int chips);
	
	//After the initial deal a hit
	public void hit(Card c);
	
	//or stand
	public void stand();
	
	//Know the handvalue and hand at any point of time
	public int getHandValue();
	
	//Did the participant win?
	public boolean isBlackJack();
	
	//Did participant loose?
	public boolean isBusted();
	
	//A win or lose hand should be cleared. So we try to identify it
	public boolean isBlackJackOrBusted();
}
