import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VictoryWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VictoryWorld extends World
{

    /**
     * Constructor for objects of class VictoryWorld.
     * 
     */
    public VictoryWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);  
        setBackground(new GreenfootImage("victory.png"));
        
    }
    
    public void act(){
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
