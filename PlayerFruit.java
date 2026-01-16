import greenfoot.*;

/**
 * AnnoyingFruit is the player-controlled fruit.
 * The player selects which annoying fruit to play as.
 * 
 * @author Monika and Carmen 
 * @version 24 Nov, 2025
 */
public abstract class PlayerFruit extends Fruits
{
    //movement variables
    public int yVelocity = 0;
    protected boolean onGround = false;
    protected int jumpStrength = 8;
    public boolean onLadder = false; 
    
    //constructor for PlayerFruit (player)
    public PlayerFruit(
        int direction,
        String imagePath,
        int hp)
    {
        super(direction);
        setImage(imagePath);
        initStats(hp); // reactionTime not used for player
    }
    
    public void act() {
        if (!(getWorld() instanceof MyWorld)) {
            return; // Don't do anything in CustomizeWorld
        }
        if (frozen) return;
        handleMovement();
        if (!onLadder) {
            applyGravity();
        }
        checkGoal();
    } 
    
    //handles left/right movement and jumping
    protected void handleMovement() {
        MyWorld world = (MyWorld) getWorld();
        
        //Horizontal movement
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - 4, getY());
            direction = -1;
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + 4, getY());
            direction = 1;
        }
        
        //Check if on ladder
        onLadder = world.isLadderAt(getX(), getY());
        
        // Ladder climbing
        if (onLadder) {
            if (Greenfoot.isKeyDown("up")) {
                setLocation(getX(), getY() - 4);
                yVelocity = 0;
            }
            if (Greenfoot.isKeyDown("down")) {
                setLocation(getX(), getY() + 4);
                yVelocity = 0;
            }
        }
        
        if (Greenfoot.isKeyDown("space") && onGround && !onLadder) {
            yVelocity = -jumpStrength;
            onGround = false;
        }
    }
    
    
    //applies gravity and landing logic
    protected void applyGravity() {
        MyWorld world = (MyWorld) getWorld();
        int cellSize = world.getCellSize();
    
        // Apply gravity
        yVelocity += 1;
    
        // Cap fall speed
        if (yVelocity > 15) {
            yVelocity = 15;
        }
    
        int nextY = getY() + yVelocity;
        
        // Calculate foot position
        int footY = nextY + getImage().getHeight()/2;
    
        // Check landing (only when falling)
        if (yVelocity > 0 &&
            world.getGridValue(getX(), footY) == 1) {
    
            // Snap player to top of platform
            int platformRow = footY / cellSize;
            int snapY = platformRow * cellSize - cellSize / 2;
    
            setLocation(getX(), snapY);
            yVelocity = 0;
            onGround = true;
        } else {
            // In air
            setLocation(getX(), nextY);
            onGround = false;
        }
    }

    
    protected void checkGoal() {
        MyWorld world = (MyWorld) getWorld();
            
        int gridValue = world.getGridValue(getX(), getY());

        if (gridValue == 5) {
            Greenfoot.setWorld(new TitleWorld()); // or NextWorld()
        }
    }
    
    //edge detection 
    protected void checkEdge() {
        if (isAtEdge()){
            direction *= -1;
        }
    }
}
