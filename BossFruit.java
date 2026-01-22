import greenfoot.*;  

/**
 * This boss fruit is inspired by Donkey Kong, but it's an AI Watermelon It's purpose is to spawn, 
 * then it shoots balls out of itself onto the platforms and down the platforms as obstacles for the player. 
 * 
 * @author (Kalkie) 
 * @version (Jan 2025)
 */

public class BossFruit extends Actor
{
    // Boss stat
    private int hp = 200;
    private int strength = 10;
    private static GreenfootSound bossHitSound = new GreenfootSound("losinghp.wav");


    //calculating what level we're on, what is the constant (2x+10) x = level you're at
    //increasing hp level 
    
    // Animation variables
    private int fadeAlpha = 0;      // starts invisible
    private boolean fadingIn = true;

    private int shakeTimer = 0;
    private int throwTimer = 0;
    
    private boolean isFalling = false;
    private boolean isAtBottom = false;

    private double yVelocity = 0;
    private double gravity = 0.5;

    public BossFruit()
    {
        GreenfootImage img = new GreenfootImage("newaibossfruit.png");
        img.scale(img.getWidth() / 12, img.getHeight() / 12);
        img.setTransparency(0); // start invisible
        setImage(img);
    }

    public void act()
    {
        if(isFalling){
            fall();
            return;
        }
        
        fadeInEffect();
        shakeInPlace();
        
        if (!isAtBottom) {
            throwMiniFruit();
        }
        
        if(isAtBottom){
            Greenfoot.setWorld(new VictoryWorld());
        }
    }

    /**
     * Slowly fades the boss in when the level starts
     */
    private void fadeInEffect()
    {
        if (fadingIn)
        {
            fadeAlpha += 2; // speed of fade

            if (fadeAlpha >= 255)
            {
                fadeAlpha = 255;
                fadingIn = false;
            }

            getImage().setTransparency(fadeAlpha);
        }
    }

    /**
     * Small shaking animation to feel "alive"
     */
    private void shakeInPlace()
    {
        shakeTimer++;

        if (shakeTimer % 20 == 0)
        {
            setLocation(getX() + 1, getY());
        }
        else if (shakeTimer % 20 == 10)
        {
            setLocation(getX() - 1, getY());
        }
    }

    /**
     * Throws mini fruits occasionally
     */
    private void throwMiniFruit()
    {
        if (isFalling) {
            return; // Don't throw fruits while falling
        }
        throwTimer++;

        if (throwTimer > 120) // every ~2 seconds
        {
            MiniFruit mini = new MiniFruit(strength);
            getWorld().addObject(mini, getX() + 20, getY());
            throwTimer = 0;
        }
    }

    /**
     * Boss takes damage
     */
    public void takeDamage(int damage)
    {
        bossHitSound.play();
        hp -= damage;

        if (hp <= 0)
        {
            getWorld().removeObject(this);
        }
    }
    
    public void startFalling(){
        isFalling = true;
    }
    
    private void fall(){
        yVelocity += gravity; 
        int newY = getY() + (int)yVelocity;
        
        MyWorld world = (MyWorld) getWorld();
        
        // Check if boss reached the bottom
        if (newY >= world.getHeight() - getImage().getHeight() / 2) {
            // Stop at the bottom
            setLocation(getX(), world.getHeight() - getImage().getHeight() / 2);
            isFalling = false;
            isAtBottom = true;
            yVelocity = 0;
        } else {
            setLocation(getX(), newY);
        }
    }
}