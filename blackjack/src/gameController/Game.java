package gameController;

import participants.Dealer;
import participants.Player;
import resources.Card;
import resources.Table;
import util.utilities;
import extensible.BettingStrategy;
import extensible.PaymentRatio;
import extensible.SimpleBettingStrategy;
import extensible.SimplePaymentRatio;



public class Game {
	
		public static void main(String[] args){
			Game now=new Game();
			now.run();
			return;
		}
		
		public  void run(){
				
			//Create a player
			//choose a betting strategy
			BettingStrategy bs=new SimpleBettingStrategy();
			
			Player player=new Player(bs);
			
			//Dont bother about dealer and table. We go to a different table hence a different dealer after every hand
			
			//Do you want to Simulate it or Play it?
			//utilities.simulator=new RandomSimulator();
			utilities.print("Are you ready to start the new game?(y/n)");
			while(utilities.sayYes()){
				playAHand(player);
				if(!player.canBet()){
					utilities.print("Player has no more money. Poor guy :( lost all his money!");
					return;
				}
				utilities.print("Are you ready for next game?(y/n)");
			}
			
			utilities.print("Player walks homes happily");
			return;
				
		}

		public  void playAHand(Player player){
			//pick a table
			Table table=new Table();
					
			//dealer is available there
			Dealer dealer=new Dealer();
			
			//set the PaymentRatio
			PaymentRatio pr=new SimplePaymentRatio();
			dealer.setPaymentRatio(pr);
			
			playAHand(player, dealer, table);
			
			return;
		}

		public  void playAHand(Player player,Dealer dealer,Table table){
			
			utilities.print("--------------------------");
			utilities.print("I am now starting the hand");
			
			//player has arrived
			
			//Player puts the bet on table
			int playerBet=player.placeBet();
			utilities.print("Player has bet for "+playerBet);
			table.takeChips(playerBet);
			
			//make initial deal to player on this table
			dealer.initializeDeal(player,table);
						
			//From wiki:
			//Get 21 points on your first two cards (called a blackjack), without a dealer blackjack;
			utilities.print("Initial: "+player.toString());
			if(player.isBlackJack()){
				table.declareWinner(player,dealer);
				return;
			}
			
			//dealer opens up on of his card
			utilities.print("Dealer has card: "+dealer.faceUpACard());
			
			//TODO Multi spli NOT allowed?
			//TODO double down after split? NO
			if(player.canSplit()){
				utilities.print("Would you like to split?");
				if(utilities.sayYes()){
					//match the card and bet amount
					//take away one card from hand
					Card card=player.splitCards();
					
					utilities.print("Your first hand:");
					//let dealer serve one card to this first hand from table
					dealer.initializeDeal(player, table);
					utilities.print("First hand: "+player.toString());
					playRegular(player, dealer, table);
					
					//prepare second hand
					table.takeChips(player.placeBet(playerBet));
					player.hit(card);
					
					//play second hand
					utilities.print("Your second hand:");
					dealer.initializeDeal(player, table);
					utilities.print("second hand: "+player.toString());
					playRegular(player, dealer, table);
					return;
				}
				
			}
			
			
			//do you wanna double down?
			utilities.print("Do you want to double down?");
			if(utilities.sayYes()){
				doubleDown(player,dealer,table);
				return;
			}
			
			playRegular(player, dealer, table);
			return;
			
	}
		public void playRegular(Player player,Dealer dealer,Table table){
			//NO double down. Play regular game
			utilities.print("Hit(y)/Stand(n)?");
			
			//Player - HIT
			while(utilities.sayYes(player.getHand(),dealer.faceUpACard())){
				player.hit(table.draw());
				utilities.print("Update: "+player.toString());
				if(player.isBlackJackOrBusted()){
					table.declareWinner(player, dealer);
					return;
				}
				utilities.print("Hit(y)/Stand(n)?");
			}
			
			//Player - STAND
			utilities.print("Initial: "+dealer.toString());
			//From wiki: The dealer has to take hits until his or her cards total 17 or more points. (In some casinos, the dealer also hits on a "soft" 17—e.g., an initial ace and six.)
			//We are following the generic rule where dealer stands on 17 (hard/soft)
			dealer.hit(table, player);
			
			table.declareWinner(player, dealer);
			
			return;
		}
	
		public void doubleDown(Player player,Dealer dealer, Table table ){
			//execute the double down
			//betting upto original bet
			int doubleDownBet=utilities.randInt(player.getBetAmount());
			utilities.print("Adding "+doubleDownBet+" as double down amount");
			table.takeChips(player.placeBet(doubleDownBet));
			
			//only one hit for player 
			player.hit(table.draw());
			utilities.print("Update: "+player.toString());
			
			if(!player.isBlackJackOrBusted())
				dealer.hit(table,player);
			
			
			table.declareWinner(player, dealer);
		}
}
