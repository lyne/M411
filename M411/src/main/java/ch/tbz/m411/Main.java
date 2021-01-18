package ch.tbz.m411;

import ch.tbz.m411.api.BeerAdmin;

public class Main {

    public static void main(String[] args) {
        BeerAdmin admin = new BeerAdmin();
        admin.loadBeerStyles();
    }

}
