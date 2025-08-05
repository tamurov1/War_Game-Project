package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class WarPlayer extends Player {

    protected GroupOfCards pile;

    public WarPlayer(String name, GroupOfCards startingPile) {
        super(name);
        this.pile = startingPile;
    }

    public int cardsLeft() {
        return pile.getCards().size();
    }

    public WarCard flip() {
        if (pile.getCards().isEmpty()) {
            return null;
        }
        return (WarCard) pile.getCards().remove(0);
    }

    public void capture(ArrayList<WarCard> won) {
        
        List<Card> shuffled = new ArrayList<>(won);
        Collections.shuffle(shuffled);
        pile.getCards().addAll(shuffled);
        
    }

    public boolean hasEnough(int needed) {
        return pile.getCards().size() >= needed;
    }

}
