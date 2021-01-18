package ch.tbz.m411.client;

import ch.tbz.m411.api.BeerAdmin;
import ch.tbz.m411.exceptions.CancelException;

/**
 * The main navigation client for the user.
 */
public class Navigation extends InteractiveTUI {

    private final BeerAdmin admin;

    public Navigation(BeerAdmin admin) {
        this.admin = admin;
    }

    /**
     * Starts the main menu.
     */
    public void menu() {
        try {
            System.out.println("========== MAIN MENU ==========");
            System.out.println("ls) List beer styles");
            System.out.println("ss) Search beer styles");
            System.out.println("lb) List beers in local cache");
            System.out.println("vb) View beer in local cache");
            System.out.println("lr) List breweries");
            System.out.println("x) Exit");
            System.out.print("> ");

            String action = interactive(false);
            switch (action) {
                case "ls":
                    System.out.println("========== LIST BEER STYLES ==========");
                    admin.printBeerStyles();
                    break;
                case "ss":
                    System.out.println("========== SEARCH BEER STYLES ==========");
                    System.out.println("Search > ");
                    String search = interactive(true);
                    admin.printBeerStyles(search);
                    break;
                case "lb":
                    // TODO: List beers in local cache
                    break;
                case "vb":
                    // TODO: View beer in local cache
                    break;
                case "lr":
                    // TODO: List breweries in local cache
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
