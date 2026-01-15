import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * CustomizeWorld allows players to select their fruit characters before the simulation starts.
 * Each fruit has unique stats that affect the ending result of the game depends on which fruit player picks.
 * 
 * @author Carmen Cheung
 * @version 13 Jan, 2026
 */
public class CustomizeWorld extends World
{
    //List of available fruit names
    private ArrayList<String> fruitNames = new ArrayList<>();
    
    private int index = 0; 
    
    //the fruit currently shown
    private PlayerFruit displayedFruit;
    
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
        
        showCurrentFruit();
        
        //Add navigation and selection buttons
        addObject(new PrevButton(this), 60, 320);
        addObject(new AfterButton(this), 210, 320);
        addObject(new SelectButton(this), 135, 320);
    }
    
    //Displays the current fruit and removes the previous fruit display
    public void showCurrentFruit(){
        //remove old display
        if (displayedFruit != null) {
            removeObject(displayedFruit);
        }
        
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
    
    //create the appropriate fruit object based on name
    private PlayerFruit createFruit(String name){
        //Load image from Fruit folder
        String imagePath = "Fruit/" + name + ".png"; 
        //create fruit
        switch(name){
            //case "Lemon": return new Lemon(imagePath);
            //case "Pineapple": return new Pineapple(imagePath);
            case "Apple": return new Apple(imagePath);
            //case "Kiwi": return new Kiwi(imagePath);
            //case "Orange": return new Orange(imagePath);
            default:
                System.out.println("Warning: No class found for fruit: " + name);
                return null;
        }
         
    }
    
    private void showStats(PlayerFruit f){
        // Clear previous text
        showText("", 420, 160);
        showText("", 420, 190);
        showText("", 420, 220);
        showText("", 420, 255);
    
        showText("HP: " + (int)f.hp, 420, 160);
    }
    
    public void nextFruit(){
        index = (index + 1) % fruitNames.size();
        showCurrentFruit();
    }
    
    public void prevFruit(){
        index = (index - 1 + fruitNames.size()) % fruitNames.size();
        showCurrentFruit();
    }
    
    public void selectFruit() { 
        selectedFruit = displayedFruit; 
        
        //Pass selected fruit to main world
        MyWorld.selectedFruit = selectedFruit; 
        
        Greenfoot.setWorld(new MyWorld()); 
    }
    
    public PlayerFruit getSelectedFruit() {
        return displayedFruit;
    }
}