package extensible;

public class SimpleBettingStrategy implements BettingStrategy{
	public int getBetAmount(int chips){
		return Math.min(10, chips);
	}
}
