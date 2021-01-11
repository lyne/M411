package ch.tbz.m411.client;

import ch.tbz.m411.exceptions.CancelException;

/**
 * The main navigation client for the user.
 */
public class Navigation extends InteractiveTUI {

    public Navigation() {
        menu();
    }

    /**
     * Starts the main menu.
     */
    public void menu() {
        try {
            System.out.println("========== MAIN MENU ==========");
            System.out.println("t) Search for text");
            System.out.println("l) Search locals");
            System.out.println("x) Exit");
            System.out.print("> ");

            String action = interactive(false);
            switch (action) {
                case "t":
                    // TODO: Search for text
                    break;
                case "l":
                    // TODO: Search locals
                    break;
                case "x":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid selection! Please retry.");
                    break;
            }
            menu();
        } catch (CancelException e) {
            // An action has been cancelled.
            menu();
        }
    }


}
