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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
        // Stage settings
        stage.setTitle("UpHouse Test");
        stage.setWidth(750);
        stage.setHeight(750);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        
        // Root settings
        Group root = new Group();
        ObservableList nodeList = root.getChildren();
        
        // Create scene
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        
        // Create canvas and add to root
        Canvas canvas = new Canvas(stage.getWidth(), stage.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Create rooms 
        createRooms(stage);
        
        // Get user input
        getUserInput(scene);
        
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
                public void handle(ActionEvent ae){
                    // Clear the canvas
                    gc.clearRect(0, 0, stage.getWidth(), stage.getHeight());

                    // Set time since the game was started
                    double t = (System.currentTimeMillis() - startTime) / 1000;           
                    
                    // Text settings
                    Font font = Font.font("Lato", FontWeight.NORMAL, 16);
                    gc.setFont(font);

                    // Print name of the currently active room
                    displayRoomText(gc);

                    // Update player position
                    player.move(stage, gc);

                    // Set background color
                    background.setFill(Color.rgb(currentRoom.getBR(), currentRoom.getBG(), currentRoom.getBB()));

                    // Display images
                    gc.drawImage(playerSprite, player.getX(), player.getY());
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
   
    // Get user key input
    private static void getUserInput(Scene scene){
        // Use a set to prevent duplicates
        currentlyActiveKeys = new HashSet<String>();
        
        // When a key is pressed, add it to currently active keys
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                currentlyActiveKeys.add(event.getCode().toString());
            }
        });
        
        // When a key is released, remove it from currently active keys
        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                currentlyActiveKeys.remove(event.getCode().toString());
            }
        });
    }
    
    // Create rooms
    private void createRooms(Stage stage){
        // Define which rooms should in the game
        Room kitchen, livingRoom, bedroom, office, garden;

        // Create an instance of each room with a description
        kitchen = new Room("Rum: Køkken", "Køkken", 37, 174, 96);
        livingRoom = new Room("Rum: Stue", "Stue", 142, 150, 173);
        bedroom = new Room("Rum: Soveværelse", "Soveværelse", 52, 73, 94);
        office = new Room("Rum: Kontor", "Kontor", 230, 126, 34);
        garden = new Room("Rum: Have", "Have", 26, 188, 156);

        // Settings for each room
        kitchen.setExit("øst", kitchen, livingRoom, "images/doorVertical.png");
        kitchen.setExit("syd", kitchen, office, "images/doorHorizontal.png");
        kitchen.setExit("vest", kitchen, bedroom, "images/doorVertical.png");
        kitchen.addItem("Køleskab", 650, 650, 50, "images/item.png");
        kitchen.addItem("Komfur", 605, 180, 50, "images/item.png");

        livingRoom.setExit("vest", livingRoom, kitchen, "images/doorVertical.png");
        livingRoom.addItem("Vindue", 650, 100, 50, "images/item.png");
        livingRoom.addItem("Brændeovn", 375, 375, 50, "images/item.png");

        bedroom.setExit("øst", bedroom, kitchen, "images/doorVertical.png");
        bedroom.addItem("Vindue", 100, 200, 50, "images/item.png");

        office.setExit("nord", office, kitchen, "images/doorHorizontal.png");
        office.setExit("øst", office, garden, "images/doorVertical.png");
        office.addItem("Computer", 100, 650, 50, "images/item.png");
        office.addItem("Radiator", 650, 650, 50, "images/item.png");

        garden.setExit("vest", garden, office, "images/doorVertical.png");
        garden.addItem("Tag (Solceller)", 375, 375, 50, "images/item.png");

        // Set the start location
        currentRoom = kitchen; 
    }

    // Get the room that the player is currently in
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    // Move between rooms
    public void goRoom(String direction){   
        // Get the exit of the chosen room
        Room nextRoom = currentRoom.getExit(direction);

        // Go to the next room and show its description
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getDescription());
        }
    }

    // Display text with info about what room the player is in
    public static void displayRoomText(GraphicsContext gc){
      gc.setFill(Color.WHITE);
      gc.fillText("Rum: " + currentRoom.getName(), 10, 25);
    }
    
    // Get HashMap of currently active keys
    public HashSet<String> getCurrentlyActiveKeys(){
        return currentlyActiveKeys;
    }
    
    // Launch application
    public static void main(String[] args) {
        launch(args);
    }
}
