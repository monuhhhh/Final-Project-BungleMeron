import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.UserInfo;
/**
 *
 * This game is inspiered by the iconic Nintendo game, Donkey Kong! We names our game BungleMeron since 
 * the original games name follows the pattern of "(foolish) (common term in Japan for giant ape)". 
 * So we Found a sysnonym for foolish, and found the word for Melon in Japanese.
 * 
 * The games story is that the Giant boss melon turned up the temperature in the fridge and is guarding the thermostat! 
 * Your mission is to clibm your way to the top through obstacles over the course of 5 levels to banish the Boss and 
 * restore the correct temperature setting!
 * 
 * Instructions for Mr.Cohen:
 *      Avoid obstacles my jumping over them with the space key, move left and right with the arrow keys and to climb
 *      the ladders. The miniFruit obstacle that the boss shoots out decrease hp by 10, the droplets falling from the sky
 *      can increase hp by 10 if you perfectly jump ontop, or decrease hp by 15. 
 *      
 *      Make contact with the boss at the top of each Level in order to move onto the next level, Once you reach the 
 *      last level. You will have an opportunity to defeat the boss by making them fall off the shelf. 
 * 
 * Cheats to get there faster:
 *      This cheat for thsi game also requiers you to have a but of skill! In order to jump to the next level
 *      without having to climb the ladder that is swarming with miniFruit obstacels, you must jump perfectly 
 *      ontop of the droplet obstacle (this also has you gain 10 hp) when its floating around halfway through the shelf. 
 *      You will be able to jump through a shelf and potentially arvoid some obstacles. 
 * 
 * Grid implementation:
 *      The game world is organized as a 2D grid with 12 columns and 17 rows, where each cell is 40 pixels. 
 *      Each cell holds a number representing what’s in that space: 0 = empty, 1 = platform, 2 = ladder, 
 *      3 = obstacle/block, 4 = player start, 5 = goal. When a level loads, the grid is read and objects 
 *      are placed in the world centered in their cells. Helper methods like getGridValue(), 
 *      isPlatformBelow(), and isLadderAt() use the grid for collision detection and movement logic. 
 *      showGrid() can be used for debugging to display cell boundaries and values.
 *      
 * 
 * Save/load implimentation:
 *      The game saves the player’s current HP using Greenfoot’s UserInfo system. When a level is 
 *      completed, the player’s HP is stored in slot 0 with user.store(). On the next level, the game 
 *      retrieves this value and applies it to the player so health carries over between levels. This 
 *      ensures continuity in player progress and allows HP to persist even if the game is closed and 
 *      restarted.
 * 
 * credits:
 *      Code:
 *      - monika gr11 final project
 *      
 *      Sound:
 *      
 *      Images: 
 *      - droplet: Adobe stock image Cartoon Water Droplet Image 
 *      
 *      
 *      
 * 
 * Bugs: not any known bugs 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Carmen Cheung, Monika, Kalkie
 * @version 14 Jan, 2026
 */

public class MyWorld extends World
{
    // Static variable to hold the selected fruit from aCustomizeWorld
    public static PlayerFruit selectedFruit;
    
    private int[][] grid;
    private int cellSize = 40;
    private int gridWidth = 12; // Adjusted for 480 width (480/40 = 12)
    private int gridHeight = 17; // Adjusted for 675 height (675/40 ≈ 17)
    private int currentLevel = 1;
    
    private int dropletTimer = 0;//monika
    
    
    public MyWorld()
    {    
        super(480, 675, 1);
        setBackground("levelbg.png");
        
        
        initializeGrid();
        buildLevel(currentLevel);
        showGrid(); 
    }
    
    public void act(){//monika
        spawnDroplets(); 
    }
    
    private void initializeGrid() {
        grid = new int[gridHeight][gridWidth];
    }
    
