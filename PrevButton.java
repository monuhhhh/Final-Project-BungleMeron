import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PrevButton allows the player to cycle to the previous fruit option in the CustomizeWorld.
 * 
 * @author Carmen Cheung 
 * @version Jan 13, 2025
 */
public class PrevButton extends Button
{
    private CustomizeWorld world; 
    /**
     * Construct the PrevButton
     * 
     * @param w the CustomizeWorld this button belongs to
     */
    public PrevButton(CustomizeWorld w){
        this.world = w;
        GreenfootImage image = new GreenfootImage("prev.png");
        image.scale(50, 50);
        setImage(image);
    }
    
    /**
     * Act - do whatever the PrevButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            world.prevFruit();
        }
    }
}
