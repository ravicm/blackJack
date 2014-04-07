package extensible;
import java.util.ArrayList;

import resources.Card;
import util.utilities;

public class RandomSimulator implements Simulator{

	@Override
	public boolean sayYes(ArrayList<Card> hand, Card dealerCard) {
		return utilities.randInt(2)>1;
	}
	public boolean sayYes() {
		return utilities.randInt(2)>1;
	}
}
