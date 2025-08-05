package ca.sheridancollege.project;

public class WarCard extends Card {

    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

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
