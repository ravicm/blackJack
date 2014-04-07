package resources;


import participants.Dealer;
import participants.Participant;
import participants.Player;
import util.utilities;

public class Table {
		
	private int chips=0;
	private Deck deck=null;
	
	public Table(){
		this.chips=0;
		this.deck=new Deck();
	}
	
	public Table(int decks){
		this.chips=0;
		this.deck=new Deck(decks);
	}
	
	public int giveChips(){
		return chips;
	}
	
	public void takeChips(int chips){
		this.chips+=chips;
	}

	public Card draw() {
		return this.deck.draw();
	}

	public void declareWinner(Player player, Dealer dealer) {
		if(player.isBlackJack()){
			int dealerBet=dealer.placeBet(player.getBetAmount(),1);
			takeChips(dealerBet);
			printSummary("Player is blackjack, won "+dealerBet+" chips", player, dealer);
			return;
		}
		
		else if(player.isBusted()){
			printSummary("Player is busted, lost "+player.getBetAmount()+" chips", dealer, player);
			return;
		}
		
		else if(dealer.isBusted()){
			int dealerBet=dealer.placeBet(player.getBetAmount(),2);
			takeChips(dealerBet);
			printSummary("Dealer is busted. Player won "+player.getBetAmount()+" chips", player, dealer);
			return;
		}
		
		else if(dealer.isBlackJack()){
			printSummary("Dealer is blackjack. Player lost "+player.getBetAmount()+" chips", dealer, player);
			return;
		}
		
		else if(player.getHandValue()>dealer.getHandValue()){
			int dealerBet=dealer.placeBet(player.getBetAmount(),3);
			takeChips(dealerBet);
			printSummary("Player has better hand value. Player won "+player.getBetAmount()+" chips", player, dealer);
			return;
		}
		
		else if(player.getHandValue()<dealer.getHandValue()){
			printSummary("Dealer has better hand value. Player lost "+player.getBetAmount()+" chips", dealer, player);
			return;
		}else if(player.getHandValue()==dealer.getHandValue()){
			utilities.print("\n========\nSummary:\n PUSH\nBoth have same hand value");
			player.collectBet(giveChips());
			utilities.print(player.toString());
			utilities.print(dealer.toString());
			player.clearHand();
			dealer.clearHand();
			clearTable();
			utilities.print("Hand clear\n");
			return;
		}
	}
		
	private void printSummary(String reason,Participant winner,Participant loser ){
		winner.collectBet(giveChips());
		utilities.print("\n========\nSummary:"+reason);
		utilities.print("*Winner*:"+winner.toString());
		utilities.print("!Loser!:"+loser.toString());
		winner.clearHand();
		loser.clearHand();
		clearTable();
		utilities.print("Hand clear\n");
	}

	private void clearTable() {
		this.chips=0;
		this.deck=new Deck();
	}
	public String toString(){
		return "Table: "+
					"Chips on table: "+chips+"Deck on table: "+deck.toString();
		
	}
	
}
