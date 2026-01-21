import greenfoot.*;

//adding a comment so it will accept my commit 
/**
 * AnnoyingFruit is the player-controlled fruit.
 * The player selects which annoying fruit to play as.
 * 
 * @author  Carmen and Monika
 * @version 24 Nov, 2025
 */  
public abstract class PlayerFruit extends Fruits
{
    //movement variables
    public int yVelocity = 0;
    protected boolean onGround = false;
    protected int jumpStrength = 10;
    public boolean onLadder = false; 
    
    
    protected int damageCooldown = 0;
    protected final int DAMAGE_COOLDOWN_TIME = 30;
    
    protected int hp;
    protected int maxHp = 100;
    
    //constructor for PlayerFruit (player)
    public PlayerFruit(String imagePath, int initialHp, int maxHP) {//monika
        super(1); // default direction, not important anymore
        setImage(imagePath);
     
        this.maxHp = maxHP;
        this.hp = initialHp;
    }
    
    
    
    @Override
    protected void die() {//monika
        MyWorld world = (MyWorld) getWorld();
        if (world != null) {
            world.lose();
        }
    }
    
    public void act() {// monika
        if (!(getWorld() instanceof MyWorld)) return;
        if (frozen) return;
    
        if (damageCooldown > 0) {
            damageCooldown--;
        }
    
        handleMovement();
    
        // droplet collision must be checked before gravity snapping
        checkDropletCollision();
    
        // gravity + landing logic
        if (!onLadder) {
            applyGravity();
        }
    
        // other collisions
        checkMiniFruitCollision();
    
        // goal tile check
        checkGoal();
    }

    
    public void changeHP(int amount) {//monika
        hp += amount;
        if (hp < 0) hp = 0; 
    
        MyWorld world = (MyWorld)getWorld();
        if (world != null) {
            world.updateLifeCounter(hp);
        }
    
        if (hp <= 0) {
            world.lose();
        }
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
        if (yVelocity > 15) yVelocity = 15;
    
        int nextY = getY() + yVelocity;
        int footY = nextY + getImage().getHeight() / 2;
    
        // Check landing (only when falling)
        if (yVelocity > 0 && world.getGridValue(getX(), footY) == 1) {
            int platformRow = footY / cellSize;
            int snapY = platformRow * cellSize - cellSize / 2;
            setLocation(getX(), snapY);
            yVelocity = 0;
            onGround = true;
        } else {
            setLocation(getX(), nextY);
            onGround = false;
        }
    }

    
    protected void checkGoal() {
        MyWorld world = (MyWorld) getWorld();
            
        int gridValue = world.getGridValue(getX(), getY());

        if (gridValue == 5) {
            //######world = (MyWorld) getWorld();
            world.nextLevel();
        }
    }
    
    private void checkDropletCollision() {// monika
        for (Droplets d : getWorld().getObjects(Droplets.class)) {
    
            // droplet hitbox dimensions
            int boxW = 24;
            int boxH = 34;
    
            // hitbox edges
            int dropletLeft   = d.getX() - boxW / 2;
            int dropletRight  = d.getX() + boxW / 2;
            int dropletTop    = d.getY() - boxH / 2;
            int dropletBottom = d.getY() + boxH / 2;
    
            // player edges (still image-based, that's fine)
            int playerLeft   = getX() - getImage().getWidth() / 2;
            int playerRight  = getX() + getImage().getWidth() / 2;
            int playerTop    = getY() - getImage().getHeight() / 2;
            int playerBottom = getY() + getImage().getHeight() / 2;
    
            // axis-aligned bounding box overlap
            boolean overlap =
                    playerRight  > dropletLeft &&
                    playerLeft   < dropletRight &&
                    playerBottom > dropletTop &&
                    playerTop    < dropletBottom;
    
            if (!overlap) continue;
    
            // ±10px stomp window against flat top
            boolean hitFromTop =
                    Math.abs(playerBottom - dropletTop) <= 10 &&
                    getY() < d.getY();
    
            if (hitFromTop) {
                changeHP(10);
                yVelocity = -8;
            } else {
                changeHP(-15);
            }
    
            getWorld().removeObject(d);
            return;
        }
    }



    
   
   
    private void checkMiniFruitCollision() {// monika
        for (MiniFruit mf : getWorld().getObjects(MiniFruit.class)) {
    
            int dx = Math.abs(mf.getX() - getX());
            int dy = Math.abs(mf.getY() - getY());
    
            // horizontal overlap tolerance
            if (dx > getImage().getWidth() / 2) continue;
    
            // vertical overlap tolerance
            if (dy > getImage().getHeight() / 2) continue;
    
            // any contact causes damage
            damageMe(10);
            getWorld().removeObject(mf);
            return;
        }
    }


    
    
        
    public void damageMe(int damage) {//monika
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

    
   
    // Optional heal method
    public void heal(int amount) {//monika
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
