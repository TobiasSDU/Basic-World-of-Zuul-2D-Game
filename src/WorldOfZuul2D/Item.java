package WorldOfZuul2D;

public class Item{
    final private String name;
    final private double x;
    final private double y;
    final private double itemCenterX;
    final private double itemCenterY;
    final private int size;
    final private String imageLink;

    public Item(String name, double x, double y, int size, String imageLink){
      this.name = name;
      this.x = x;
      this.y = y;
      this.size = size;
      this.imageLink = imageLink;
      this.itemCenterX = this.x + (this.size / 2);
      this.itemCenterY = this.y + (this.size / 2);
    }

    // Get the distance from this item to the player
    public double calculateDistance(Player p){
        return Math.round(Math.sqrt(Math.pow((p.getX() - itemCenterX), 2) +
                                   Math.pow((p.getY() - itemCenterY), 2)));
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
    
    // Get item name
    public String getName(){
        return name;
    }
    
    // Get item size
    public int getSize(){
        return size;
    }
}
