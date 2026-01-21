import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * FinalButton activates the final event when the player gets close enough. 
 * 
 * @author Carmen Cheung 
 * @version Jan 16, 2026
 */
public class FinalButton extends Button
{
    //track whether the button has already been activated
    private boolean activated = false;
    
    /**
     * constructor that construct the FinalButton
     */
    public FinalButton(){
        GreenfootImage image = new GreenfootImage("finalbutton.png");
        image.scale(40, 40);
        setImage(image);
    }
    
    /**
     * Act - do whatever the FinalButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkActivation(); 
    }
    
    /**
     * Checks if the player is within activation range.
     * When activated, it triggers the boss event. 
     */
    private void checkActivation(){
        if (!activated) {
            java.util.List<PlayerFruit> players = getWorld().getObjects(PlayerFruit.class);
            if (!players.isEmpty()) {
                PlayerFruit player = players.get(0);
                
                // Check if player is within 40 pixels (one grid cell)
                int distance = (int)Math.sqrt(
                    Math.pow(player.getX() - getX(), 2) + 
                    Math.pow(player.getY() - getY(), 2)
                );
                                
                if (distance < 40) {
                    activated = true;
                    //Trigger final boss behavior
                    MyWorld world = (MyWorld) getWorld();
                    world.makeBossFall();
                    
                    getImage().setTransparency(150);
                }
            }
        }
    }
}
