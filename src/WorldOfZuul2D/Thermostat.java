package WorldOfZuul2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Thermostat extends WorldOfZuul2D{
    private double x;
    private double y;
    private static boolean xKey, cKey;  
    private double rotation;
    double setting;
    
    public Thermostat(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    // Draw and update thermostat
    public void displayThermostat(GraphicsContext gc){
        setActiveKeys();
        setThermostatRotation();
        
        // Translate to middle of thermostat before rotating, then return
        gc.save();
        gc.translate(550, 550);
        gc.rotate(rotation);
        gc.translate(-550, -550);
        
        // Draw thermostat
        gc.setFill(Color.WHITE);
        gc.fillOval(450, 450, 200, 200);
        gc.setFill(Color.BLACK);
        gc.fillRect(547, 460, 6, 30);
        gc.restore();
        
        // Display thermostat value
        setting = Math.sqrt(Math.pow(Math.floor((rotation % 360) / 36), 2));
        gc.setFill(Color.BLACK);
        gc.fillText("Thermostat: " + (int)setting, 500, 550);
    }
    
    // Set rotation key variables
    public void setActiveKeys(){
        xKey = super.getCurrentlyActiveKeys().contains("X") ? true : false;
        cKey = super.getCurrentlyActiveKeys().contains("C") ? true : false;
    }
    
    // Check which direction to rotate the thermostat
    public void setThermostatRotation(){
        if (rotation > 0){
            if (xKey) rotation -= 1;
        }
        if (rotation < 359){
            if (cKey) rotation += 1;
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
    
    // Get rotation value
    public double getThermostatRotation(){
        return rotation;
    }
    
    // Set rotation value
    public void setThermostatRotation(double amount){
        rotation += amount;
    }
}


