package WorldOfZuul2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Player extends WorldOfZuul2D{
    private double x;
    private double y;
    final private int size;
    private static boolean up, down, left, right;
    final private String imageLink;
    
    public Player(double x, double y, int size, String imageLink){
        this.x = x;
        this.y = y;
        this.size = size;
        this.imageLink = imageLink;
    }
    
    public void move(Stage stage, GraphicsContext gc){
        setMovementVariables();
        
        // Movement speed and direction
        if (getDirection().contains("up")) y -= 4;
        if (getDirection().contains("down")) y += 4;
        if (getDirection().contains("left")) x -= 4;
        if (getDirection().contains("right")) x += 4;

        // Go through doors
        for (Exit exit: super.getCurrentRoom().getExits()){
            if (Math.sqrt(Math.pow(x + (size / 2) - exit.getCenterX(), 2) + Math.pow(y + (size / 2) - exit.getCenterY(), 2)) < size){
                switch(exit.getDirection()){
                    case "up":
                        y = stage.getHeight() - size - 125 - 15;
                        break;
                    case "down":
                        y = 0 + size + 125;
                        break;
                    case "right":
                        x = 0 + size + 125;
                        break;
                    case "left":
                        x = stage.getWidth() - size - 125 - 15;
                        break;
                }
                super.getCurrentRoom().goRoom(exit.getDirection());
            }
        }
        
        // Collisions with items
        for (Item item: super.getCurrentRoom().getItems()){
            if (Math.sqrt(Math.pow(x + (size / 2) - item.getCenterX(), 2) + Math.pow(y + (size / 2) - item.getCenterY(), 2)) < (size / 2) + (item.getSize() / 2)){
                if (getDirection().contains("up")) y += 4;
                if (getDirection().contains("down")) y -= 4;
                if (getDirection().contains("left")) x += 4;
                if (getDirection().contains("right")) x -= 4;
            }
        }
        
        // Collisions with walls
        if (x < 125) x = 125;
        if (x > stage.getWidth() - 125 - size) x = stage.getWidth() - 125 - size;
        if (y < 125) y = 125;
        if (y > stage.getHeight() - 125 - size) y = stage.getHeight() - 125 - size;
    }
    
    // Set movememnt variables
    public void setMovementVariables(){
        up = super.getCurrentlyActiveKeys().contains("UP") ? true : false;
        down = super.getCurrentlyActiveKeys().contains("DOWN") ? true : false;
        left = super.getCurrentlyActiveKeys().contains("LEFT") ? true : false;
        right = super.getCurrentlyActiveKeys().contains("RIGHT") ? true : false;
    }
    
    // Check which direction variables are active
    public String getDirection(){
        String directions = "";
        if (up == true) directions += "up ";
        if (down == true) directions += "down ";
        if (left == true) directions += "left ";
        if (right == true) directions += "right ";
        return directions;
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
    
    public int getSize(){
        return size;
    }
    
    // Get image link
    public String getImageLink(){
        return imageLink;
    }
}
