package ch.tbz.m411.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Connection to Api
 *
 * @author  Alessio Carcavallo
 * @version 1.0
 * @since   11.01.2021
 */
public class BeerAdmin {
    /**
     * Method for lopping trough
     * @param rd
     * @return
     * @throws IOException
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
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
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

    public static HashMap<String, String> loadBeerStyles() throws JSONException {

        String apiUrl = "http://api.brewerydb.com/v2/beers";
        String apiKey = "?key=1511d0db4a1d6841481c672455358cff";
        HashMap<String, String> beerstyles = new HashMap<String, String>();
        try {
            JSONObject json = readJsonFromUrl(apiUrl + apiKey);
            JSONArray data = json.getJSONArray("data");
            for (Iterator<Object> it = data.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj instanceof JSONObject) {
                    JSONObject beer = (JSONObject)obj;
                    beerstyles.put(beer.getString("id"), beer.getString("name"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beerstyles;
    }

    public static void printBeerStyles() throws JSONException {
        Map<String, String> beerstyles = loadBeerStyles();
        for (String id:beerstyles.keySet()) {
            String name = beerstyles.get(id);
            System.out.println(id + "::" + name);
        }
    }

    public static void printBeerStyles(String search) {

    }

    public static void getBeerListForStyle(int idStyle) {

    }

    /**
     * Example of Call
     * @param args
     * @throws IOException
     * @throws JSONException

    public static void main(String[] args) throws IOException, JSONException {

    }
    */
}