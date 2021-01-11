package ch.tbz.m411.client;

import ch.tbz.m411.exceptions.CancelException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class InteractiveTUI {

    protected BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public InteractiveTUI() {
        
    }

    /**
     * Get a string from the console.
     * Supports IDEs like IntelliJ IDEA or Eclipse.
     *
     * @param cancellable whether the process can be cancelled.
     * @return the user input.
     * @throws CancelException if cancellable is set to true and the user submits "cancel".
     */
    protected String interactive(boolean cancellable) throws CancelException {
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (cancellable && input != null && input.equals("cancel")) throw new CancelException();
        return input;
    }

    /**
     * Get a string from the console which matches an element of the available values.
     *
     * @param cancellable whether the process can be cancelled.
     * @param values      the available values.
     * @return the user input.
     * @throws CancelException if cancellable is set to true and the user submits "cancel".
     */
    protected String interactiveEnum(boolean cancellable, String... values) throws CancelException {
        String input = interactive(cancellable);
        for (String item : values) if (input.equalsIgnoreCase(item)) return item;
        System.out.println("Invalid value, please try again! " + Arrays.toString(values));
        System.out.print("> ");
        return interactiveEnum(cancellable, values);
    }

}
