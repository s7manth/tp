package seedu.address.history;

/**
 * API of the InputHistory component
 */
public interface InputHistory {
    /**
     * Stores the input entered by the user.
     * @param input the user input.
     */
    void storeInput(String input);

    /**
     * Returns the most recent input by the user.
     */
    String getLastUserInput();

}
