import greenfoot.*;  

/**
 * Selected fruit from player is the main player. This class handles collision detection, hp, 
 * user input, as well as the players movement. 
 * 
 * @author  Monika Kouyoumdjian
 * @version Jan 14 2026
 */  
public abstract class PlayerFruit extends Fruits
{
    //movement variables
    public int yVelocity = 0;
    protected boolean onGround = false;
    public boolean onLadder = false; 
    
    protected int jumpStrength = 10;
    
    //HP variables
    protected int hp;
    protected int maxHp;

    protected String name; 

    //damage cooldown
    protected int damageCooldown = 0;
    protected final int DAMAGE_COOLDOWN_TIME = 30;

    //constructor for PlayerFruit (player)
    public PlayerFruit(String imagePath, String name, int initialHp, int maxHP) {
        super(1); // default direction, not important anymore
        setImage(imagePath);
        
        this.name = name; 
        this.maxHp = maxHP;
        this.hp = initialHp;
    }
    
    public String getName() {
        return name; 
    }
    
    protected void die() {
        MyWorld world = (MyWorld) getWorld();
        if (world != null) { 
            world.lose();
        }
    }
    
    public void act() {
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

    //updates hp and UI
    public void changeHP(int amount) {
        hp += amount;
        if (hp < 0) hp = 0; 
        if (hp > maxHp) hp = maxHp;

        MyWorld world = (MyWorld)getWorld();
        if (world != null) {
            world.updateLifeCounter(hp);
        }

        if (hp <= 0) {
            die();
        }
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
        
        // Jumping
        if (Greenfoot.isKeyDown("space") && onGround && !onLadder) {
            yVelocity = -jumpStrength;
            onGround = false;
        }
    }

    //applies gravity and landing logic
    protected void applyGravity() {
        MyWorld world = (MyWorld) getWorld();
        int cellSize = world.getCellSize();

        yVelocity += 1; // gravity
        if (yVelocity > 15) yVelocity = 15; // cap fall speed

        int nextY = getY() + yVelocity;
        int footY = nextY + getImage().getHeight() / 2;

        // Check landing
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
            world.nextLevel();
        }
    }

    private void checkDropletCollision() {
        for (Droplets d : getWorld().getObjects(Droplets.class)) {
            int boxW = 24;
            int boxH = 34;

            int dropletLeft   = d.getX() - boxW / 2;
            int dropletRight  = d.getX() + boxW / 2;
            int dropletTop    = d.getY() - boxH / 2;
            int dropletBottom = d.getY() + boxH / 2;

            int playerLeft   = getX() - getImage().getWidth() / 2;
            int playerRight  = getX() + getImage().getWidth() / 2;
            int playerTop    = getY() - getImage().getHeight() / 2;
            int playerBottom = getY() + getImage().getHeight() / 2;

            boolean overlap =
                    playerRight  > dropletLeft &&
                    playerLeft   < dropletRight &&
                    playerBottom > dropletTop &&
                    playerTop    < dropletBottom;

            if (!overlap) continue;

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

    private void checkMiniFruitCollision() {
        for (MiniFruit mf : getWorld().getObjects(MiniFruit.class)) {
            int dx = Math.abs(mf.getX() - getX());
            int dy = Math.abs(mf.getY() - getY());

            if (dx > getImage().getWidth() / 2) continue;
            if (dy > getImage().getHeight() / 2) continue;

            changeHP(-10); // any contact causes damage
            getWorld().removeObject(mf);
            return;
        }
    }

    // Optional heal method
    public void heal(int amount) {
        changeHP(amount);
    }

    //edge detection 
    protected void checkEdge() {
        if (isAtEdge()){
            direction *= -1;
        }
    }
}

