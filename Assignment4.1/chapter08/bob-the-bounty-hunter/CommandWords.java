/**
 * This class is part of the "Bob the Bounty Hunter" application. 
 * "Bob the Bounty Hunter" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Eric Wallace
 * @version 1
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "cuff", "back", "take", "drop"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     *  Print all valid commands to System.out.
     */
    public String getCommandWords()
    {
        String commandWords = " ";
        for(String command : validCommands) {
            commandWords += command + " ";
        }
        return commandWords;
    }
}
