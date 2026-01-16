import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Goal extends Actor
{
    public Goal() {
        // Make platform invisible since background shows shelves
        GreenfootImage img = new GreenfootImage(40, 40);
        img.setColor(new Color(0, 0, 0, 0)); // Fully transparent
        img.fill();
        setImage(img);
    }
}
