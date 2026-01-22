import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Platform represents a surface that the player can stand or land on during the game.
 * 
 * @author Carmen Cheung 
 * @version Jan 16, 2026
 */
public class Platform extends Actor
{
    public Platform() {
        // Make platform invisible since background shows shelves
        GreenfootImage img = new GreenfootImage(40, 40);
        img.setColor(new Color(0, 0, 0, 0)); // Fully transparent
        img.fill();
        setImage(img);
    }
    
    //To see collision boxes during testing:
    /*
    public Platform() {
        GreenfootImage img = new GreenfootImage(40, 40);
        img.setColor(new Color(255, 0, 0, 50)); // Semi-transparent red
        img.fill();
        setImage(img);
    }
    */
}
