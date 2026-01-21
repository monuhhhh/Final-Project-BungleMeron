import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * VictoryWorld displayed when the player completes all level of the game.
 * It allow player to press 'r' to restart the game. 
 * 
 * @author Carmen Cheung
 * @version Jan 16, 2026
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
    
    /**
     * if the player presses the 'r' key, saved player data is rest and the game restart. 
     */
    public void act(){
        if (Greenfoot.isKeyDown("r")) {
            UserInfo user = UserInfo.getMyInfo();
            
            if (user != null) {
                user.setInt(0, 0); // reset saved HP
                user.store();
            }
            
            //navagate the player to the title screen
            Greenfoot.setWorld(new TitleWorld());
        }
    }
}
