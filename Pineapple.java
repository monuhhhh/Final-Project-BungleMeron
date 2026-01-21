import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pineapple represents the AnnoyingPineapple variant of the Annoying fruit player. 
 * 
 * @author Monika
 * @version 15 Jan 2026
 */
public class Pineapple extends PlayerFruit
{
    /**
     * Act - do whatever the Pineapple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Pineapple(String imagePath) 
    {
        super(imagePath, "Italian pizza",  70, 100); // direction, imagePath, hp
    }
    
    public void act()
    {
        super.act(); // Call the parent's act method
    }
}
