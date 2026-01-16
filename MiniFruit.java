import greenfoot.*;

public class MiniFruit extends Actor
{
    private int speed = 2;
    private int damage;

    private int stage = 0; // which ladder we are heading to
    private boolean climbing = false;

    // Ladder X positions (pixels)
    private int[] ladderX = {230, 310, 150, 70};

    // Where to stop climbing (Y pixel)
    private int[] ladderEndY = {260, 340, 460, 540};

    public MiniFruit(int bossStrength)
    {
        damage = bossStrength;

        GreenfootImage img = new GreenfootImage("newaibossfruit.png");
        img.scale(img.getWidth() / 16, img.getHeight() / 16);
        setImage(img);
    }

    public void act()
    {
        if (stage >= ladderX.length)
        {
            moveTowardPlayer();
        }
        else if (climbing)
        {
            climbDown();
        }
        else
        {
            moveTowardLadder();
        }

        checkPlayerHit();
    }

    /* ---------------- PATH FOLLOWING ---------------- */

    private void moveTowardLadder()
    {
        int targetX = ladderX[stage];

        if (Math.abs(getX() - targetX) <= speed)
        {
            setLocation(targetX, getY());
            climbing = true;
        }
        else if (getX() < targetX)
        {
            setLocation(getX() + speed, getY());
        }
        else
        {
            setLocation(getX() - speed, getY());
        }
    }

    private void climbDown()
    {
        setLocation(getX(), getY() + 2);

        if (getY() >= ladderEndY[stage])
        {
            climbing = false;
            stage++;
        }
    }

    private void moveTowardPlayer()
    {
        PlayerFruit player = (PlayerFruit)getWorld()
                .getObjects(PlayerFruit.class)
                .get(0);

        if (player.getX() > getX())
            setLocation(getX() + speed, getY());
        else
            setLocation(getX() - speed, getY());
    }

    /* ---------------- DAMAGE ---------------- */

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
