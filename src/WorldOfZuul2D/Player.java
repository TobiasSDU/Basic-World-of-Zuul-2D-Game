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
        displayDistanceToItems(gc);
        
        // Movement speed and direction
        if (getDirection().contains("up")) y -= 4;
        if (getDirection().contains("down")) y += 4;
        if (getDirection().contains("left")) x -= 4;
        if (getDirection().contains("right")) x += 4;

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
    
    // Display distance to items in the currently active room
    public void displayDistanceToItems(GraphicsContext gc){
        for (int i = 0; i < super.getCurrentRoom().getItems().size(); i++){
            gc.fillText("Afstand til  " + super.getCurrentRoom().getItems().get(i).getName()
                  + ": " + super.getCurrentRoom().getItems().get(i).calculateDistance(this),
                                                                         500, (i + 1) * 20);
        }
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
