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
    
    
    protected int damageCooldown = 0;
    protected final int DAMAGE_COOLDOWN_TIME = 30;
    
    //constructor for PlayerFruit (player)
    public PlayerFruit(
        int direction,
        String imagePath,
        int hp, int maxHP)
    {
        super(direction);
        setImage(imagePath);
        initStats(hp, maxHP); // reactionTime not used for player
    }
    
    public void takeDamage(double dmg) {//monika
        if (damageCooldown > 0) return;
      
        hp -= dmg;
        damageCooldown = DAMAGE_COOLDOWN_TIME;
    
        if (hp <= 0) {
            hp = 0;
            die();
        }
    }
    
    @Override
    protected void die() {
        MyWorld world = (MyWorld) getWorld();
        if (world != null) {
            world.lose();
        }
    }
    
    public void act() {
        if (!(getWorld() instanceof MyWorld)) {
            return; // Don't do anything in CustomizeWorld
        }
        if (frozen) return;
        if (damageCooldown > 0) {
            damageCooldown--;
        }
        handleMovement();
        if (!onLadder) {
            applyGravity();
        }
        checkCollision();
        checkGoal();
    } 
    
    //handles left/right movement and jumping
    protected void handleMovement() {//monika
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
    protected void applyGravity() {//monika
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
            world = (MyWorld) getWorld();
            world.nextLevel();
        }
    }
    
    private void checkCollision() {//monika - used code from gr 11 project as base 
        if (isTouching(Droplets.class)) {
            Droplets droplet = (Droplets) getOneIntersectingObject(Droplets.class);
 
            if (droplet != null) {
                // Get the positions of the player and Perry
                int playerBottom = getY() + getImage().getHeight() / 2;
                int dropletTop  = droplet.getY() - droplet.getImage().getHeight() / 2;
    
                if (playerBottom <= dropletTop  + 5) {
                    // Player jumped on top of Perry
                    if (hp < maxHP) {//make sure doesnt surpasse max hp
                        heal(1); 
                    } // Add a health
                    getWorld().removeObject(droplet); // Remove Perry after being jumped on
                } else {
                    // Player touched droplet from the side or bottom
                    takeDamage(1);
                }
            }
        }
        
        
        if (isTouching(Boss.class)) {//someone else wprking on this
            // Decrease the player's life
            damageMe(1); 
        }
    }
    
    
    
    public void damageMe(int damage) {//monika
        takeDamage(damage); 
    
        MyWorld world = (MyWorld) getWorld();
        if (world != null) {
            world.updateLifeCounter((int) hp);
        }
    }
    
    public void heal(int amount) {//monika
        hp += amount;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }
    
    
    
    //edge detection 
    protected void checkEdge() {
        if (isAtEdge()){
            direction *= -1;
        }
    }
}
