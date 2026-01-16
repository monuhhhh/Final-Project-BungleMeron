import greenfoot.*;

/**
 * AnnoyingFruit is the player-controlled fruit.
 * The player selects which annoying fruit to play as.
 * 
 * @author Monika
 * @version 24 Nov, 2025
 */
public abstract class AnnoyingFruit extends Fruits
{
    //movement variables
    protected int yVelocity = 0;
    protected boolean onGround = false;
    protected int jumpStrength = 15;
    
    //constructor for AnnoyingFruit (player)
    public AnnoyingFruit(int direction) {
        super(direction);
    }
    
    public void act() {
        super.act();   // ← THIS IS NON-NEGOTIABLE
        if (previewMode || frozen) return;
    
        handleMovement();
        applyGravity();
    }
    
    //handles left/right movement and jumping
    protected void handleMovement() {
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - 4, getY());
            direction = -1;
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + 4, getY());
            direction = 1;
        }
        if (Greenfoot.isKeyDown("space") && onGround) {
            yVelocity = -jumpStrength;
            onGround = false;
        }
    }
    
    //applies gravity and landing logic
    protected void applyGravity() {
        yVelocity += 1;
        setLocation(getX(), getY() + yVelocity);

        if (isTouching(Ground.class)) {
            onGround = true;
            yVelocity = 0;
        }
    }

    //edge detection 
    protected void checkEdge() {
        if (isAtEdge()){
            direction *= -1;
        }
    }
}
