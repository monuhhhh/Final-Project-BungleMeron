import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.UserInfo;
/**
 * Write a description of class MyWorld here.
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
    
    private int dropletTimer = 0;
    
    
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
    private void addPlayerFruit(int x, int y) {
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