import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * AnnoyingApple represents the Apple variant of the Annoying fruit player.
 * 
 * @author Monika
 * @version 24 Nov, 2025
 */
public class AnnoyingOrange extends AnnoyingFruit
{
    public AnnoyingOrange(int direction){
        super(direction);
        initStats(100, 100, 70);
        setImage("AnnoyingFruit/AnnoyingOrange.png");
    }
}
