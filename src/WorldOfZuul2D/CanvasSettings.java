package WorldOfZuul2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public final class CanvasSettings extends WorldOfZuul2D{
    public static void updateCanvas(GraphicsContext gc, Stage stage, Room currentRoom){
        // Clear the canvas
        gc.clearRect(0, 0, stage.getWidth(), stage.getHeight());

        // Text settings
        Font font = Font.font("Lato", FontWeight.NORMAL, 16);
        gc.setFont(font);

        // Print name of the currently active room
        currentRoom.displayRoomText(gc);
    }
}
