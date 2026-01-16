import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
    }
    

}



/**
 * need someone to add this code into the constructor if someone made it: 
 * Fruits player;

switch (CustomizeWorld.selectedFruitName) {
    case "Apple":
        player = new AnnoyingApple(1);
        break;
    case "Lemon":
        player = new AnnoyingLemon(1);
        break;
    case "Pineapple":
        player = new AnnoyingPineapple(1);
        break;
    case "Kiwi":
        player = new AnnoyingKiwi(1);
        break;
    case "Orange":
        player = new AnnoyingOrange(1);
        break;
    default:
        player = new AnnoyingApple(1); // safe fallback
}

addObject(player, 100, 300);

 */
