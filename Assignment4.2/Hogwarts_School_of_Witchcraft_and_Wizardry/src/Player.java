import java.util.ArrayList;

/**
 * This class is part of the "Hogwarts School of Witchcraft and Wizardry" application.
 *
 * @author Eric Wallace
 * @version 1.0
 */
public class Player {
    private Room currentRoom;
    private String name;
    private ArrayList<Item> inventory;

    public Player(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        inventory = new ArrayList<>();
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
        System.out.println(item.getName() + " added to inventory.");
    }

    public void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door in that direction!");
        } else {
            currentRoom = nextRoom;
            System.out.println("You are now in " + currentRoom.getLongDescription());
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getName() {
        return name;
    }

    public int getInventorySize() {
        return inventory.size();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
