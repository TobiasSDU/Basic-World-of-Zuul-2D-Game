package WorldOfZuul2D;

import java.util.HashSet;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public final class UserInput extends WorldOfZuul2D {
    // Get user key input
    public static HashSet<String> getUserInput(Scene scene){
        HashSet<String> activeKeys;
        
        // Use a set to prevent duplicates
        activeKeys = new HashSet<String>();
        
        // When a key is pressed, add it to currently active keys
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                activeKeys.add(event.getCode().toString());
            }
        });
        
        // When a key is released, remove it from currently active keys
        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                activeKeys.remove(event.getCode().toString());
            }
        });
        
        return activeKeys;
    }
}
