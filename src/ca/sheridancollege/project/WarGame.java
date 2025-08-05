package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class WarGame extends Game {

    private WarPlayer p1;
    private WarPlayer p2;
    private final int WAR_FACE_DOWN = 1;

    public WarGame() {
        super("War Card Game");

        GroupOfCards deck = createStandardDeck();
        deck.shuffle();

        ArrayList<Card> cards = deck.getCards();
        ArrayList<WarCard> half1 = new ArrayList<>();
        ArrayList<WarCard> half2 = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            if (i % 2 == 0) {
                half1.add((WarCard) cards.get(i));
            } else {
                half2.add((WarCard) cards.get(i));
            }
        }

        GroupOfCards p1Deck = new GroupOfCards(26);
        p1Deck.getCards().addAll(half1);
        GroupOfCards p2Deck = new GroupOfCards(26);
        p2Deck.getCards().addAll(half2);

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

    private GroupOfCards createStandardDeck() {
        GroupOfCards deck = new GroupOfCards(52);
        for (WarCard.Suit suit : WarCard.Suit.values()) {
            for (WarCard.Rank rank : WarCard.Rank.values()) {
                deck.getCards().add(new WarCard(suit, rank));
            }
        }
        return deck;
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        int rounds = 0;

        while (p1.cardsLeft() > 0 && p2.cardsLeft() > 0 && rounds < 10) {
            System.out.println("----------------------------");
            System.out.println("Round " + (rounds + 1));
            System.out.println("Press ENTER to continue playing or type EXIT to stop playing.");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            battle(new ArrayList<>());
            rounds++;
        }

        declareWinner();
    }

    private void battle(ArrayList<WarCard> pile) {
        WarCard card1 = p1.flip();
        WarCard card2 = p2.flip();

        if (card1 == null || card2 == null) {
            return;
        }

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
            System.out.println(p1.getName() + " doesn't have enough cards for war. " + p2.getName() + " wins!");
            p2.capture(pile);
            p1.capture(new ArrayList<>());
            return;
        }

        if (!p2.hasEnough(WAR_FACE_DOWN + 1)) {
            System.out.println(p2.getName() + " doesn't have enough cards for war. " + p1.getName() + " wins!");
            p1.capture(pile);
            p2.capture(new ArrayList<>());
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
        if (p1.cardsLeft() > p2.cardsLeft()) {
            System.out.println(p1.getName() + " wins the game!");
        } else if (p2.cardsLeft() > p1.cardsLeft()) {
            System.out.println(p2.getName() + " wins the game!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}
