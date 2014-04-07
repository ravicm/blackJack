/**
 * 
 */
package extensible;

import java.util.ArrayList;

import resources.Card;

/**
 * @author rcmalleb
 *
 */
public interface Simulator {
	public boolean sayYes(ArrayList<Card> hand, Card dealerCard);
	public boolean sayYes();
}
