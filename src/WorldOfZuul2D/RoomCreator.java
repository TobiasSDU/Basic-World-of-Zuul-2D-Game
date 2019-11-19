package WorldOfZuul2D;

import javafx.stage.Stage;

public final class RoomCreator extends WorldOfZuul2D{
    // Create rooms
    public static Room createRooms(Stage stage){
        // Define which rooms should in the game
        Room lounge, bedroom, garden, office, attic, kitchen;

        // Create an instance of each room with a description
        lounge = new Room("Lounge", 37, 174, 96);
        attic = new Room("Attic", 142, 150, 173);
        bedroom = new Room("Bedroom", 52, 73, 94);
        office = new Room("Office", 230, 126, 34);
        garden = new Room("Garden", 26, 188, 156);
        kitchen = new Room("Kitchen", 122, 219, 0);

        // Set exits
        lounge.setExit("left", office, "images/doorVertical.png");
        lounge.setExit("right", garden, "images/doorVertical.png");
        lounge.setExit("up", bedroom, "images/doorHorizontal.png");
        lounge.setExit("down", kitchen, "images/doorHorizontal.png");
        
        bedroom.setExit("up", attic, "images/doorHorizontal.png");
        bedroom.setExit("down", lounge, "images/doorHorizontal.png");

        attic.setExit("down", bedroom, "images/doorHorizontal.png");

        garden.setExit("left", lounge, "images/doorVertical.png");

        office.setExit("right", lounge, "images/doorVertical.png");

        kitchen.setExit("up", lounge, "images/doorHorizontal.png");
        
        // Lounge items
        lounge.addItem(new Item("Carpets", "Carpet", 200, 200, 50, "images/item.png"));
        lounge.addItem(new Item("Windows", "Lounge Window", 500, 500, 50, "images/item.png"));
        lounge.addItem(new Item("Heaters", "Radiator", 200, 500, 50, "images/item.png"));
        lounge.addItem(new Item("Wall Insulation", "No Insulation", 500, 200, 50, "images/item.png"));

        // Bedroom items
        bedroom.addItem(new Item("Beds", "Bed", 200, 200, 50, "images/item.png"));
        bedroom.addItem(new Item("Windows", "Bedroom Window", 500, 500, 50, "images/item.png"));
        bedroom.addItem(new Item("Curtains", "Curtains", 200, 500, 50, "images/item.png"));
        bedroom.addItem(new Item("Wall Insulation", "No Insulation", 500, 200, 50, "images/item.png"));

        // Garden items
        garden.addItem(new Item("Solar Panels", "Roof", 200, 200, 50, "images/item.png"));
        garden.addItem(new Item("Greenhouses", "Greenhouse", 500, 500, 50, "images/item.png"));

        // Office items
        //office.setItem(computer);

        // Attic items
        attic.addItem(new Item("Attic Insulation", "No Insulation", 200, 200, 50, "images/item.png"));

        // Kitchen items
        kitchen.addItem(new Item("Appliances", "Old Fridge", 200, 200, 50, "images/item.png"));
        kitchen.addItem(new Item("Appliances", "Old Washing Machine", 500, 500, 50, "images/item.png"));
        kitchen.addItem(new Item("Appliances", "Old Clothes dryer", 500, 200, 50, "images/item.png"));

        // Set the start location
        return lounge;
    }
}
