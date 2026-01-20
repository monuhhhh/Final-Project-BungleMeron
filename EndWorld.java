import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.UserInfo;

/**
 * Write a description of class EndWorld here.
 * 
 * @author Carmen Cheung
 * @version Jan 16, 2026
 */
public class EndWorld extends World
{

    /**
     * Constructor for objects of class EndWorld.
     * 
     */
    public EndWorld(int hp, int level)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        setBackground(new GreenfootImage("end.png")); 
        
        showText("Final HP: " + hp, getWidth() / 2, 150);
        showText("Level Reached: " + level, getWidth() / 2, 200);
        showText("Press r to Restart", getWidth() / 2, 250);
    }

    public void act() {
        if (Greenfoot.isKeyDown("r")) {
            UserInfo user = UserInfo.getMyInfo();
            
            if (user != null) {
                user.setInt(0, 0); // reset saved HP
                user.store();
            }
            
            Greenfoot.setWorld(new TitleWorld());
        }
    }
}
