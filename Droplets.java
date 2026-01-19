import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// credits to Falling Objects - Greenfoot.org
// Monika Kouyoumdjian
// Jan 16
public class Droplets extends SuperSmoothMover
{
    private int fallTime; // delay in frames before the object starts falling
    private int actCounter = 0; // counter to track number of frames passed
    
    private static final String IMAGE_PATH = "droplet.png";
     
    public Droplets(int fallTime)
    {
        this.fallTime = fallTime;

        GreenfootImage img = new GreenfootImage(IMAGE_PATH);
        img.scale(img.getWidth() / 6, img.getHeight() / 6); // make small
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


