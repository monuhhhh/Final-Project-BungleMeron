import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleWorld here.
 * 
 * @author Carmen Cheung 
 * @version (a version number or a date)
 */
public class TitleWorld extends World
{
 
    /**
     * Constructor for objects of class TitleWorld.
     * 
     */
    public TitleWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);  
        setBackground(new GreenfootImage("titleworld.png"));
        //Add start button
        StartButton start = new StartButton();
        addObject(start, getWidth()/2, getHeight()/2 + 80); 
    }
}

