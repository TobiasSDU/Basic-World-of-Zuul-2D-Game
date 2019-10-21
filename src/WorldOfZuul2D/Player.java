package WorldOfZuul2D;

import javafx.stage.Stage;

public class Player extends WorldOfZuul2D{
    private double x;
    private double y;
    final private int size;
    final private String imageLink;
    
    public Player(double x, double y, int size, String imageLink){
        this.x = x;
        this.y = y;
        this.size = size;
        this.imageLink = imageLink;
    }
    
    public void move(Stage stage){
        // Movement speed and direction
        if (super.getDirection().contains("up")) y -= 4;
        if (super.getDirection().contains("down")) y += 4;
        if (super.getDirection().contains("left")) x -= 4;
        if (super.getDirection().contains("right")) x += 4;

        // Go through doors
        for (Exit exit: super.getCurrentRoom().getExits()){
            if (Math.sqrt(Math.pow(x - exit.getX(), 2) + Math.pow(y - exit.getY(), 2)) < size){
                switch(exit.getDirection()){
                    case "nord":
                        y = stage.getHeight() - size - (size / 4);
                        break;
                    case "syd":
                        y = 0 + size + (size / 4);
                        break;
                    case "øst":
                        x = 0 + size;
                        break;
                    case "vest":
                        x = stage.getWidth() - size - 10;
                        break;
                }
                super.goRoom(exit.getDirection());
            }
        }
        
        // Collisions with walls
        if (x < 0) x = 0;
        if (x > stage.getWidth() - size) x = stage.getWidth() - size;
        if (y < 0) y = 0;
        if (y > stage.getHeight() - size) y = stage.getHeight() - size;
    }
    
    // Get x position
    public double getX(){
        return x;
    }
    
    // Get y position
    public double getY(){
        return y;
    }
    
    // Change x position by a certain amount
    public void setX(int amount){
        x += amount;
    }
    
    // Change y position by a certain amount
    public void setY(int amount){
        y += amount;
    }
    
    // Get image link
    public String getImageLink(){
        return imageLink;
    }
}
