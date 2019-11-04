package WorldOfZuul2D;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Room extends WorldOfZuul2D{
    final private String description;
    final private String name;
    final private ArrayList<Exit> exits;
    final private ArrayList<Item> items;
    
    // Background-color RGB values
    final private int backgroundR;
    final private int backgroundG;
    final private int backgroundB;
    
    public Room(String description, String name, int bckgR, int bckgG, int bckgB){
        this.description = description;
        this.name = name;
        this.backgroundR = bckgR;
        this.backgroundG = bckgG;
        this.backgroundB = bckgB;

        exits = new ArrayList<>();
        items = new ArrayList<>();
    }
    
    // Add an exit to a room
    public void setExit(String direction, Room neighbor, String imageLink){
        exits.add(new Exit(direction, neighbor, imageLink));
    }
    
    // Get the room that an exit leads to by using the direction as the key
    public Room getExit(String direction){
        HashMap<String, Room> exitTargetRoom = new HashMap<>();

        for (Exit exit: exits){
            exitTargetRoom.put(exit.getDirection(), exit.getTargetRoom());
        }

        return exitTargetRoom.get(direction);
    }
    
    // Add an item to a room
    public void addItem(String name, double xPos, double yPos, int size, String imageLink){
        items.add(new Item(name, xPos, yPos, size, imageLink));
    }
    
    // Draw doors
    public void drawExits(Stage stage, GraphicsContext gc){
        // Set door position
        for(Exit exit: exits){
            switch (exit.getDirection()){
                case "vest":
                    exit.setX(0);
                    exit.setY(stage.getHeight() / 2 - 25); 
                    break;
                case "nord":
                    exit.setX(stage.getWidth() / 2 - 25);
                    exit.setY(0);
                    break;
                case "øst":
                    exit.setX(stage.getWidth() - 10);
                    exit.setY(stage.getHeight() / 2 - 25);
                    break;
                case "syd":
                    exit.setX(stage.getWidth() / 2 - 25);
                    exit.setY(stage.getHeight() - 10);
                    break;
                default:
                    break;
            }
            Image exitSprite = new Image(exit.getImageLink());
            gc.drawImage(exitSprite, exit.getX(), exit.getY());
        }
    }
    
    // Draw each item in the room
    public void displayItems(GraphicsContext gc){
        for (Item item: items){
            Image itemSprite = new Image(item.getImageLink());
            gc.drawImage(itemSprite, item.getX(), item.getY());
            
            // Display item text
            Font font = Font.font("Lato", FontWeight.NORMAL, 12);
            gc.setFont(font);
            gc.setFill(Color.WHITE);
            gc.fillText(item.getName(), item.getX(), item.getY() + (item.getSize() * 1.5));
        }
    }
    
    // Move between rooms
    public void goRoom(String direction){   
        // Get the exit of the chosen room
        Room nextRoom = super.getCurrentRoom().getExit(direction);

        // Go to the next room and show its description
        if (nextRoom != null) super.setCurrentRoom(nextRoom);
    }
    
    // Display text with info about what room the player is in
    public void displayRoomText(GraphicsContext gc){
      gc.setFill(Color.WHITE);
      gc.fillText("Rum: " + super.getCurrentRoom().getName(), 10, 25);
    }
    
    // Get room name
    public String getName(){
        return name;
    }
    
    // Get room description
    public String getDescription(){
        return description;
    }
    
    // Get ArrayList of exits
    public ArrayList<Exit> getExits(){
        return exits;
    }
    
    // Get ArrayList of items
    public ArrayList<Item> getItems(){
        return items;
    }
    
    // Get background color RGB values
    public int getBR(){
        return backgroundR;
    }
    
    public int getBG(){
        return backgroundG;
    }
    
    public int getBB(){
        return backgroundB;
    }
}
