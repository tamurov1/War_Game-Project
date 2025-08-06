/**
 * SYST 17796 Project Base code.
 * 
 * @author andreas 2025.08.06
 * @author tamurov 2025.08.06
 * @author jasmeet 2025.08.06
 * @author ishanveer 2025.08.06
 */

package ca.sheridancollege.project;

public class WarCard extends Card {

    private Suit suit;
    private Rank rank;

    public WarCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int compareTo(WarCard other) {
        return this.rank.ordinal() - other.rank.ordinal();
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

}
