
/**
 * Write a description of class Characters here.
 *
 * @author Eric Wallace
 * @version 1
 */
public class Character
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private String quest;
    private String questItem;
    private Item reward;
    private Room currentRoom;
    /**
     * Constructor for objects of class Characters
     */
    public Character(String name, String description, String quest, String questItem, Item reward, Room currentRoom)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        this.questItem = questItem;
        this.quest = quest;
        this.reward = reward;
        setCurrentRoom(currentRoom);
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room room)
    {
        currentRoom.addCharacter(this);
        currentRoom = room;
    }
    
    public String getCharacterInfo()
    {
        return "name: " + name + "\ndescription: " + description;
    }
    
    public String talk()
    {
        return quest;
    }
    
    public Item giveReward()
    {
        Item i = reward;
        reward = null;
        return i;
    }

    /**
     * Set name
     *
     */
    public void setName()
    {
        // put your code here
        this.name = name;
    }
    
    /** 
     * Set description
     *
     */
    public void setDescription()
    {
        // put your code here
        this.description = description;
    }
    
    /**
     * Set questItem
     */
    public void setQuestItem()
    {
        this.questItem = questItem;
    }
    
    /** 
     * Set quest
     *
     */
    public void setQuest()
    {
        // put your code here
        this.quest = quest;
    }
    
    /**
     * Get name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Get QuestItem
     * 
     */
    public String getQuestItem()
    {
        return questItem;
    }
    
    /**
     * Get quest
     */
    public String getQuest()
    {
        return quest;
    }
}
