import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ladder here.
 * 
 * @author Carmen Cheung 
 * @version (a version number or a date)
 */

public class Ladder extends Actor
{
    public Ladder() {
        GreenfootImage img = new GreenfootImage(40, 40);
        img.setColor(Color.ORANGE);
        img.fillRect(0, 0, 10, 40);
        img.fillRect(30, 0, 10, 40);
        img.fillRect(0, 10, 40, 5);
        img.fillRect(0, 25, 40, 5);
        setImage(img);
    }
}
