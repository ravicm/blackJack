package extensible;

import util.utilities;

public class RandomBettingStrategy implements BettingStrategy{

	public int getBetAmount(int chips){
		return utilities.randInt(chips);
	}
}
