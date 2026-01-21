import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Fruits is a superclass for all fruit characters in the game.
 * It defines stats and core behaviours shared by all fruits, as well as 
 * some additional methods and variables that can be easily implementted if needed. 
 * 
 * @author Monika Kouyoumdjian
 * @version 24 Nov, 2025 and jan 16 2026
 */
public abstract class Fruits extends SuperSmoothMover
{
    protected double speed, maxSpeed, strength, hp, maxHP;
    protected boolean awake = true;
    protected boolean knockedDown = false;
    
    protected boolean previewMode = false; 

    protected int direction;  // 1 for right, -1 for left
    
    protected int spawnRate = 120;
    
    private int knockdownTimer = 30;
    
    protected int attackCooldown = 0;
    
    public boolean frozen = false;
        //<<<<<<< HEAD
    
    //set the spawn rate
        //=======
        //>>>>>>> Monika-Levels
    protected void setSpawnRate(int rate) {
        this.spawnRate = rate;
    }
    
    // get the current spawn rate
    public int getSpawnRate() {
        return spawnRate;
    }
    
    //constructor for fruits 
    public Fruits(int direction) {
        this.direction = direction;
        
        // choose a random speed
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
    }
    
    //set whether this fruit is in preview mode where the fruit doesn't execute any game behaviours
    public void setPreviewMode(boolean b) {
        previewMode = b;
    }

    //subclasses can then customize these variables for the unique identity of each fruit 
    protected void initStats(int hp, int maxHP){
        this.hp = hp;
        this.maxHP = maxHP; 
    }
    
    /**
     * Act - do whatever the Fruit wants to do.
     * This method is called whenever the 'Act' or 'Run' button is pressed.
     */
    public void act()
    {
        if (frozen) return;
        
        if (previewMode){
            return;
        }
        
        if (!knockedDown) { // move only if awake
            moveFruit();
            animateRocking();
            checkEdge();
        } else {
            handleKnockDown();
        }
        
        if(!awake){
            return;
        }
    }
    
    protected void animateRocking() {
        // default idle rocking
        setRotation(15 * direction);
    }
    
    //Calculates the distance to another actor using the Pythagorean theorem
    protected double getDistanceTo(Actor other) {
        return Math.hypot(getX() - other.getX(), getY() - other.getY());
    }
    
    //Applies damage to this fruit and triggers knockdown if health reaches zero
    public void takeDamage(double dmg) {
        hp -= dmg;
        if (hp <= 0) {
            hp = 0;
            die();
        }
    }
    
    protected void die() {
        removeFromWorld();
    }
    
    //Triggers the knockdown state
    public void isKnockedDown(){
        if (!knockedDown) {
            knockedDown = true;
            speed = 0;
            setRotation(90 * direction);
            
            GreenfootImage img = getImage();
            img.setTransparency(150); // fade or splat effect
        }
    }
    
    //Manages the knockdown state, apply fading effect
    protected void handleKnockDown() {
        if(knockdownTimer > 0){
            knockdownTimer--;
            return; 
        }
        GreenfootImage img = getImage();
        int transparency = img.getTransparency();
        if (transparency > 0) {
            img.setTransparency(transparency - 5); // fade gradually
        } else {
            removeFromWorld();
        }
    }
    
    //remove fruit from the world
    protected void removeFromWorld() {
        World w = getWorld();
        if (w != null) w.removeObject(this);
    }
    
    //Checks if this fruit is colliding with another actor
    protected boolean isColliding(Actor other) {
        if (other == null){
           return false; 
        }
        return this.intersects(other);
    }
    
    //base movement (can be overridden by subclasses)
    protected void moveFruit() {
        if (getWorld() == null) return;
        setLocation(getX() + (int)(direction * speed), getY());
    }

    //abstract promised 
    protected abstract void checkEdge();
}
