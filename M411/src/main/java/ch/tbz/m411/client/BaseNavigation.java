package ch.tbz.m411.client;

import ch.tbz.m411.api.BeerAdmin;
import ch.tbz.m411.exceptions.CancelException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public abstract class BaseNavigation {

    protected final BeerAdmin admin;

    protected BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public BaseNavigation(BeerAdmin admin) {
        this.admin = admin;
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
     * Get an integer from the console.
     * Supports IDEs like IntelliJ IDEA or Eclipse.
     *
     * @param cancellable whether the process can be cancelled.
     * @return the user input.
     * @throws CancelException if cancellable is set to true and the user submits "cancel".
     */
    protected int interactiveInt(boolean cancellable) throws CancelException {
        String input = interactive(cancellable);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please try again and enter a valid integer.");
            System.out.print("> ");
            return interactiveInt(cancellable);
        }
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

    /**
     * Get a beer style id from the console.
     * Supports IDEs like IntelliJ IDEA or Eclipse.
     *
     * @param cancellable whether the process can be cancelled.
     * @return the user input.
     * @throws CancelException if cancellable is set to true and the user submits "cancel".
     */
    protected String interactiveBeerStyleId(boolean cancellable) throws CancelException {
        String input = interactive(cancellable);
        if (admin.getBeerStyles().containsKey(input)) return input;
        System.out.println("Could not find beer style, please try again.");
        System.out.print("> ");
        return interactiveBeerStyleId(cancellable);
    }

    /**
     * Get a beer style id from the console.
     * Supports IDEs like IntelliJ IDEA or Eclipse.
     *
     * @param cancellable whether the process can be cancelled.
     * @return the user input.
     * @throws CancelException if cancellable is set to true and the user submits "cancel".
     */
    protected String interactiveBeerId(boolean cancellable) throws CancelException {
        String input = interactive(cancellable);
        if (admin.getBeers().containsKey(input)) return input;
        System.out.println("Could not find beer, please try again.");
        System.out.print("> ");
        return interactiveBeerId(cancellable);
    }

}
