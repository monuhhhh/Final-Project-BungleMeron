import greenfoot.*;

/**
 * MiniFruit rolls down platforms like Donkey Kong barrels.
 */
public class MiniFruit extends Actor
{
    private int speed = 2;
    private int damage;

    public MiniFruit(int bossStrength)
    {
        damage = bossStrength;

        GreenfootImage img = new GreenfootImage("Fruit/Watermelon.png");
        img.scale(img.getWidth() / 6, img.getHeight() / 6);
        setImage(img);
    }

    public void act()
    {
        roll();
        fallIfNeeded();
        checkPlayerHit();
    }

    private void roll()
    {
        move(speed);
    }

    private void fallIfNeeded()
    {
        MyWorld world = (MyWorld)getWorld();

        if (!world.isPlatformBelow(getX(), getY()))
        {
            setLocation(getX(), getY() + 3);
        }
    }

    private void checkPlayerHit()
    {
        PlayerFruit player = (PlayerFruit)getOneIntersectingObject(PlayerFruit.class);

        if (player != null)
        {
            player.takeDamage(damage);
            getWorld().removeObject(this);
        }
    }
}
