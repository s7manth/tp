package seedu.address.history;

/**
 * A class to record the commands entered by the user
 */
public class InputHistoryManager implements InputHistory {
    String lastUserInput;

    /**
     * Constructs an {@code InputHistoryManager}
     */
    public InputHistoryManager() {
        this.lastUserInput = "";
    }

    /**
     * Stores the most user input.
     * @param input the user input.
     */
    @Override
    public void storeInput(String input) {
        this.lastUserInput = input;
    }

    /**
     * Returns the last user input.
     */
    @Override
    public String getLastUserInput() {
        return this.lastUserInput;
    }
}
