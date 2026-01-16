import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectButton here.
 * 
 * @author Carmen Cheung 
 * @version Jan 13, 2025
 */
public class SelectButton extends Button
{
    /**
     * Act - do whatever the SelectButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private CustomizeWorld world; 
    
    public SelectButton(CustomizeWorld w){
        this.world = w; 
        setButtonImage();
    }
    
    private void setButtonImage(){
        GreenfootImage image = new GreenfootImage("select.png");
        image.scale(70, 40);
        setImage(image);
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
             world.selectFruit(); 
        }
    }
}
