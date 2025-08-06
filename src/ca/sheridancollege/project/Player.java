/**
 * SYST 17796 Project Base code.
 * 
 * @author andreas 2025.08.06
 * @author tamurov 2025.08.06
 */
package ca.sheridancollege.project;

public abstract class Player {

    private String name; //the unique name for this player

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method to be overridden when the subclass the Player class with the specific type of Player and filled in
     * with logic to play the game.
     */
    public abstract void play();

}
