package ch.tbz.m411;

import ch.tbz.m411.api.BeerAdmin;
import ch.tbz.m411.client.Navigation;

public class Main {

    public static void main(String[] args) {
        BeerAdmin admin = new BeerAdmin();
        System.out.println("Loading beer styles and breweries...");
        admin.loadBeerStyles();
        admin.getBreweries();
        Navigation navigation = new Navigation(admin);
        navigation.menu();
    }

}
