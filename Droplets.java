import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//credits to Falling Objects - Greenfoot,org 
//Monika kouyoumdjian
//Jan 16
public class Droplets extends SuperSmoothMover//code taken from monika grade 11 project
{
    private int fallTime; //delay in frames before the ofject starts falling
    private int actCounter = 0; //counter to track the numebr of frames that have passed
    
    public Droplets(int fallTime){
        this.fallTime = fallTime; //set the delay before the object starts falling
    }
     
    public void act() 
    {
        if(actCounter > fallTime){
            setLocation(getX(), getY() + 1); //more the perry down i pixel at a time
        }
        actCounter++; //increment 
        
        if (getY() > getWorld().getHeight() - 30) {
            getWorld().removeObject(this); //remove perry from the worl 
        }
 
    }
}