    private void buildLevel(int levelNumber) {
        removeObjects(getObjects(null));
        
        if (levelNumber == 1) {
            int[][] level1 = {
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 0 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 1 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 2 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 3 - space
                {0,0,0,5,2,0,0,0,0,0,0,0}, // Row 4 - top shelf with goal
                {1,1,1,1,2,1,1,1,1,1,1,1}, // Row 5 - ladder
                {0,0,0,0,2,0,0,2,0,0,0,0}, // Row 6 - ladder
                {1,1,1,1,1,1,1,2,1,1,1,1}, // Row 7 - second shelf
                {0,0,0,0,2,0,0,2,0,0,0,0}, // Row 8 - Ladder
                {1,1,1,1,2,1,1,1,1,1,1,1}, // Row 9 - third shelf
                {0,0,0,0,2,0,0,0,0,0,0,0}, // Row 10 - ladder
                {0,0,2,0,2,0,0,0,0,0,0,0}, // Row 11 - ladder
                {1,1,2,1,1,1,1,1,1,1,1,1}, // Row 12 - fourth shelf
                {0,0,2,0,0,0,0,0,0,0,4,0}, // Row 13 - Ladder
                {1,1,1,1,1,1,1,1,1,1,1,1}, // Row 14 - fifth shelf
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 15 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}  // Row 16 - space
            };
            grid = level1;
        }
        else if (levelNumber == 2) {
            int[][] level2 = {
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 0 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 1 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 2 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 3 - space
                {0,0,0,5,0,0,0,0,0,0,2,0}, // Row 4 - top shelf with goal
                {1,2,1,1,1,1,1,1,1,1,2,1}, // Row 5 - ladder
                {0,2,0,0,0,0,0,0,0,0,0,0}, // Row 6 - ladder
                {1,1,1,1,1,1,1,1,2,1,1,1}, // Row 7 - second shelf
                {0,0,0,0,0,0,0,0,2,0,0,0}, // Row 8 - Ladder
                {1,1,2,1,1,1,1,1,1,1,1,1}, // Row 9 - third shelf
                {0,0,2,0,0,0,0,0,0,0,0,0}, // Row 10 - ladder
                {0,0,2,0,0,0,0,0,0,0,0,0}, // Row 11 - ladder
                {1,1,1,1,1,1,2,1,1,1,1,1}, // Row 12 - fourth shelf
                {0,0,0,0,0,0,2,0,0,0,4,0}, // Row 13 - Ladder
                {1,1,1,1,1,1,1,1,1,1,1,1}, // Row 14 - fifth shelf
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 15 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}  // Row 16 - space  
            };
            grid = level2;
        }
        else if(levelNumber == 3) {
            int[][] level3 = {
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 0 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 1 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 2 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 3 - space
                {0,0,0,5,0,0,0,0,0,0,2,0}, // Row 4 - top shelf with goal
                {1,0,1,1,1,1,1,1,1,1,2,1}, // Row 5 - ladder
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 6 - ladder
                {1,1,1,1,1,1,1,1,2,1,1,1}, // Row 7 - second shelf
                {0,0,0,0,0,0,0,0,2,0,0,0}, // Row 8 - Ladder
                {1,1,2,1,1,1,1,1,1,1,1,1}, // Row 9 - third shelf
                {0,0,2,0,0,0,0,0,0,0,0,0}, // Row 10 - ladder
                {0,0,2,0,0,0,0,0,0,0,0,0}, // Row 11 - ladder
                {1,1,1,1,1,1,2,1,1,1,1,1}, // Row 12 - fourth shelf
                {0,0,0,0,0,0,2,0,0,0,4,0}, // Row 13 - Ladder
                {1,1,1,1,1,1,1,1,1,1,1,1}, // Row 14 - fifth shelf
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 15 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}  // Row 16 - space 
            }; 
            grid = level3; 
        }
        else if (levelNumber == 4) {
            int[][] level4 = {
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 0 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 1 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 2 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 3 - space
                {0,0,0,5,0,0,0,0,0,0,0,0}, // Row 4 - top shelf with goal
                {1,1,1,1,1,1,1,1,1,1,2,1}, // Row 5 - ladder
                {0,0,0,0,0,0,0,0,0,0,2,0}, // Row 6 - ladder
                {1,1,1,1,1,2,1,1,1,1,1,1}, // Row 7 - second shelf
                {0,0,0,0,0,2,0,0,0,0,0,0}, // Row 8 - Ladder
                {1,1,1,1,1,1,1,1,2,1,1,1}, // Row 9 - third shelf
                {0,0,0,0,0,0,0,0,2,0,0,0}, // Row 10 - ladder
                {0,0,0,0,0,0,0,0,2,0,0,0}, // Row 11 - ladder
                {1,2,1,1,1,1,1,1,1,1,1,1}, // Row 12 - fourth shelf
                {0,2,0,0,0,0,0,0,0,0,4,0}, // Row 13 - Ladder
                {1,1,1,1,1,1,1,1,1,1,1,1}, // Row 14 - fifth shelf
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 15 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}  // Row 16 - space
            };
            grid = level4; 
        }
        else if (levelNumber == 5){
            int[][] level5 = {
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 0 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 1 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 2 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 3 - space
                {0,0,0,5,0,0,0,0,2,0,0,0}, // Row 4 - top shelf with goal
                {1,1,1,1,2,1,1,1,2,1,1,1}, // Row 5 - ladder
                {0,0,0,0,2,0,0,0,0,0,0,0}, // Row 6 - ladder
                {1,1,1,1,1,1,1,1,2,1,1,1}, // Row 7 - second shelf
                {0,0,0,0,0,0,0,0,2,0,0,0}, // Row 8 - Ladder
                {1,2,1,1,1,1,1,1,1,1,1,1}, // Row 9 - third shelf
                {0,2,0,0,0,0,0,0,0,0,0,0}, // Row 10 - ladder
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 11 - ladder
                {1,1,1,1,1,1,1,1,2,1,1,1}, // Row 12 - fourth shelf
                {0,0,0,0,0,0,0,0,2,0,4,0}, // Row 13 - Ladder
                {1,1,1,1,1,1,1,1,1,1,1,1}, // Row 14 - fifth shelf
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 15 - space
                {0,0,0,0,0,0,0,0,0,0,0,0}  // Row 16 - space 
            };
            grid = level5; 
        }
        placeObjects();
        showLevelNumber(); 
        
        PlayerFruit player = (PlayerFruit)getObjects(PlayerFruit.class).get(0);
        updateLifeCounter(player.hp);
        
        spawnBoss();
        spawnDroplets();
        
        
    }        
        
    private void spawnBoss(){//kalkie
        // Spawn exactly one boss per level
        BossFruit boss = new BossFruit();
        addObject(boss, 100, 160); 
    }
    
    private void spawnDroplets() {//monika
        dropletTimer++;
    
        int spawnDelay = Math.max(220 - currentLevel * 30, 80);
        
        if (dropletTimer >= spawnDelay) {
            dropletTimer = 0;
     
            int dropletsThisSpawn = currentLevel; // 1–5 droplets
    
            for (int i = 0; i < dropletsThisSpawn; i++) {
                int col = Greenfoot.getRandomNumber(gridWidth);
                int x = col * cellSize + cellSize / 2;
                int y = 0;
    
                int fallDelay = Greenfoot.getRandomNumber(60);
                addObject(new Droplets(fallDelay), x, y);
            }
        }
    }
    
    
    
    
    
    
    private void showLevelNumber() {
        showText("Level " + currentLevel, 60, 30); 
    }
    
    private void placeObjects() {
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                int x = col * cellSize + cellSize / 2;
                int y = row * cellSize + cellSize / 2;
                
                switch (grid[row][col]) {
                    case 1: // Platform
                        addObject(new Platform(), x, y);
                        break;
                    case 2: // Ladder
                        addObject(new Ladder(), x, y);
                        break;
                    case 3: // Obstacle/ Block
                        //addObject(new Block(), x, y);
                        break;
                    case 4: // Player start - Use selected fruit from CustomizeWorld
                        addPlayerFruit(x, y);
                        break;
                    case 5: // Goal
                        addObject(new Goal(), x, y);
                        break;
                }
            }
        }
    }
    
    /**
     * Add the player's selected fruit to the world
     */
    private void addPlayerFruit(int x, int y) {//momika
        if (selectedFruit == null) {
            // Default to Lemon if no fruit was selected
            selectedFruit = new Lemon("Fruit/Lemon.png");
        }
        // Create a new instance of the same fruit type
        PlayerFruit player = recreateFruit(selectedFruit);
        
        UserInfo user = UserInfo.getMyInfo();
        if(user != null){
            int savedHP = user.getInt(0);
            if(savedHP > 0){
                player.hp = savedHP; 
            }
        }
        
        addObject(player, x, y);
        
        // Scale the fruit image appropriately for gameplay
        GreenfootImage img = new GreenfootImage(player.getImage());
        img.scale(img.getWidth() / 5, img.getHeight() / 5); // Adjust size for gameplay
        player.setImage(img);
        
        player.onGround = true;
        player.yVelocity = 0;
        
        updateLifeCounter(player.hp); 
    }
    
    /**
     * Recreate a fruit based on the selected fruit's type
     */
    private PlayerFruit recreateFruit(PlayerFruit fruit) {
        String imagePath = "Fruit/";
        
        if (fruit instanceof Lemon) {
            return new Lemon(imagePath + "Lemon.png");
        } else if (fruit instanceof Pineapple) {
            return new Pineapple(imagePath + "Pineapple.png");
        } else if (fruit instanceof Apple) {
            return new Apple(imagePath + "Apple.png");
        } else if (fruit instanceof Kiwi) {
            return new Kiwi(imagePath + "Kiwi.png");
        } else if (fruit instanceof Orange) {
            return new Orange(imagePath + "Orange.png");
        }
        
        // Default fallback
        return new Lemon(imagePath + "Lemon.png");
    }
    
    public int getGridValue(int x, int y) {
        int col = x / cellSize;
        int row = y / cellSize;
        
        if (row >= 0 && row < gridHeight && col >= 0 && col < gridWidth) {
            return grid[row][col];
        }
        return 0;
    }
    
    public boolean isPlatformBelow(int x, int y) {
        return getGridValue(x, y + cellSize) == 1;
    }
    
    public boolean isLadderAt(int x, int y) {
        int col = x / cellSize;
        int row = y / cellSize;
        
        if (row >= 0 && row < gridHeight && col >= 0 && col < gridWidth) {
            return grid[row][col] == 2;
        }
        return false;
    }
    
    /**
     * Progress to the next level
     */
    public void nextLevel() {
        //save player hp
        PlayerFruit player = getObjects(PlayerFruit.class).get(0);
        savePlayerHP(player.hp);
        
        currentLevel++;
        buildLevel(currentLevel);
    }
    
    public int getCellSize() {
        return cellSize;
    }
    
    public int getCurrentLevel() {
        return currentLevel;
    }
    
        /**
     * Display grid lines and numbers for debugging
     */
    public void showGrid() {
        GreenfootImage bg = getBackground();
        bg.setColor(Color.RED);
        bg.setFont(new Font("Arial", false, false, 12));
        
        // Draw vertical lines and column numbers
        for (int col = 0; col <= gridWidth; col++) {
            int x = col * cellSize;
            bg.drawLine(x, 0, x, getHeight());
            if (col < gridWidth) {
                bg.drawString("" + col, x + 5, 15);
            }
        }
        
        // Draw horizontal lines and row numbers
        for (int row = 0; row <= gridHeight; row++) {
            int y = row * cellSize;
            bg.drawLine(0, y, getWidth(), y);
            if (row < gridHeight) {
                bg.drawString("" + row, 5, y + 20);
            }
        }
        
        // Draw cell values
        bg.setColor(Color.BLUE);
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                if (grid[row][col] != 0) {
                    int x = col * cellSize + cellSize / 2 - 5;
                    int y = row * cellSize + cellSize / 2 + 5;
                    bg.drawString("" + grid[row][col], x, y);
                }
            }
        }
    }
    
    public void lose() {//monika
        PlayerFruit player = getObjects(PlayerFruit.class).get(0);
        savePlayerHP(player.hp); 
        
        Greenfoot.setWorld(new EndWorld(player.hp, currentLevel));
    }
    
    public void updateLifeCounter(int hp) { //monika
        showText("HP: " + hp, getWidth() - 60, 30); // top right, like level number
    }
    
    public void savePlayerHP(int hp) {//monika
        UserInfo user = UserInfo.getMyInfo();
        if (user != null) {
            user.setInt(0, hp);   // slot 0 stores HP
            user.store();        
        }
    }
}