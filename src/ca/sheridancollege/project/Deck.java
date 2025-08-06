/**
 * SYST 17796 Project Base code.
 * 
 * @author andreas 2025.08.06
 * @author tamurov 2025.08.06
 */
package ca.sheridancollege.project;

public class Deck extends GroupOfCards {
    public Deck(int size) {
        super(size);
    }

    public static Deck createStandardDeck() {
        Deck deck = new Deck(52);
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.getCards().add(new WarCard(suit, rank));
            }
        }
        return deck;
    }
}
