import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Goal represent an invisible target area in the game.
 * When the player reaches this area, it can be used
 * to trigger level completion or victory conditions.
 * 
 * @author Carmen Cheung 
 * @version Jan 20, 2026
 */
public class Goal extends Actor
{
    /**
     * COnstructs the Goal object.
     */
    public Goal() {
        // Make platform invisible since background shows shelves
        GreenfootImage img = new GreenfootImage(40, 40);
        img.setColor(new Color(0, 0, 0, 0)); // transparent
        img.fill();
        setImage(img);
    }
}
