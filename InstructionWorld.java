import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * InstructionWorld displays the game instructions before gameplay begins.
 * 
 * @author Carmen Cheung
 * @version Jan 16, 2026
 */
public class InstructionWorld extends World
{
    /**
     * Constructor for objects of class InstructionWorld.
     * 
     */
    public InstructionWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);  
        setBackground(new GreenfootImage("instruction.png"));
        //start button that proceed to CustomizeWorld
        StartButton start = new StartButton("CustomizeWorld");
        addObject(start, getWidth()/2, getHeight()/2 + 130); 
    }
}
