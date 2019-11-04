package WorldOfZuul2D;

import java.util.HashSet;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class WorldOfZuul2D extends Application {
    private static HashSet<String> currentlyActiveKeys;
    private static Player player;
    private static Thermostat thermostat;
    private static Room currentRoom; 
    
    @Override
    public void init(){
        System.out.println("Started");
    }
    
    @Override
    public void start(Stage stage) {
        setStage(stage);
        
        // Root settings
        Group root = new Group();
        ObservableList nodeList = root.getChildren();
        
        // Create scene
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        
        // Create canvas and add to root
        Canvas canvas = new Canvas(stage.getWidth(), stage.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Create rooms 
        currentRoom = RoomCreator.createRooms(stage);
        
        // Get user input
        currentlyActiveKeys = UserInput.getUserInput(scene);
        
        // Create Player
        player = new Player(375, 375, 30, "/images/player.png");
        Image playerSprite = new Image(player.getImageLink());
        
        // Create thermostat
        thermostat = new Thermostat(stage.getWidth() - 300, stage.getHeight() - 300);

        // Create game loop and make it run indefinitely
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        
        // Create background
        Rectangle background = new Rectangle(0, 0, 750, 750);
        nodeList.add(background);
        
        // System time at application start  
        final long startTime = System.currentTimeMillis();
        
        KeyFrame kf;
        kf = new KeyFrame(
            Duration.seconds(0.017), // 60 FPS
            new EventHandler<ActionEvent>(){
                public void handle(ActionEvent ae) {
                    // Set time since the game was started
                    double t = (System.currentTimeMillis() - startTime) / 1000;
                    
                    // Clear canvas and print the name of the active room
                    CanvasSettings.updateCanvas(gc, stage, currentRoom);

                    // Draw and update player
                    player.move(stage, gc);
                    gc.drawImage(playerSprite, player.getX(), player.getY());
                    
                    // Set background color
                    background.setFill(Color.rgb(currentRoom.getBR(), currentRoom.getBG(), currentRoom.getBB()));

                    // Display items and exits in the active room
                    currentRoom.displayItems(gc);
                    currentRoom.drawExits(stage, gc);
                    
                    // Display thermostat
                    thermostat.displayThermostat(gc);
                }
            }
        );
        
        // Add keyFrames to game loop
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
        
        // Add canvas to root node
        nodeList.add(canvas);

        // Set stage scene and display stage
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop(){
        System.out.println("Stopped");
    }
    
    // Stage settings
    private void setStage(Stage stage){
        stage.setWidth(750);
        stage.setHeight(750);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
    }
   
    // Get HashSet of currently active Keys
    public HashSet<String> getCurrentlyActiveKeys(){
        return currentlyActiveKeys;
    }

    // Get the room that the player is currently in
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    // Set currently active room
    public static void setCurrentRoom(Room room){
        currentRoom = room;
    }
    
    // Launch application
    public static void main(String[] args) {
        launch(args);
    }
}
