import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Bob the Bounty Hunter" application. 
 * "Bob the Bounty Hunter" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Eric Wallace
 * @version 1
 */
public class Room 
{
    private String description;
    // Stores exits of the room.
    private HashMap<String, Room> exits;
    private Item item;
    private ArrayList<Item> items;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * @param itemDescription The item's description.
     * @param itemWeight The item's weight.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room to which the exit leads.
     */
    public void setExits(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Add a new item to the room.
     * @param itemDescription Item's description.
     * @param item weight Item's weight.
     */
    public void addItem(String itemDescription, int itemWeight, String itemName)
    {
        items.add(new Item(itemDescription, itemWeight, itemName));
    }
    
    /**
     *  Remove an item from the room.
     *  @param itemName The item to be removed.
     */
    public Item getItem(String itemName)
    {
        Item item = null;
        for(Item i : items) {
            if(i.getDescription().equals(itemName)) {
                item = i;
            }
        }
        if (item == null) {
            System.out.println("There is no such item in this room.");
            return item;
        }
        return item;
    }
    
    /**
     * Remove requested item.
     * @param item The item.
     * 
     */
    public void removeItem(Item item)
    {
        items.remove(item);
    }
    
    /**
     *  Group information on all the items.
     *  @return Description and weight of all the items.
     */
    public String getItemsInfo()
    {
        String itemsInfo = " ";
        for(Item item : items)
            itemsInfo += item.getItemInfo() + "\n";
        return itemsInfo;
    }
    
    /**
     *  Return the room that is reached if we go from this room in direction "direction."
     *  If there is no room in that direction, return null.
     *  @param direction The exit's direction.
     *  @param The room in the given direction.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Return a description of the room's exits,
     * for example "Exits: north west."
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return a long description of this room, of the form:
     *  You are in the kitchen.
     *  Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + getItemsInfo();
    }
}
