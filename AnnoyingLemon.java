import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * AnnoyingApple represents the Apple variant of the Annoying fruit player.
 * 
 * @author Monika
 * @version 24 Nov, 2025
 */
public class AnnoyingLemon extends AnnoyingFruit
{
    public AnnoyingLemon(int direction){
        super(direction);
        initStats(100, 100, 70);
        setImage("AnnoyingFruit/AnnoyingLemon.png");
    }
}
