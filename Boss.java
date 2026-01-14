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
        super(direction, teamID);
        awake = false; // bosses start passive
    }

    public void act() {
        if (!awake) {
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

public class Player extends SuperSmoothMover
{
    private GreenfootImage image;
    private int hp, maxHP; //plaers current max healt points 
    private double speed; //normal speed (running around on solid ground)
    private int acceleration; //rate at which falling accelerates at 
    private int jumpStrength; //will translate to the hight of the jump
    private double vSpeed = 2; /*this variable will be initialized with 
                            the speed value and then altered depending 
                            on the action (falling or jumping up)
                            */
                               
    
    
    //connect to the projectile classes, the speed of the projectiles 
    public int projectileDSpeed = 20; //Shooting Direction - Greenfoot Platformer

    public int projectileFSpeed = 20;
    public double nearestDistance;
    public double distance;
    //for f key shooting * credits to danpost on greenfoot How do I make shot delays? for the delays in the shootF
    
    
    private int shotDelay; //the delay between shots (in frames, so 30 frames)
    private int currentShotDelay; //current countdown of shotDelay
    
    //shooting sounds
    private GreenfootSound[] shootSounds;
    private int shootSoundIndex;
    
    public Player () {
        enableStaticRotation(); //from the supersmoothmover, prevents the player image from rotating (not necessary because player is not turnung
        
        speed = 2.5;//speed is two, horizontal movement
        maxHP = 5; //max hp assigned
        hp = maxHP; //player starts with full health
        jumpStrength = 10; //player jump strength
        acceleration = 1; //scceleration of fall
        
        shotDelay = 10; //delay between the shots is 10 frames
        currentShotDelay = 0; //delay countdown 
        
        shootSounds = new GreenfootSound[10];
        for (int i = 0; i < shootSounds.length; i++){ //mr.cohen 40 min shooter
            shootSounds[i] = new GreenfootSound ("shoot.wav");
            shootSounds[i].setVolume(70);
        }
        shootSoundIndex = 0;
        
        drawPlayer();
    }

    
    public void act()
    {
        fall(); //apply gravity
        checkKeys(); //players input porcessed 
        checkFall(); //falling
        checkCollision(); //collisions with otehr objects 
    }

    private void drawPlayer() { //drawing the player 
        image = new GreenfootImage (44, 44);
        image.setColor(Color.GREEN);
        image.fill();
        setImage(image);
    }
    private void checkKeys (){
        // instantaneous keyboard input for movement:
        if (Greenfoot.isKeyDown("left")) //when the left key is pressed, player will move left 
        {
            setLocation (getX() - speed, getY());
            projectileDSpeed = -20; //solved to have it go left when going left by using logic from going right 
        }
        if (Greenfoot.isKeyDown("right")) //when the right key is pressed 
        {
            setLocation (getX() + speed, getY());
            projectileDSpeed = 20; //Shooting Direction - Greenfoot Platformer
        }
        if (Greenfoot.isKeyDown("up"))
        {
            if (onGround())
            {
                jump();
            }
        }
        if (Greenfoot.isKeyDown("f"))//fires homing shots, can be held down 
        {
            if (onGround())
            {
                shootF();
            }
        }

        /**not using Greenfoot.isKeyDown to detect in d is pressed 
        because the player should need to shoot for every bullet shot, 
        not just hold down and shoot a bunch
        */
        String key = Greenfoot.getKey();
        if (key != null){ // something was pressed..
            if (key.equals("d") || key.equals("D")){ // d key was pressed (dirrect shoorting where you haave to aim)..
                shootD();
            }
            
        }
 
    }    
    private void checkFall()
    {
        if (onGround())
        {
            vSpeed = 0;
        }else{
            fall(); 
        }
    }
    public boolean onGround() //
    {
        Object under = getOneObjectAtOffset(0, getImage().getHeight()/2 , Ground.class);
        return under != null; //returns true is theres a ground object belwo
    }
    private void vSpeed(int speed) //this speed is taken from the speed variable, since the speed will be altered, create a new speed to be altered called vspeed so the initial speed always stays the same 
    {
        vSpeed= speed;//vPeed means vertical speed
    }
    public void fall()
    {
        setLocation (getX(), getY() + vSpeed);
        vSpeed += acceleration; //vspeed starts at two, two is added every time (2, 4, 6, 8....)
    }
    private void jump()
    {
        vSpeed(-jumpStrength);
        fall();
    }
    
    
    
    private void playShootSound(){ //mr.cohen 40 min shooter
        shootSounds[shootSoundIndex].play();
        shootSoundIndex++;
        if (shootSoundIndex == shootSounds.length){
            shootSoundIndex = 0;
        }
    }
    
    
    
    private void shootD () {
        // add a new projectile              projectileDSpeed from video on youtube Shooting Direction - Greenfoot Platformer 
        getWorld().addObject(new ProjectileD(projectileDSpeed), getX() + 20, getY()+getImage().getHeight()/2);
        playShootSound();
    }
    private void shootF () {
        
        if(currentShotDelay > 0){
            currentShotDelay--; //decrease shot delay countdwon 
        }else if (currentShotDelay == 0 && Greenfoot.isKeyDown("f")){
            //initialize variable nearestDistance to the largest value, so when we use it to compare later to see which ennemy is in shortest range 
            nearestDistance = Double.MAX_VALUE; 
            Enemy nearestEnemy = getNearestEnemy(); //find the closest enemy
           
            if(nearestEnemy != null)
            {
                //turn towards the nearest enemys position
                //inspiration form the enemy class, enemies following the player, same happens to the bullets 
        
                //Add new projectile aimed towards the enemys position 
                getWorld().addObject(new ProjectileF(projectileFSpeed, nearestEnemy), getX() + 20, getY() + getImage().getHeight()/2);
                
                //resset shot delay to prevent fast spamming shots 
                currentShotDelay = shotDelay;
                playShootSound();
            }
        }
        
        
        //finding nearest enemy credits to: Tricky situation... Get the nearest object greenfoot.org    
    }
    public double getDistance(Enemy closestEnemy)
    {
        return Math.hypot(closestEnemy.getX() - getX(), closestEnemy.getY() - getY());
    }
    
    public Enemy getNearestEnemy()  //finding nearest enemy credits to: Tricky situation... Get the nearest object greenfoot.org

    {
        java.util.List<Enemy> enemies = getWorld().getObjects(Enemy.class);//find all ennemies within the range (800)

        if (enemies.size() == 0){
            return null;//no enemies have been found
        }
        
        
        Enemy nearestEnemy = enemies.get(0);
        //initialize variable nearestDistance to the largest value, so when we use it to compare later to see which ennemy is in shortest range 
        nearestDistance = Double.MAX_VALUE;
        
        
        for (int i = 0; i < enemies.size(); i++) {
            distance = getDistance(enemies.get(i)); //calcs distance to current enemy 
            if (distance < nearestDistance) {
                nearestEnemy = enemies.get(i);
                nearestDistance = distance;
            }
        }
        return nearestEnemy;
    }
    
    
    
    
    
    
    private void handlePlayer(Class targetClass, int pointsToAdd, int lifeChange){
        if(getOneIntersectingObject(targetClass) != null){
            GameWorld world = (GameWorld) getWorld();
            world.addPointsScore(pointsToAdd); //add points to score
        }
        
        if(getOneIntersectingObject(targetClass) != null){
            GameWorld world = (GameWorld) getWorld();
            hp = Math.max(0, hp + lifeChange); // Add or remove lives
            world.updateLifeCounter(hp); // Update life counter display
    
            if (hp == 0) {
                world.lose(); // Trigger lose if lives reach 0
            }
        }

    }
    
    public int getLives () {
        return hp;
    }
    private void checkCollision() {
        if (isTouching(Perry.class)) {
            Perry perry = (Perry) getOneIntersectingObject(Perry.class);

            if (perry != null) {
                // Get the positions of the player and Perry
                int playerBottom = getY() + getImage().getHeight() / 2;
                int perryTop = perry.getY() - perry.getImage().getHeight() / 2;
    
                if (playerBottom <= perryTop + 5) {
                    // Player jumped on top of Perry
                    handlePlayer(Perry.class, 0, 1); // Add a life
                    getWorld().removeObject(perry); // Remove Perry after being jumped on
                } else {
                    // Player touched Perry from the side or bottom
                    handlePlayer(Perry.class, 0, -1); // Remove a life
                }
            }
        }
        
        
        if (isTouching(Enemy.class)) {
            // Decrease the player's life
            damageMe(1);
        }
    }
    public void damageMe(int damage) {
        hp = Math.max(0, hp - damage);
        // If lives reach zero, trigger game over
        if (hp == 0) {
            GameWorld world = (GameWorld) getWorld();
            world.lose();  // Trigger lose state
        }
        // Update the life counter in GameWorld
        GameWorld world = (GameWorld) getWorld();
        world.updateLifeCounter(hp);
    }
    
    
}


