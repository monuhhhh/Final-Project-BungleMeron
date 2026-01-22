import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This obstacle is inspired by a Super Mario-style droplet.
 * Player gains 10HP if they jump directly on top, otherwise they lose 15HP.
 */
public class Droplets extends SuperSmoothMover
{
    private int fallTime; // delay in frames before the object starts falling
    private int actCounter = 0; // counter to track number of frames passed

    private static final String IMAGE_PATH = "droplets.png";

    public Droplets(int fallTime)
    {
        this.fallTime = fallTime;

        GreenfootImage img = new GreenfootImage(IMAGE_PATH);
        img.scale(img.getWidth() / 8, img.getHeight() / 8); // make small
        setImage(img);
    }

    public void act()
    {
        if (getWorld() == null) return;

        if (actCounter > fallTime) {
            setLocation(getX(), getY() + 1); // move droplet down 1 pixel
        }
        actCounter++;

        // remove droplet if it reaches bottom of world
        if (getY() > getWorld().getHeight() - 30) {
            getWorld().removeObject(this);
            return;
        }

        // check collision with player
        checkPlayerCollision();
    }

    /**
     * Checks if PlayerFruit contacts this droplet and applies HP accordingly
     */
    private void checkPlayerCollision() {
        for (PlayerFruit player : getWorld().getObjects(PlayerFruit.class)) {

            // droplet hitbox dimensions
            int boxW = 24;
            int boxH = 34;

            // hitbox edges
            int dropletLeft   = getX() - boxW / 2;
            int dropletRight  = getX() + boxW / 2;
            int dropletTop    = getY() - boxH / 2;
            int dropletBottom = getY() + boxH / 2;

            // player edges
            int playerLeft   = player.getX() - player.getImage().getWidth() / 2;
            int playerRight  = player.getX() + player.getImage().getWidth() / 2;
            int playerTop    = player.getY() - player.getImage().getHeight() / 2;
            int playerBottom = player.getY() + player.getImage().getHeight() / 2;

            // check for overlap
            boolean overlap =
                    playerRight  > dropletLeft &&
                    playerLeft   < dropletRight &&
                    playerBottom > dropletTop &&
                    playerTop    < dropletBottom;

            if (!overlap) continue;

            // ±10px stomp window against flat top
            boolean hitFromTop =
                    Math.abs(playerBottom - dropletTop) <= 10 &&
                    player.getY() < getY();

            if (hitFromTop) {
                player.changeHP(10);
                player.yVelocity = -8;
            } else {
                player.changeHP(-15);
            }

            getWorld().removeObject(this);
            return;
        }
    }
}



