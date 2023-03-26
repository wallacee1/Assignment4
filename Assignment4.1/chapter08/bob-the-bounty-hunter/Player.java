 
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author Eric Wallace
 * @version 1
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private String name;
    private ArrayList<Item> items;
    public static int max_inventory_weight = 1500;
    private int inventoryWeight;
    /**
     * Constructor for objects of class Player
     */
    public Player(String name, Room startingRoom)
    {
        // initialise instance variables
        this.name = name;
        this.currentRoom = startingRoom;
        items = new ArrayList<>();
        inventoryWeight = 0;
    }

    /**
     * Method to set the current room.
     */
    public void setCurrentRoom(Room currentRoom)
    {
        // set the current room
        this.currentRoom = currentRoom;
    }
    
    /**
     * Takes the item from the current room.
     * @param item
     */
    public void takeItem(Item item)
    {
        items.add(item);
        inventoryWeight += item.getWeight();
    }
    
    /**
     * Player eats cookie to increase load capacity.
     */
    public void eatCookie()
    {
        max_inventory_weight += 2500;
        System.out.println("Cookie eaten.");
        System.out.println("New max capacity: " + max_inventory_weight);
    }
    
    /**
     * Drop an item in the current room.
     * @param itemName The item to be removed.
     */
    public Item dropItem(String itemName)
    {
        Item item = null;
        for(Item i : items) {
            if(i.getDescription().equals(itemName)) {
                item = i;
                System.out.println(item.getDescription() + "dropped");
                inventoryWeight -= item.getWeight();
            }
        }
        items.remove(item);
        if(item == null) {
            System.out.println("This player does not have such item.");
        }
        System.out.println("Current player inventory:");
        for(Item i : items) {
            System.out.println(i.getItemInfo());
        }
        System.out.println("Current carrying" + "capacity:" + inventoryWeight + "g.");
        return item;
    }
    
    /**
     *  Return the current room.
     *  
     *  @return Return the current room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     *  Return the players name.
     *  @return Return the player name.
     */
    public String getName()
    {
        return name;
    } 
    
    /**
     * Return inventory weight.
     * @return inventoryWeight.
     * 
     */
    public int getInventoryWeight()
    {
        return inventoryWeight;
    }
    
    /**
     * 
     */
    public void itemInventory()
    {
        System.out.println("Current player inventory:");
        for(Item i : items) {
            System.out.println(i.getItemInfo());
        }
        System.out.print("Current load:" + inventoryWeight + " g.\n");
    }
    
}
