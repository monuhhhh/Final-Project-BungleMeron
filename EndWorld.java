import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.UserInfo;

/**
 * EndWorld displays the final game results after the player lose the games. 
 * It shows the player's final HP, the level reached, and allows the player to restart the game.
 * 
 * @author Carmen Cheung
 * @version Jan 16, 2026
 */
public class EndWorld extends World
{
    /**
     * Constructor for objects of class EndWorld.
     * 
     * @param hp the player's final health points
     * @param level the highest level the player reached
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
    
    /**
     * If the player presses the 'r' key, saved data is reset and the game restart.
     */
    public void act() {
        if (Greenfoot.isKeyDown("r")) {
            UserInfo user = UserInfo.getMyInfo();
            
            if (user != null) {
                user.setInt(0, 0); // reset saved HP
                user.store();
            }
            
            //return to the title screen
            Greenfoot.setWorld(new TitleWorld());
        }
    }
}
