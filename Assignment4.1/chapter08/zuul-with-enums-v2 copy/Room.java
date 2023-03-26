import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Character> characters;
    private Character talkingTo = null;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<Item>();
        characters = new ArrayList<Character>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + getItemsInfo() + getCharactersInfo();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public void addCharacter(String name, String description, String quest, String questItem, Item reward)
    {
        addCharacter(new Character(name, description, quest, questItem, reward, this));
    }
    
    public String[] getExitDirections()
    {
        return (String[]) exits.keySet().toArray();
    }
    
    public void addCharacter(Character character) 
    {
        characters.add(character);
    }
    
    public void removeCharacter(Character character)
    {
        characters.remove(character);
    }
    
    public Character getCharacter(String name)
    {
        Character character = null;
        for (Character c : characters) {
            if (c.getName().equals(name)) {
                character = c;
                characters.remove(c);
                break;
            }
        }
        if (character == null) {
            System.out.println("There is no such character in this room.");
        }
        return character;
    }
    
    private String getCharactersInfo()
    {
        String s = " ";
        for (Character c : characters)
            s += c.getCharacterInfo();
        return s;
    }
    
    private void talk(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Talk to whom?");
            return;
        }
        String name = command.getSecondWord();
        Character c = player.getCurrentRoom().getCharacter(name);
        
        talkingTo = c;
        if (talkingTo == null) {
            System.out.println("This character is not present in this room");
        } 
        else {
            System.out.println(c.talk());
        }
    }
    
    private void give(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Give what?");
            return;
        }
        if(talkingTo == null) {
            System.out.println("You have to first talk to someone in order to give an item.");
        }
        else {
            Item item = talkingTo.getReward();
            String name = command.getSecondWord();
            if (name.equals(talkingTo.getQuestItem())) {
                if(character.getInventoryWeight() + item.getWeight() <= character.getMaxWeight()) {
                    character.dropItem(name);
                    character.takeItem(talkingTo.giveReward());
                    character.getCurrentRoom().removeCharacter(talkingTo);
                    talkingTo = null;
                }
                else {
                    System.out.println("Item too heavy! Try dropping some items.");
                }
            }
            else {
                System.out.println("Wrong item is given.");
            }
        }
    }
}

