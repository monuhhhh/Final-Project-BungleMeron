import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossFruit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class BossFruit extends Actor
{
    // Boss stats
    private int hp = 200;
    private int strength = 10;

    // Animation variables
    private int fadeAlpha = 0;      // starts invisible
    private boolean fadingIn = true;

    private int shakeTimer = 0;
    private int throwTimer = 0;

    public BossFruit()
    {
        GreenfootImage img = new GreenfootImage("Fruit/Watermelon.png");
        img.scale(img.getWidth() / 3, img.getHeight() / 3);
        img.setTransparency(0); // start invisible
        setImage(img);
    }

    public void act()
    {
        fadeInEffect();
        shakeInPlace();
        throwMiniFruit();
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
        hp -= damage;

        if (hp <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}
