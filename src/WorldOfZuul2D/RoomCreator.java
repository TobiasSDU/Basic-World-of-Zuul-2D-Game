package WorldOfZuul2D;

import javafx.stage.Stage;

public final class RoomCreator extends WorldOfZuul2D{
    // Create rooms
    public static Room createRooms(Stage stage){
        // Define which rooms should in the game
        Room kitchen, livingRoom, bedroom, office, garden;

        // Create an instance of each room with a description
        kitchen = new Room("Rum: Køkken", "Køkken", 37, 174, 96);
        livingRoom = new Room("Rum: Stue", "Stue", 142, 150, 173);
        bedroom = new Room("Rum: Soveværelse", "Soveværelse", 52, 73, 94);
        office = new Room("Rum: Kontor", "Kontor", 230, 126, 34);
        garden = new Room("Rum: Have", "Have", 26, 188, 156);

        // Settings for each room
        kitchen.setExit("øst", livingRoom, "images/doorVertical.png");
        kitchen.setExit("syd", office, "images/doorHorizontal.png");
        kitchen.setExit("vest", bedroom, "images/doorVertical.png");
        kitchen.addItem("Køleskab", 650, 650, 50, "images/item.png");
        kitchen.addItem("Komfur", 605, 180, 50, "images/item.png");

        livingRoom.setExit("vest", kitchen, "images/doorVertical.png");
        livingRoom.addItem("Vindue", 650, 100, 50, "images/item.png");
        livingRoom.addItem("Brændeovn", 375, 375, 50, "images/item.png");

        bedroom.setExit("øst", kitchen, "images/doorVertical.png");
        bedroom.addItem("Vindue", 100, 200, 50, "images/item.png");

        office.setExit("nord", kitchen, "images/doorHorizontal.png");
        office.setExit("øst", garden, "images/doorVertical.png");
        office.addItem("Computer", 100, 650, 50, "images/item.png");
        office.addItem("Radiator", 650, 650, 50, "images/item.png");

        garden.setExit("vest", office, "images/doorVertical.png");
        garden.addItem("Tag (Solceller)", 375, 375, 50, "images/item.png");

        // Set the start location
        return kitchen;
    }
}
