import java.util.Stack;
/**
 *  This class is the main class of the "Bob the Bounty Hunter" application. 
 *  "Bob the Bounty Hunter" is a very simple, text based adventure game.  Users 
 *  can hunt for fugitives to collect bounty.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Eric Wallace
 * @version 1
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> roomPath;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, barn, crops, livingRoom, bedroom, kitchen, cellar;
      
        // create the rooms
        outside = new Room("outside the main entrance of the fugitives alleged location");
        barn = new Room("in the barn");
        crops = new Room("in the crops");
        livingRoom = new Room("in the living room");
        bedroom = new Room("in the bedroom");
        kitchen = new Room("in the kitchen");
        cellar = new Room("in the cellar");
        // initialise room exits
        outside.setExits("north", livingRoom);
        outside.setExits("east", crops);
        outside.setExits("west", barn);
        
        barn.setExits("east", outside);
        
        crops.setExits("west", outside);
        
        livingRoom.setExits("east", kitchen);
        livingRoom.setExits("south", outside);
        livingRoom.setExits("west", bedroom);
        
        bedroom.setExits("east", livingRoom);
        
        kitchen.setExits("down", cellar);
        kitchen.setExits("west", livingRoom);
        
        cellar.setExits("up", kitchen);
        
        player = new Player("Bob", outside);
        roomPath = new Stack<>();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing " + player.getName() + ". Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Bob the Bounty Hunter!");
        System.out.println("Bob the Bounty Hunter is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("cuff")) {
            cuff(command);
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("back")) {
            back();
        }
        else if (commandWord.equals("items")) {
            items();
        }
        else if (commandWord.equals("cookie")) {
            cookieEat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommands());
    }
    
    /**
     *  Add method to for user to look around the current room.
     *  @return Description of the current room and exits.
     */
    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    /**
     *  Add method to handcuff the bounty.
     *  
     */
    private void cuff(Command command)
    {
        System.out.println("You have apprehended the fugitive, collect your bounty");
    }
    
    /**
     *  Take the player to the previous room.
     */
    private void back()
    {
        if(roomPath.empty()) {
            System.out.println("You are at the start," + "there is no previous room.");
        }
        else 
        {
           player.setCurrentRoom(roomPath.pop());
           printLocationInfo();
        }
    }
    
    /**
     * Take an item from the current room.
     * 
     */
    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = player.getCurrentRoom().getItem(itemName);
        int playerCapacity = Player.max_inventory_weight;
        if(player.getInventoryWeight() + item.getWeight() <= playerCapacity) {
            player.takeItem(item);
        }
        else {
            System.out.println("The item load is too heavy! Try dropping some items.");
        }
        System.out.println("Current capacity:" + player.getInventoryWeight() + "g.");
    }
    
    /**
     * Drop an item in the current room.
     * 
     */
    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        player.getCurrentRoom().addItem(player.dropItem(itemName));
    }
    
    /**
     * Eat cookie, increase carrying capacity.
     */
    public void cookieEat()
    {
        Item cookie = player.getCurrentRoom().getItem("magic_cookie");
        if(cookie != null) {
            player.eatCookie();
            player.getCurrentRoom().removeItem(cookie);
        } 
        else {
            System.out.println("No cookie :/");
        }
    }
    /**
     * Print out the current inventory
     * 
     */
    private void items()
    {
        player.itemInventory();
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        
        if (direction.equals("north")) {
            nextRoom = player.getCurrentRoom().getExit("north");
        }
        if(direction.equals("east")) {
            nextRoom = player.getCurrentRoom().getExit("east");
        }
        if(direction.equals("south")) {
            nextRoom = player.getCurrentRoom().getExit("south");
        }
        if(direction.equals("west")) {
            nextRoom = player.getCurrentRoom().getExit("west");
        }
        if(nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            roomPath.push(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
        }
    }
    
    /**
     * Print out location information.
     * @return The current location and options for movement are printed.
     */
    private void printLocationInfo()
    {
        System.out.println("You are " + player.getCurrentRoom().getDescription());
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
