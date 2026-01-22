import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fruits is a superclass for all fruit characters in the game.
 * It defines shared behaviours like movement direction. 
 * Most other variables are specific to PlayerFruit and have been removed.
 * 
 * @author Monika Kouyoumdjian
 * @version Jan 16 2026
 */
public abstract class Fruits extends SuperSmoothMover
{
    protected int direction;  // 1 for right, -1 for left
    
    protected boolean frozen = false; 

    //constructor for fruits 
    public Fruits(int direction) {
        this.direction = direction;
    }
    
    //base movement (can be overridden by subclasses)
    protected void moveFruit() {
        if (getWorld() == null) return;
        setLocation(getX() + direction, getY());
    }

    //abstract promised 
    protected abstract void checkEdge();
}

