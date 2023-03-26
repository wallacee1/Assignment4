import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private final int COMMAND_LIMIT = 5;
    private int commandCount;
    private Room teleportRoom;
    private boolean hasKey;
    private HashMap<String, Room> rooms;
    private HashMap<CommandWord, Function<Command, Boolean>> functions;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        rooms = new HashMap<>();
        createRooms();
        parser = new Parser();
        commandCount = 0;
        hasKey = false;
        functions = new HashMap<>();
        createFunctions();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
      
        // create the rooms
        rooms.put("outside", new Room("outside the main entrance of the university"));
        rooms.put("theater", new Room("in a lecture theater"));
        rooms.put("pub", new Room("in the campus pub"));
        rooms.put("lab", new Room("in a computing lab"));
        rooms.put("office", new Room("in the computing admin office"));
        rooms.put("transporter", new Room("You are inside the " + "transporter room.\n The walls are shaking. " + "You are transported to a random room."));
        
        // initialise room exits
        rooms.get("outside").setExit("east", rooms.get("theater"));
        rooms.get("outside").setExit("south", rooms.get("lab"));
        rooms.get("outside").setExit("west", rooms.get("pub"));
        rooms.get("outside").setExit("north", rooms.get("transporter"));
    
        rooms.get("theater").setExit("west", rooms.get("outside"));
    
        rooms.get("pub").setExit("east", rooms.get("outside"));
    
        rooms.get("lab").setExit("east", rooms.get("office"));
    
        rooms.get("office").setExit("west", rooms.get("lab"));
    
        currentRoom = rooms.get("outside");  // start game outside
    }
    
    public void createFunctions() 
    {
        functions.put(CommandWord.UNKNOWN, (Command command) -> {
            System.out.println("I don't know what you mean...");
            return false;
        });
        functions.put(CommandWord.HELP, (Command command)->{
            printHelp();
            return false;
        });
        functions.put(CommandWord.GO, (Command command)->{
            goRoom(command);
            return false;
        });
        functions.put(CommandWord.QUIT, (Command command)->{
            return quit(command);
        });
        functions.put(CommandWord.TALK, (Command command)->{
            return talk(command);
        });
        functions.put(CommandWord.GIVE, (Command command)->{
            return give(command);
        });
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
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println("You need to reach the office in 5 commands!");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
            
            case LOOK:
                look();
                break;
                
            case TALK:
                talk(command);
                break;
            
            case GIVE:
                give(command);
                break;
            case MOVE:
                moveCharacters();
                return wantToQuit;
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
        parser.showCommands();
    }
    
    private void createCharacters()
    {
        Item johnReward = new Item("cake", "Chocolate cake", 5);
        Character john = new Character("John", "Man without a hat.", "Give him a hat.", "hat", johnReward, character.getCurrentRoom());
        characters.add(john);
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
    
        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);
    
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            if(currentRoom.equals(rooms.get("lab"))){
                if(!hasKey){
                    System.out.println("The door to the lab is locked" + " . Find a key!");
                    currentRoom = rooms.get("outside");
                } else {
                    System.out.println("You unlock the doors with a key.");
                }
            } else if(currentRoom.equals(rooms.get("transporter"))) {
                System.out.println(currentRoom.getShortDescription());
                Random rng = new Random();
                List<String> randomRoomRooster = rooms.keySet().stream().filter(room -> !room.equals("transporter")).collect(Collectors.toList());
                currentRoom = rooms.get(randomRoomRooster.get(rng.nextInt(randomRoomRooster.size())));
            }
            System.out.println(currentRoom.getLongDescription());
    
            if(currentRoom.equals(rooms.get("pub"))){
                if(!hasKey) {
                    System.out.println("You have found a key!");
                    hasKey = true;
                }
            }      
        }
    }

    /**
     * Look around the room to see where you are.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Charge the teleport device
     */
    private void charge()
    {
        teleportRoom = currentRoom;
        System.out.println("Teleport charged!");
    }
    
    /**
     * Teleport to a room the device has been charged in
     */
    private void teleport() {
        if(teleportRoom != null){
            currentRoom = teleportRoom;
            System.out.println("Teleport fired!");
            System.out.println(currentRoom.getLongDescription());
        }
        else {
            System.out.println("The device was not charged!");
        }
    }
    
    private void moveCharacters()
    {
        Random rand = new Random();
        Room current = null;
        for(Characters c : characters) {
            if(rand.nextDouble() > 0.5) {
                current = c.getCurrentRoom();
                String[] neighbors = current.getExitDirections();
                int r = rand.nextInt(neighbors.length);
                c.setCurrentRoom(current.getExit(neighbors[r]));
                current.removeCharacter(c);
            }
        }
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
