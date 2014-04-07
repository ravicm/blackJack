package extensible;


public class MartingaleBettingStrategy  implements BettingStrategy{
	private int bet=1;
	public int getBetAmount(int chips){
		if(bet*2<=chips){
			bet*=2;
			return bet;
		}
		
		return chips;
	}
	
}
