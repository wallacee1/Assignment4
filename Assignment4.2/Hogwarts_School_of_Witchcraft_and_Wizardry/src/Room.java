import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "Hogwarts School of Witchcraft and Wizardry" application.
 *
 * @author Eric Wallace
 * @version 1.0
 */
public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getDescription() {
        return description;
    }

    public Item checkForItems() {
        if (items.isEmpty()) {
            return null;
        }
        return items.get(0);
    }

    public String getLongDescription() {
        String itemString = "";
        for (Item item : items) {
            itemString += item.getName() + "\n";
        }
        return "You are " + description + ".\n" + itemString + getExitString();
    }

    private String getExitString() {
        String exitString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            exitString += " " + exit;
        }
        return exitString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItemToInventory(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void printExits() {
        String exitString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            exitString += " " + exit;
        }
        System.out.println(exitString);
    }
}
