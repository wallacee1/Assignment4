import java.util.List;
import java.util.Random;
/**
 * Write a description of class TransporterRoom here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TransporterRoom extends Room
{
    // instance variables - replace the example below with your own
    private List rooms;

    /**
     * Constructor for objects of class TransporterRoom
     */
    public TransporterRoom(String description)
    {
        // initialise instance variables
        super(description);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setRooms(List<Room> rooms)
    {
        // put your code here
        this.rooms = rooms;
    }
    
    @Override
    public Room getExit(String direction)
    {
        if(getExit().get(direction) == null) {
            return null;
        }
        return findRandomRoom();
    }
    
    private Room findRandomRoom()
    {
        if(rooms == null || rooms.size() == 0) {
            return null;
        }
        Random random = new Random();
        int choice = random.nextInt(rooms.size());
        return rooms.get(choice);
    }
}
