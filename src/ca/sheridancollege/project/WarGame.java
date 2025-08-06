/**
 * SYST 17796 Project Base code.
 * 
 * @author andreas 2025.08.06
 * @author tamurov 2025.08.06
 */

package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class WarGame extends Game {

    private WarPlayer p1;
    private WarPlayer p2;
    private final int WAR_FACE_DOWN = 1;

    public WarGame() {
        super("War Card Game");

        Deck deck = Deck.createStandardDeck();
        deck.shuffle();

        ArrayList<Card> cards = deck.getCards();
        GroupOfCards p1Deck = new GroupOfCards(26);
        GroupOfCards p2Deck = new GroupOfCards(26);

        for (int i = 0; i < cards.size(); i++) {
            if (i % 2 == 0) p1Deck.getCards().add(cards.get(i));
            else p2Deck.getCards().add(cards.get(i));
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        p1 = new HumanPlayer(name, p1Deck);
        p2 = new ComputerPlayer("Computer", p2Deck);

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        setPlayers(players);
    }

    @Override
    public void play() {
        while (p1.cardsLeft() > 0 && p2.cardsLeft() > 0) {
            System.out.println("----------------------------");
            System.out.println("Press ENTER to play next round, or type EXIT to stop.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) break;
            battle(new ArrayList<>());
        }
        declareWinner();
    }

    private void battle(ArrayList<WarCard> pile) {
        WarCard card1 = p1.flip();
        WarCard card2 = p2.flip();
        if (card1 == null || card2 == null) return;

        pile.add(card1);
        pile.add(card2);

        System.out.println(p1.getName() + " plays: " + card1);
        System.out.println(p2.getName() + " plays: " + card2);

        int comparison = card1.compareTo(card2);
        if (comparison > 0) {
            System.out.println(p1.getName() + " wins the round!");
            p1.capture(pile);
        } else if (comparison < 0) {
            System.out.println(p2.getName() + " wins the round!");
            p2.capture(pile);
        } else {
            System.out.println("WAR!");
            resolveWar(pile);
        }

        System.out.println(p1.getName() + " cards left: " + p1.cardsLeft());
        System.out.println(p2.getName() + " cards left: " + p2.cardsLeft());
    }

    private void resolveWar(ArrayList<WarCard> pile) {
        if (!p1.hasEnough(WAR_FACE_DOWN + 1)) {
            System.out.println(p1.getName() + " doesn't have enough cards. " + p2.getName() + " wins!");
            p2.capture(pile);
            return;
        }
        if (!p2.hasEnough(WAR_FACE_DOWN + 1)) {
            System.out.println(p2.getName() + " doesn't have enough cards. " + p1.getName() + " wins!");
            p1.capture(pile);
            return;
        }

        for (int i = 0; i < WAR_FACE_DOWN; i++) {
            pile.add(p1.flip());
            pile.add(p2.flip());
        }
        battle(pile);
    }

    @Override
    public void declareWinner() {
        System.out.println("Game Over");
        if (p1.cardsLeft() == 52) {
            System.out.println(p1.getName() + " has all the cards and wins the game!");
        } else if (p2.cardsLeft() == 52) {
            System.out.println(p2.getName() + " has all the cards and wins the game!");
        } else if (p1.cardsLeft() > p2.cardsLeft()) {
            System.out.println(p1.getName() + " wins the game!");
        } else if (p2.cardsLeft() > p1.cardsLeft()) {
            System.out.println(p2.getName() + " wins the game!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}
