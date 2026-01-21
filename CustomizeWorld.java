import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * CustomizeWorld allows players to select their fruit character they want to control.
 * 
 * @author Carmen Cheung
 * @version 13 Jan, 2026
 */
public class CustomizeWorld extends World
{
    //List of available fruit names
    private ArrayList<String> fruitNames = new ArrayList<>();
    
    //index to track which fruit is currently selected
    private int index = 0; 
    
    //the fruit currently shown
    private PlayerFruit displayedFruit;
    
    //the fruit chosen by the player
    private PlayerFruit selectedFruit; 
    
    /**
     * Constructor for objects of class CustomizeWorld.
     * 
     */
    public CustomizeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        setBackground(new GreenfootImage("af.png"));
        
        // display fruits
        fruitNames.add("Lemon");
        fruitNames.add("Pineapple");
        fruitNames.add("Apple");
        fruitNames.add("Kiwi");
        fruitNames.add("Orange");
        
        //show the frist fruit when the world starts
        showCurrentFruit();
        
        //Add navigation and selection buttons
        addObject(new PrevButton(this), 60, 320);
        addObject(new AfterButton(this), 210, 320);
        addObject(new SelectButton(this), 135, 320);
    }
    
    /**
     * Displays the currently selected fruit
     * and removes the previously displayed one
     */    
    public void showCurrentFruit(){
        //remove old display
        if (displayedFruit != null) {
            removeObject(displayedFruit);
        }
        
        //create a new fruit based on the current index
        displayedFruit = createFruit(fruitNames.get(index));
        
        //Add the fruit to the world         
        addObject(displayedFruit, 130, 220);
        
        //scale down the fruit image 
        GreenfootImage img = new GreenfootImage(displayedFruit.getImage());
        img.scale(img.getWidth() / 2, img.getHeight() / 2);
        displayedFruit.setImage(img);
        
        // Display its stats
        showStats(displayedFruit);
    }
    
    /**
     * Creates and returns the correct fruit object
     * based on the fruit name
     */    
    private PlayerFruit createFruit(String name){
        //Load image from Fruit folder
        String imagePath = "Fruit/" + name + ".png"; 
        //create fruit
        switch(name){
            case "Lemon": return new Lemon(imagePath);
            case "Pineapple": return new Pineapple(imagePath);
            case "Apple": return new Apple(imagePath);
            case "Kiwi": return new Kiwi(imagePath);
            case "Orange": return new Orange(imagePath);
            default:
                System.out.println("Warning: No class found for fruit: " + name);
                return null;
        }
         
    }
    
    /**
     * displays the selected fruit's stats
     */
    private void showStats(PlayerFruit f){
        // Clear previous text
        showText("", 420, 160);
        showText("", 420, 190);
        showText("", 420, 220);
        showText("", 420, 255);
    
        //show fruit name
        showText(f.getName(), 420, 190);
        //show fruit hp
        showText(" HP: " + (int)f.hp, 420, 220);
    }
    
    /**
     * switch to the next fruit in the list
     */
    public void nextFruit(){
        index = (index + 1) % fruitNames.size();
        showCurrentFruit();
    }
    
    /**
     * switches to the previous fruit in the list
     */
    public void prevFruit(){
        index = (index - 1 + fruitNames.size()) % fruitNames.size();
        showCurrentFruit();
    }
    
    /**
     * save the selcted fruit and navigate to the MyWorld
     */
    public void selectFruit() { 
        selectedFruit = displayedFruit; 
        
        //Pass selected fruit to main world
        MyWorld.selectedFruit = selectedFruit; 
        
        //switch to the main game world
        Greenfoot.setWorld(new MyWorld()); 
    }
    
    /**
     * return the currently displayed fruit
     */
    public PlayerFruit getSelectedFruit() {
        return displayedFruit;
    }
}