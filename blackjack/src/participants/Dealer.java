package participants;

import extensible.PaymentRatio;
import resources.Card;
import resources.Table;
import util.utilities;


public class Dealer extends Participant {
	private static final int playerMinValue = 17;
	private PaymentRatio paymentratio=null;
	public Dealer(){
		this.chips=10000;
	}
	
	
	public Dealer(int chips){
		this.chips=chips;
	}
	
	@Override
	public String toString() {
		return "Dealer:"+super.toString();
	}
	
	public void setPaymentRatio(PaymentRatio paymentratio){
		this.paymentratio=paymentratio;
		return;
	}
	public void initializeDeal(Player player,Table table) {
		for(int i=player.getHand().size();i<2;i++){
			player.hit(table.draw());
		}
		while(hand.size()<2){
			hit(table.draw());
		}
	}

	public Card faceUpACard() {
		return hand.get(utilities.randInt(2)-1);
	}
	
	public void hit(Table table,Player player){
		while(getHandValue()<playerMinValue){
			super.hit(table.draw());
			utilities.print("Update: "+toString());
		}
	}
	
	public int placeBet(int playerChips, int playerState){
		int bet=playerChips;
		if(paymentratio!=null)
			bet=paymentratio.placeBet(playerChips, playerState);
		return super.placeBet(bet);
	}
	
}
