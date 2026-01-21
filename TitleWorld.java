import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TitleWorld displays the title screen of the game.
 * It provides a start button that navigate player to the Customize screen. 
 * 
 * @author Carmen Cheung 
 * @version Jan 13, 2025
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
        //start button that navigate to the customize screen
        StartButton start = new StartButton("InstructionWorld");
        addObject(start, getWidth()/2, getHeight()/2 + 80); 
    }
}

