import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Color;

/**
 * Write a description of class Boss here.
 * 
 * @author Monika
 * @version (a version number or a date)
 */
public abstract class Boss
{
    private int pulseCounter = 0;
    //protected Fruits currentTarget; //single target
    private boolean fadingIn = true; // fade in when spawned
    
    
    protected int slamRadius = 80;  // small
    protected int windupFrames = 40; // medium
    protected int knockback = 5; // light
    protected int slamCooldown = 0; // frames until next attack
    protected boolean isSlamming = false; // true during slam
    protected int slamFrameCounter = 0; // tracks windup

    public Boss(int direction, int teamID) {
        //super(direction, teamID);
        //awake = false; // bosses start passive
    }

    public void act() {
        //if (!awake) {
            pulseRed(); // pulsing while waiting
        } else {
            fadeIn(); // gradually appear
            chargeAndFight(); // attack logic
        }
    }

    //methods for boss activation
    public void wakeUp() { awake = true; }
    public void sleep() { awake = false; }

    protected void pulseRed() {
        pulseCounter++;
        GreenfootImage img = getImage();
        int alpha = (int)(Math.sin(pulseCounter / 10.0) * 100 + 155);
        img.setColor(Color.RED);
        img.setTransparency(alpha);
        setImage(img);
    }

    // gradually make boss visible
    private void fadeIn() {
        if (!fadingIn) return;

        GreenfootImage img = getImage();
        int alpha = img.getTransparency();
        if (alpha < 255) {
            alpha += 5; // speed of fade-in
            if (alpha > 255) alpha = 255;
            img.setTransparency(alpha);
            setImage(img);
        } else {
            fadingIn = false; // fully visible now
        }
    }

    @Override protected void moveFruit() {}  // bosses do not move
    @Override protected void checkEdge() {}  // bosses ignore edges
}
