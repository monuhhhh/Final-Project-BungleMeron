import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * CustomizeWorld allows players to select their Annoying fruit character
 * before the simulation starts.
 * 
 * The selected Annoying fruit will be used as the player character.
 * 
 * @author Monika kouyoumdjain and Carmen Cheung
 * @version 24 Nov, 2025
 */
public class CustomizeWorld extends World
{
    // List of available Annoying fruit names
    private ArrayList<String> annoyingFruitNames = new ArrayList<>();
    
    private int index = 0; 
    
    // the fruit currently shown
    private Fruits displayedFruit;
    
    public static String selectedFruitName;


    /**
     * Constructor for objects of class CustomizeWorld.
     */
    public CustomizeWorld()
    {    
        super(600, 400, 1); 
        setBackground(new GreenfootImage("af.png"));
        
        // display fruits
        annoyingFruitNames.add("Lemon");
        annoyingFruitNames.add("Pineapple");
        annoyingFruitNames.add("Apple");
        annoyingFruitNames.add("Kiwi");
        annoyingFruitNames.add("Orange");
        
        showCurrentFruit();
        
        // Add navigation and selection buttons
        addObject(new PrevButton(this), 60, 320);
        addObject(new AfterButton(this), 210, 320);
        addObject(new SelectButton(this), 135, 320);
    }
    
    // Displays the current fruit and removes the previous fruit display
    public void showCurrentFruit()
    {
        if (displayedFruit != null) {
            removeObject(displayedFruit);
        }
        
        displayedFruit = createFruit(annoyingFruitNames.get(index));
        displayedFruit.setPreviewMode(true);
        
        addObject(displayedFruit, 130, 220);
        
        // scale down the fruit image
        GreenfootImage img = new GreenfootImage(displayedFruit.getImage());
        img.scale(img.getWidth() / 2, img.getHeight() / 2);
        displayedFruit.setImage(img);
        
        // Display its stats
        showStats(displayedFruit);
    }
    
    // Create the appropriate Annoying fruit based on name
    private Fruits createFruit(String name)
    {
        switch(name){
            case "Lemon": return new AnnoyingLemon(1);
            case "Pineapple": return new AnnoyingPineapple(1);
            case "Apple": return new AnnoyingApple(1);
            case "Kiwi": return new AnnoyingKiwi(1);
            case "Orange": return new AnnoyingOrange(1);
        }
        return null;
    }
    
    // Displays stats for the selected fruit
    private void showStats(Fruits f)
    {
        showText("", 420, 160);
        showText("", 420, 190);
        showText("", 420, 220);
    
        showText("HP: " + (int)f.hp, 420, 160);
        showText("Max HP: " + (int)f.maxHP, 420, 190);
        showText("Strength: " + (int)f.strength, 420, 220);
    }
    
    public void nextFruit()
    {
        index = (index + 1) % annoyingFruitNames.size();
        showCurrentFruit();
    }
    
    public void prevFruit()
    {
        index = (index - 1 + annoyingFruitNames.size()) % annoyingFruitNames.size();
        showCurrentFruit();
    }
    
    // Finalizes selection and starts the game
    public void selectFruit(){
        selectedFruitName = annoyingFruitNames.get(index);
        Greenfoot.setWorld(new MyWorld());
    }
}
