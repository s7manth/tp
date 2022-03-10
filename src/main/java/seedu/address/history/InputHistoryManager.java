package seedu.address.history;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to record the commands entered by the user
 */
public class InputHistoryManager implements InputHistory {
    private final int INIT_INPUT_INDEX_VALUE = -1;

    private List<String> previousInputs;
    private int currentInputIndex;

    /**
     * Constructs an {@code InputHistoryManager}
     */
    public InputHistoryManager() {
        this.previousInputs = new ArrayList<>();
        this.currentInputIndex = INIT_INPUT_INDEX_VALUE;
    }

    /**
     * Stores the most user input.
     * @param input the user input.
     */
    @Override
    public void storeInput(String input) {
        this.previousInputs.add(input);
        currentInputIndex = refreshInputIndex();
    }

    /**
     * Returns a previous user input.
     */
    @Override
    public String getPreviousUserInput() {
        // Return the first input provided by the user at app launch.
        if (currentInputIndex == INIT_INPUT_INDEX_VALUE) {
            return previousInputs.get(0);
        }

        String previousUserInput = previousInputs.get(currentInputIndex);
        currentInputIndex--;
        return previousUserInput;
    }

    private int refreshInputIndex() {
        return previousInputs.size() - 1;
    }
}
