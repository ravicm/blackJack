package extensible;

public class SimplePaymentRatio implements PaymentRatio {
	private enum participantStatus {BLACKJACK,BUSTED,BETTERHANDVAL};
	
	public int placeBet(int playerChips,int playerState){
		switch(playerState){
			case 1:
				return (int)(1.5*playerChips);
			case 2:
				return playerChips;
			case 3:
				return playerChips;
		}
		return 0;
	}
}
