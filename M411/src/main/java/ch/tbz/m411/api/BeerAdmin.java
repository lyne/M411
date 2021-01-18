package ch.tbz.m411.api;

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
import ch.tbz.m411.beer.Beer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Connection to Api
 *
 * @author Alessio Carcavallo
 * @version 1.0
 * @since 11.01.2021
 */
public class BeerAdmin {
    @Getter
    private Map<String, String> beerStyles = new HashMap<>();
    @Getter
    private Map<String, Beer> beers = new HashMap<>();
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
     * @throws IOException when there's an error reading the response
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
     * @throws JSONException
     */
    public void loadBeerStyles() throws JSONException {
        String apiUrl = "http://api.brewerydb.com/v2/beers";
        String apiKey = "?key=1511d0db4a1d6841481c672455358cff";
        try {
            JSONObject json = readJsonFromUrl(apiUrl + apiKey);
            JSONArray data = json.getJSONArray("data");
            for (Iterator<Object> it = data.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj instanceof JSONObject) {
                    JSONObject beer = (JSONObject) obj;
                    beerStyles.put(beer.getString("id"), beer.getString("name"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBeerStyles() throws JSONException {
        for (String id : beerStyles.keySet()) {
            String name = beerStyles.get(id);
            System.out.println(id + "::" + name);
        }
    }

    public void printBeerStyles(String search) {
        for (String id : beerStyles.keySet()) {
            String name = beerStyles.get(id);
            if (name.toLowerCase().contains(search.toLowerCase()))
                System.out.println(id + "::" + name);
        }

    }

    public void getBeerListForStyle(int idStyle) {
        try {
            String call = "http://api.brewerydb.com/v2/beers?key=1511d0db4a1d6841481c672455358cff&style=" + idStyle;
            JSONObject json = readJsonFromUrl(call);
            JSONArray data = json.getJSONArray("data");
            for (Iterator<Object> it = data.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj instanceof JSONObject) {
                    JSONObject beerObj = (JSONObject)obj;
                    Beer beer = new Beer(beerObj);
                    beers.put(beer.getId(), beer);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}