import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * AfterButton is a button that allow the player to cycle to the next fruit option in CustomizeWorld. 
 * 
 * @author Carmen Cheung 
 * @version Jan 13, 2025
 */
public class AfterButton extends Button
{
    /**
     * Act - do whatever the AfterButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private CustomizeWorld world; 
    public AfterButton(CustomizeWorld w){
        this.world = w; 
        GreenfootImage image = new GreenfootImage("after.png");
        image.scale(50, 50);
        setImage(image);
    }
    /**
     * Checks for mouse clicks on this button.
     * When clicked, it tells the CustomizeWorld
     * to show the next fruit.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            world.nextFruit();
        }
    }
}
