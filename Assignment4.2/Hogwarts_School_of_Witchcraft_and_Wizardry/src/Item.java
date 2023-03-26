/**
 * This class is part of the "Hogwarts School of Witchcraft and Wizardry" application.
 *
 * @author Eric Wallace
 * @version 1.0
 */
public enum Item {
    WAND("Wand"),
    POTION("Potion"),
    SPELLBOOK("Spellbook"),
    ANTIDOTE("Antidote"),
    BROOM("Broom");

    private final String name;

    Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


