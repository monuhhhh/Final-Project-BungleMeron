import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    /*
    public Platform() {
        // Make platform invisible since background shows shelves
        GreenfootImage img = new GreenfootImage(40, 40);
        img.setColor(new Color(0, 0, 0, 0)); // Fully transparent
        img.fill();
        setImage(img);
    }
    */
    // OR if you want to see collision boxes during testing:
    
    public Platform() {
        GreenfootImage img = new GreenfootImage(40, 40);
        img.setColor(new Color(255, 0, 0, 50)); // Semi-transparent red
        img.fill();
        setImage(img);
    }
    
}
