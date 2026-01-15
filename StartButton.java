import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author Carmen Cheung 
 * @version Jan 13, 2025
 */
public class StartButton extends Button
{
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public StartButton() {
        GreenfootImage image = new GreenfootImage("start.png");
        image.scale(300, 250);
        setImage(image);
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new CustomizeWorld()); // go to CustomizeWorldAnnoyingFruit when clicked
        }
    }
}
