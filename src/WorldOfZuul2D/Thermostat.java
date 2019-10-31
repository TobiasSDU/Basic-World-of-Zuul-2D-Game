package WorldOfZuul2D;

public class Thermostat {
    private double x;
    private double y;
    private String imageLink;
    private double heading;
    
    public Thermostat(double x, double y, String imageLink){
        this.x = x;
        this.y = y;
        this.imageLink = imageLink;
        heading = 0;
    }
    
    public String getImageLink(){
        return imageLink;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
}
