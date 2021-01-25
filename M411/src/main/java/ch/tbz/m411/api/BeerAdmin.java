package ch.tbz.m411.api;

import ch.tbz.m411.beer.Beer;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Connection to Api
 *
 * @author Alessio Carcavallo
 * @version 1.0
 * @since 11.01.2021
 */
public class BeerAdmin {

    public static final String API_URL = "http://api.brewerydb.com/v2/";
    public static final String API_KEY = "?key=1511d0db4a1d6841481c672455358cff";

    @Getter
    private Map<String, String> beerStyles = new HashMap<>();
    @Getter
    private Map<String, Beer> beers = new HashMap<>();
    @Getter
    private Map<String, String> breweries = new HashMap<>();

    /**
     * Method for lopping trough
     *
     * @param rd
     * @return The StringBuilder toString()
     * @throws IOException when there's an error reading the response
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Method to read from BufferedReader
     *
     * @param url
     * @return
     * @throws IOException   when there's an error reading the response
     * @throws JSONException when there's an invalid body returned
     */
    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    /**
     * Method to load all Beer Styles
     *
     * @throws JSONException when there's an invalid body returned
     */
    public void loadBeerStyles() throws JSONException {
        String call = API_URL + "/beers";
        try {
            JSONObject json = readJsonFromUrl(call + API_KEY);
            JSONArray data = json.getJSONArray("data");
            for (Iterator<Object> it = data.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj instanceof JSONObject) {
                    JSONObject beerStyle = (JSONObject) obj;
                    beerStyles.put(beerStyle.getString("id"), beerStyle.getString("name"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to print all beer styles
     *
     * @throws JSONException check if invalid body returned
     */
    public void printBeerStyles() throws JSONException {
        for (String id : beerStyles.keySet()) {
            String name = beerStyles.get(id);
            System.out.println(id + "::" + name);
        }
    }

    /**
     * Method for printing beer styles with param search
     *
     * @param search
     */
    public void printBeerStyles(String search) {
        for (String id : beerStyles.keySet()) {
            String name = beerStyles.get(id);
            if (name.toLowerCase().contains(search.toLowerCase()))
                System.out.println(id + "::" + name);
        }
    }

    /**
     * Method for getting specific beer list with param id
     *
     * @param idStyle
     */
    public void getBeerListForStyle(int idStyle) {
        try {
            String call = API_URL + "/beers" + API_KEY + "&style=" + idStyle;
            JSONObject json = readJsonFromUrl(call);
            JSONArray data = json.getJSONArray("data");
            for (Iterator<Object> it = data.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj instanceof JSONObject) {
                    JSONObject beerObj = (JSONObject) obj;
                    Beer beer = new Beer(beerObj);
                    beers.put(beer.getId(), beer);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for printing beer list to console
     *
     */
    public void printBeerList() {
        for (String id : beers.keySet()) {
            Beer beer = beers.get(id);
            System.out.println(id + "::" + beer.getName());
        }
    }

    /**
     * Method for printing specific beer with param id
     *
     * @param id
     */
    public void printBeer(String id) {
        Beer beer = beers.get(id);
        System.out.println(beer.getId() + "::" + beer.getName());
        System.out.println(beer.getDescription());
    }

    /**
     * Method which calls the api for all breweries
     *
     */
    public void getBreweries() {
        try {
            String call = API_URL + "/breweries" + API_KEY;
            JSONObject json = readJsonFromUrl(call);
            JSONArray data = json.getJSONArray("data");
            for (Iterator<Object> it = data.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj instanceof JSONObject) {
                    JSONObject brewery = (JSONObject) obj;
                    breweries.put(brewery.getString("id"), brewery.getString("name"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for printing all Breweries to the console
     *
     */
    public void printBreweries() {
        for (String id : breweries.keySet()) {
            String name = breweries.get(id);
            System.out.println(id + "::" + name);
        }
    }
}