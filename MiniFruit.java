import greenfoot.*;

public class MiniFruit extends Actor
{
    private int speed = 2;
    private int damage;

    private int stage = 0; // which ladder we are heading to
    private boolean climbing = false;

    // Ladder X positions (pixels)
    private int[] ladderX = {250, 305, 170, 90};
    // Where to stop climbing (Y pixel)
    private int[] ladderEndY = {260, 340, 460, 540};

    public MiniFruit(int bossStrength)
    {
        damage = bossStrength;

        GreenfootImage img = new GreenfootImage("minifruit.png");
        img.scale(img.getWidth() / 50, img.getHeight() / 50);
        setImage(img);
    }
    
    public int getDamage() {
        return damage;
    }

    public void act(){
        //if (isTouching(MiniFruit.class)) return;
    
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
    
        //checkPlayerHit();
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
            if (getWorld().getObjects(PlayerFruit.class).isEmpty()) return;

            
        PlayerFruit player = (PlayerFruit)getWorld()
                .getObjects(PlayerFruit.class)
                .get(0);

        if (player.getX() > getX())
            setLocation(getX() + speed, getY());
        else
            setLocation(getX() - speed, getY());
    }

    /**
    
    private void checkPlayerHit(){
        PlayerFruit player = (PlayerFruit)getOneIntersectingObject(PlayerFruit.class);
    
        if (player != null)
        {
            player.damageMe(10);   // exact damage
            getWorld().removeObject(this);
        }
    }*/
    
 
}
   

