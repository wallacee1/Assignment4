
/**
 * An item that can be in a given room. It has a description and weight.
 *
 * @author Eric Wallace
 * @version 1
 */
public class Item 
{
    // Description of the item.
    private String description;
    // Item weight.
    private int weight;
    private String name;
    /**
     * Constructor for Item
     */
    public Item(String description, int weight, String name)
    {
        // initialise instance variables
        this.description = description;
        this.weight = weight;
        this.name = name;
    }
    
    /**
     *  
     */
    public String getItemInfo() 
    {
        if(description.equals(" "))
            return " ";
        return "there is " + description + ", weight: " + weight + ", name: " + name;
    }
    /**
     * Return the description
     * @return description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Return the weight
     * 
     */
    public int getWeight()
    {
        return weight;
    }
}
