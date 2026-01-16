import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author Carmen Cheung 
 * @version 14 Jan, 2026
 */

public class MyWorld extends World
{
    // Static variable to hold the selected fruit from CustomizeWorld
    public static PlayerFruit selectedFruit;
    
    private int[][] grid;
    private int cellSize = 40;
    private int gridWidth = 12; // Adjusted for 480 width (480/40 = 12)
    private int gridHeight = 17; // Adjusted for 675 height (675/40 ≈ 17)
    private int currentLevel = 1;
    
    public MyWorld()
    {    
        super(480, 675, 1);
        setBackground("levelbg.png");
        initializeGrid();
        buildLevel(currentLevel);
        showGrid(); 
    }
    
    private void initializeGrid() {
        grid = new int[gridHeight][gridWidth];
    }
    
    private void buildLevel(int levelNumber) {
        removeObjects(getObjects(null));
        
        if (levelNumber == 1) {
            int[][] level1 = {
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 0 - Top space
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 1
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 2
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 3 - Top shelf (with goal)
                {0,0,0,5,0,0,2,0,0,0,0,0}, // Row 4 - Space
                {1,1,1,1,1,1,2,1,1,1,1,1}, // Row 5 - Ladder
                {0,0,0,0,0,0,0,2,0,0,0,0}, // Row 6 - Second shelf
                {1,1,1,1,1,1,1,2,1,1,1,1}, // Row 7 - Space
                {0,0,0,0,2,0,0,0,0,0,0,0}, // Row 8 - Ladder
                {1,1,1,1,2,1,1,1,1,1,1,1}, // Row 9 - Ladder
                {0,0,0,0,2,0,0,0,0,0,0,0}, // Row 10 - Third shelf
                {0,0,2,0,0,0,0,0,0,0,0,0}, // Row 11 - Space
                {1,1,2,1,1,1,1,1,1,1,1,1}, // Row 12 - Ladder
                {0,0,2,0,0,0,0,0,0,0,4,0}, // Row 13 - Ladder
                {1,1,1,1,1,1,1,1,1,1,1,1}, // Row 14 - Ladder
                {0,0,0,0,0,0,0,0,0,0,0,0}, // Row 15 - Bottom shelf (player spawns here)
                {0,0,0,0,0,0,0,0,0,0,0,0}  // Row 16 - Drawer area at bottom
            };
            grid = level1;
        }
        else if (levelNumber == 2) {
            int[][] level2 = {
                {0,0,0,0,0,0,0,0,0,0,0,0}, 
                {0,0,0,0,0,0,0,0,0,0,0,0}, 
                {0,0,0,0,0,0,0,0,0,0,0,0}, 
                {5,1,1,1,1,1,1,1,1,1,1,1}, // Top shelf with goal
                {0,0,0,0,0,0,0,0,0,0,0,0}, 
                {0,0,0,0,0,0,2,0,0,0,0,0}, 
                {0,0,0,0,0,0,2,0,0,0,0,0}, 
                {0,1,1,1,0,0,0,0,1,1,1,1}, // Second shelf with gap
                {0,0,0,0,0,0,0,0,0,0,0,0}, 
                {0,0,0,0,2,0,0,0,0,0,0,0}, 
                {0,0,0,0,2,0,0,0,0,0,0,0}, 
                {0,1,1,1,1,1,1,0,0,0,1,1}, // Third shelf with gap
                {0,0,0,0,0,0,0,0,0,0,0,0}, 
                {0,0,2,0,0,0,0,0,0,0,0,0}, 
                {0,0,2,0,0,0,0,0,0,0,0,0}, 
                {0,1,1,1,1,1,1,1,1,1,1,4}, // Bottom shelf
                {0,0,0,0,0,0,0,0,0,0,0,0}  
            };
            grid = level2;
        }
        
        placeObjects();
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
        addObject(player, x, y);
        
        // Scale the fruit image appropriately for gameplay
        GreenfootImage img = new GreenfootImage(player.getImage());
        img.scale(img.getWidth() / 5, img.getHeight() / 5); // Adjust size for gameplay
        player.setImage(img);
        
        player.onGround = true;
        player.yVelocity = 0;
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
}