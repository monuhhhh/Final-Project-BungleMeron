import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// credits to Falling Objects - Greenfoot.org
/**
 * This obstacle is inspiered by an obstacle seen in super mario, where depending on where a player makes contact, it can either
 * help them out, or make them lose hp. If a player jumps dirrectly ontop of the droplet, they will gain 10HP, but if they make 
 * contact anywhere else, they will lose 15 hp
 * 
 * 
 * 
 * @author (monika) 
 * @version (Jan 16 2025)
 */
public class Droplets extends SuperSmoothMover
{
    private int fallTime; // delay in frames before the object starts falling
    private int actCounter = 0; // counter to track number of frames passed
    
    private static final String IMAGE_PATH = "droplets.png";
     
    public Droplets(int fallTime)
    {
        this.fallTime = fallTime;

        GreenfootImage img = new GreenfootImage(IMAGE_PATH);
        img.scale(img.getWidth() / 8, img.getHeight() / 8); // make small
        
        
        setImage(img);
    }
     
    public void act()
    {
        if (getWorld() == null) return;
        
        

        if(actCounter > fallTime) {
            setLocation(getX(), getY() + 1); // move droplet down 1 pixel
        }
        actCounter++;

        // remove droplet if it reaches bottom of world
        if (getY() > getWorld().getHeight() - 30) {
            getWorld().removeObject(this);
        }
    }
}


