import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FinalButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FinalButton extends Button
{
    private boolean activated = false;
    
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
                    MyWorld world = (MyWorld) getWorld();
                    world.makeBossFall();
                    
                    getImage().setTransparency(150);
                }
            }
        }
    }
}
