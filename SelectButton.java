import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SelectButton allows the player to confirm their fruit selection in the CustomizeWorld.
 * 
 * @author Carmen Cheung 
 * @version Jan 13, 2025
 */
public class SelectButton extends Button
{
    private CustomizeWorld world; 
    
    /**
     * Constructs the SelectButton.
     * 
     * @param w the CustomizeWorld this button belongs to
     */
    public SelectButton(CustomizeWorld w){
        this.world = w; 
        setButtonImage();
    }
    
    /**
     * Loads and scales the buttom image.
     */
    private void setButtonImage(){
        GreenfootImage image = new GreenfootImage("select.png");
        image.scale(70, 40);
        setImage(image);
    }
    
    /**
     * Act - do whatever the SelectButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
             world.selectFruit(); 
        }
    }
}
