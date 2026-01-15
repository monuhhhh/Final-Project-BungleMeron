import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * CustomizeWorld alos players to select thier fruit characters before the simulation starts,
 * player first choose the "Annoying" team fruit, the the "AI" team fruit, each fruit has 
 * unique stats that effect the ending result of the game depends on which fruit player pick.
 * 
 * @author Carmen Cheung
 * @version 24 Nov,2025
 */
public class CustomizeWorld extends World
{
    //List of available fruit names for each team
    private ArrayList<String> annoyingFruitNames = new ArrayList<>();
    private ArrayList<String> aiFruitNames = new ArrayList<>();
    
    private int index = 0; 
    
    //the fruit currently shown
    private Fruits displayedFruit;
    
    private Fruits AnnoyingFruit;
    private Fruits AiFruit; 
    
    private int selectingPlayer = 1; 
    // 1 = picking Annoying fruit
    // 2 = picking Ai fruit
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
        annoyingFruitNames.add("Lemon1");
        annoyingFruitNames.add("Pineapple1");
        annoyingFruitNames.add("Apple1");
        annoyingFruitNames.add("Kiwi1");
        annoyingFruitNames.add("Orange1");
        
        aiFruitNames.add("Lemon2");
        aiFruitNames.add("Pineapple2");
        aiFruitNames.add("Apple2");
        aiFruitNames.add("Kiwi2");
        aiFruitNames.add("Orange2");
        
        showCurrentFruit();
        
        //Add navigation and selection buttons
        addObject(new PrevButton(this), 60, 320);
        addObject(new AfterButton(this), 210, 320);
        addObject(new SelectButton(this), 135, 320);
    }
    
    //Displays the current fruit an removes the previous fruit display
    public void showCurrentFruit(){
        //remove old display
        if (displayedFruit != null) {
            removeObject(displayedFruit);
        }
        
        // Create the actual fruit object
        ArrayList<String> list = (selectingPlayer == 1) ? annoyingFruitNames : aiFruitNames;

        displayedFruit = createFruit(list.get(index));
        displayedFruit.setPreviewMode(true);
        
        //Add the fruit to the world         
        addObject(displayedFruit, 130, 220);
        
        //scale down the fruit image 
        GreenfootImage img = new GreenfootImage(displayedFruit.getImage());
        img.scale(img.getWidth() / 2, img.getHeight() / 2);
        displayedFruit.setImage(img);
        
        // Display its stats
        showStats(displayedFruit);
    }
    
    //create the appropriate fruit object based on namr and selecting player
    private Fruits createFruit(String name){
        //create Annoying team fruit
        if (selectingPlayer == 1){
            switch(name){
                case "Lemon1": return new AnnoyingLemon(1, Fruits.TEAM_ANNOYING);
                case "Pineapple1": return new AnnoyingPinapple(1, Fruits.TEAM_ANNOYING);
                case "Apple1": return new AnnoyingApple(1, Fruits.TEAM_ANNOYING);
                case "Kiwi1": return new AnnoyingKiwi(1, Fruits.TEAM_ANNOYING);
                case "Orange1": return new AnnoyingOrange(1, Fruits.TEAM_ANNOYING);
            }
        }
        
        if (selectingPlayer == 2){
            switch(name){
                case "Lemon2": return new AILemon(-1, Fruits.TEAM_AI);
                case "Pineapple2": return new AIPinapple(-1, Fruits.TEAM_AI);
                case "Apple2": return new AIApple(-1, Fruits.TEAM_AI);
                case "Kiwi2": return new AIKiwi(-1, Fruits.TEAM_AI);
                case "Orange2": return new AIOrange(-1, Fruits.TEAM_AI);
            }
        }
        
        return null; 
    }
    
    private void showStats(Fruits f){
        // Clear previous text
        showText("", 420, 160);
        showText("", 420, 190);
        showText("", 420, 220);
        showText("", 420, 255);
    
        showText(" " + (int)f.hp, 420, 160);
        showText(" " + (int)f.maxHP, 420, 190);
        showText(" " + (int)f.strength, 420, 220);
        showText(" " + f.reactionTime, 420, 255);
    }
    
    public void nextFruit(){
        ArrayList<String> list = (selectingPlayer == 1) ? annoyingFruitNames : aiFruitNames;
        index = (index + 1) % list.size();
        showCurrentFruit();
    }
    
    public void prevFruit(){
        ArrayList<String> list = (selectingPlayer == 1) ? annoyingFruitNames : aiFruitNames;
        index = (index - 1 + list.size()) % list.size();
        showCurrentFruit();
    }
    
    public void selectFruit() { 
        if(selectingPlayer ==1){
            AnnoyingFruit = displayedFruit;
            
            selectingPlayer = 2; 
            index = 0; 
            
            setBackground(new GreenfootImage("ai.png")); 
            
            showCurrentFruit();
        } else if(selectingPlayer == 2){
            AiFruit = displayedFruit;
            
            MyWorld.selectedAnnoyingFruit = AnnoyingFruit;  
            MyWorld.selectedAIFruit = AiFruit;
            
            Greenfoot.setWorld(new MyWorld());
        }
    }
    

    public Fruits getSelectedFruit() {
        return displayedFruit;
    }
    
}
