package WorldOfZuul2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Item{
    final private String name;
    final private double x;
    final private double y;
    final private double itemCenterX;
    final private double itemCenterY;
    final private int size;
    final private int price;
    final private int energyModifier;
    final private String categoryName;
    final private String imageLink;
    
    public Item(String categoryName, String name, double x, double y, int size, String imageLink){
        this.categoryName = categoryName;
        this.name = name;
        this.price = 0;
        this.energyModifier = 0;
        this.x = x;
        this.y = y;
        this.size = size;
        this.imageLink = imageLink;
        this.itemCenterX = this.x + (this.size / 2);
        this.itemCenterY = this.y + (this.size / 2);
    }

    public Item(String categoryName, String name, int price, int energyModifier, double x, double y, int size, String imageLink){
        this.categoryName = categoryName;
        this.name = name;
        this.price = price;
        this.energyModifier = energyModifier;
        this.x = x;
        this.y = y;
        this.size = size;
        this.imageLink = imageLink;
        this.itemCenterX = this.x + (this.size / 2);
        this.itemCenterY = this.y + (this.size / 2);
    }

    // Get the distance from this item to the player
    public double calculateDistance(Player p){
        return Math.sqrt(Math.pow(p.getX() + (p.getSize() / 2) - itemCenterX, 2) + Math.pow(p.getY() + (p.getSize() / 2) - itemCenterY, 2));
    }
    
    public void drawInteractableCircle(GraphicsContext gc, double diameter, Player p){
        if (calculateDistance(p) < diameter / 2){
            gc.setFill(Color.CORNFLOWERBLUE);
        } else{
            gc.setFill(Color.CRIMSON);
        }
        gc.fillOval(itemCenterX - diameter / 2, itemCenterY - diameter / 2, diameter, diameter);
    }
    
    // Get image link
    public String getImageLink(){
        return imageLink;
    }
    
    // Get x position
    public double getX(){
        return x;
    }
    
    // Get y position
    public double getY(){
        return y;
    }
    
    public double getCenterX(){
        return itemCenterX;
    }
    
    public double getCenterY(){
        return itemCenterY;
    }
    
    // Get item name
    public String getName(){
        return name;
    }
    
    // Get item size
    public int getSize(){
        return size;
    }
}
