package WorldOfZuul2D;

public class Exit {
    private double x;
    private double y;
    final private String direction;
    final private Room targetRoom;
    final private String imageLink;

    public Exit(String direction, Room targetRoom, String imageLink){
      this.direction = direction;
      this.targetRoom = targetRoom;
      this.imageLink = imageLink;
    }
    
    // Get x position of an exit 
    public double getX(){
        return x;
    }
    
    // Get y position of an exit
    public double getY(){
        return y;
    }
    
    // Get exit direction
    public String getDirection(){
        return direction;
    }
    
    // Get target room of an exit
    public Room getTargetRoom(){
        return targetRoom;
    }
    
    // Set x position of an exit
    public void setX(double newX){
        x = newX;
    }
    
    // Set y position of an exit
    public void setY(double newY){
        y = newY;
    }
    
    // Get image link
    public String getImageLink(){
        return imageLink;
    }
}
