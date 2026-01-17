import greenfoot.*;

//adding a comment so it will accept my commit 
/**
 * AnnoyingFruit is the player-controlled fruit.
 * The player selects which annoying fruit to play as.
 * 
 * @author  Carmen and monika
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
    
    protected int hp;
    protected int maxHp = 100;
    
    //constructor for PlayerFruit (player)
    public PlayerFruit(String imagePath, int initialHp, int maxHP) {
        super(1); // default direction, not important anymore
        setImage(imagePath);
    
        this.maxHp = maxHP;
        this.hp = initialHp;
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
        
        checkMiniFruitCollision();
        checkCollision();
        checkGoal(); 
        
        //gainPower();
    } 
    
    /**
    public void gainPower(int currentLevel) {//monika
        if (MyWorld.currentLevel = 5){
            
        }
    }
    */
    
    
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
                int playerBottom = getY() + getImage().getHeight() / 2;
                int dropletTop  = droplet.getY() - droplet.getImage().getHeight() / 2;
        
                if (playerBottom <= dropletTop + 5) {
                    // Player jumped on top
                    heal(1); 
                } else {
                    // Side or bottom hit
                    damageMe(1);
                }
        
                getWorld().removeObject(droplet);
            }
        }
        
        //minifruit collision in handles all in the minifruit 
    }
    
    private void checkMiniFruitCollision(){//monika
        MiniFruit hit = null;
    
        for (MiniFruit mf : getWorld().getObjects(MiniFruit.class))
        {
            double dx = mf.getX() - getX();
            double dy = mf.getY() - getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
    
            if (distance < 30)  // collision radius (tweakable)
            {
                hit = mf;
                break;
            }
        }
    
        if (hit != null)
        {
            damageMe(10);
            getWorld().removeObject(hit);
        }
    }

    
    
    /**
    public void damageMe(int damage) {//monika
        //if (damageCooldown > 0) return; // ignore if cooldown active
    
        hp = Math.max(0, hp - damage);
    
        MyWorld world = (MyWorld) getWorld();
        if (world != null) {
            world.updateLifeCounter(hp);
            if (hp == 0) {
                die();
            }
        }
    
        ///damageCooldown = DAMAGE_COOLDOWN_TIME; // start cooldown
    }*/
    
    public void damageMe(int damage) {
        // Apply damage
        hp = Math.max(0, hp - damage);
    
        // Update UI
        MyWorld world = (MyWorld)getWorld();
        if (world != null) {
            world.updateLifeCounter(hp);
            if (hp == 0) {
                // Player died
                die();
            }
        }
    }

    /**
    public void heal(int amount) {//monika
        hp = Math.min(hp + amount, maxHp); // caps at maxHp
        MyWorld world = (MyWorld)getWorld(); 
        if (world != null) world.updateLifeCounter(hp); // update UI
    } 
    */
   
    // Optional heal method
    public void heal(int amount) {
        hp = Math.min(hp + amount, maxHp);
        MyWorld world = (MyWorld)getWorld();
        if (world != null) {
            world.updateLifeCounter(hp);
        }
    }
    
    
    //edge detection 
    protected void checkEdge() {
        if (isAtEdge()){
            direction *= -1;
        }
    }
}
