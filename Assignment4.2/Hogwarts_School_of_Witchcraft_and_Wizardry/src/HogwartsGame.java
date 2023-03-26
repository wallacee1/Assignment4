import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * This class is part of the "Hogwarts School of Witchcraft and Wizardry" application.
 *
 * @author Eric Wallace
 * @version 1.0
 */
public class HogwartsGame {
    private List<Room> rooms;
    private Player player;
    private boolean gameOver;

    /**
     * Constructor for a new game.
     *
     */
    public HogwartsGame() {
        rooms = new ArrayList<>();
        generateRooms();
        player = new Player("Harry Potter", rooms.get(0));
        gameOver = false;
    }
    public static void main(String[] args) {
        HogwartsGame game = new HogwartsGame();
        game.play();
    }

    private void generateRooms() {
        Room entranceHall = new Room("Entrance Hall");
        Room greatHall = new Room("Great Hall");
        Room potionsClass = new Room("Potions Class");
        Room charmsClass = new Room("Charms Class");
        Room transfigurationClass = new Room("Transfiguration Class");
        Room library = new Room("Library");
        Room commonRoom = new Room("Common Room");
        Room courtyard = new Room("Courtyard");

        // Add exits to rooms
        entranceHall.setExit("north", greatHall);
        entranceHall.setExit("east", potionsClass);
        greatHall.setExit("south", entranceHall);
        greatHall.setExit("east", charmsClass);
        greatHall.setExit("west", transfigurationClass);
        potionsClass.setExit("west", entranceHall);
        potionsClass.setExit("east", library);
        charmsClass.setExit("west", greatHall);
        transfigurationClass.setExit("east", greatHall);
        library.setExit("west", potionsClass);
        library.setExit("east", commonRoom);
        commonRoom.setExit("west", library);
        commonRoom.setExit("east", courtyard);
        courtyard.setExit("west", commonRoom);

        // Add rooms to list of rooms
        rooms.add(entranceHall);
        rooms.add(greatHall);
        rooms.add(potionsClass);
        rooms.add(charmsClass);
        rooms.add(transfigurationClass);
        rooms.add(library);
        rooms.add(commonRoom);
        rooms.add(courtyard);

        distributeItemsRandomly();
    }

    public void play() {
        System.out.println("Welcome to Hogwarts!");
        System.out.println("You, " + player.getName() + ", have been tasked with finding all the magic items.");
        System.out.println("Explore the castle and collect all the items to win.");

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            Room currentRoom = player.getCurrentRoom();
            System.out.println("\nYou are currently in the " + currentRoom.getLongDescription());
            currentRoom.printExits();

            boolean validInput = false;
            String direction = "";

            while(!validInput) {
                // Get player input
                System.out.print("\nEnter a direction (north, south, east, west) or type 'exit' to quit: ");
                direction = scanner.nextLine().toLowerCase();

                if(isValidDirection(direction) || direction.equals("exit")){
                    validInput = true;
                }
                else {
                    System.out.println("Invalid input. Please enter a valid direction or 'exit'. ");
                }
            }

            if(direction.equals("exit")) {
                System.out.println("Goodbye!");
                scanner.close();
                return;
            }

            // Move to the next room
            player.move(direction);

            // 20% chance of teleporting to a random room
            Random rand = new Random();
            if (rand.nextInt(100) < 20) {
                System.out.println("You've been teleported to a random room!");
                player.setCurrentRoom(getRandomRoom());
            }
            // Check for items in current room
            Item item = currentRoom.checkForItems();
            if (item != null) {
                System.out.println("\nYou found a " + item.getName() + " in the " + currentRoom.getLongDescription());
                player.addItemToInventory(item);
                currentRoom.removeItem(item);
                showInventory();
            }
            // Check if player has reached the goal
            if (hasCollectedAllItems()) {
                System.out.println("\nCongratulations, you have found one of each item!");
                System.out.println("You win!");
                gameOver = true;
            }

        }
    }
    private Room getRandomRoom()
    {
        Random rand = new Random();
        return rooms.get(rand.nextInt(rooms.size()));
    }

    private boolean isValidDirection(String direction)
    {
        return direction.equals("north") || direction.equals("south") || direction.equals("west") || direction.equals("east");
    }

    private void showInventory() {
        System.out.println("\nYour inventory:");
        if (player.getInventorySize() == 0) {
            System.out.println("Empty");
        } else {
            for (Item item : player.getInventory()) {
                System.out.println("- " + item.getName());
            }
        }
    }

    private boolean hasCollectedAllItems() {
        ArrayList<Item> playerInventory = player.getInventory();
        for (Item item : Item.values()) {
            if (!playerInventory.contains(item)) {
                return false;
            }
        }
        return true;
    }
    private void distributeItemsRandomly() {
        Random rand = new Random();

        for (Item item : Item.values()) {
            boolean itemPlaced = false;
            while (!itemPlaced) {
                int roomIndex = rand.nextInt(rooms.size());
                Room room = rooms.get(roomIndex);

                // Check if the room doesn't already have an item
                if (room.checkForItems() == null) {
                    room.addItemToInventory(item);
                    itemPlaced = true;
                }
            }
        }
    }


}