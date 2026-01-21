import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ladder represents a climable object in the game. 
 * 
 * @author Carmen Cheung 
 * @version Jan 16, 2026
 */

public class Ladder extends Actor
{
    /**
     * Constructs a Ladder object.
     * 
     */
    public Ladder() {
        GreenfootImage img = new GreenfootImage(40, 40);
        img.setColor(Color.ORANGE);
        img.fillRect(0, 0, 10, 40);
        img.fillRect(30, 0, 10, 40);
        img.fillRect(0, 10, 40, 5);
        img.fillRect(0, 25, 40, 5);
        setImage(img);
    }
}
