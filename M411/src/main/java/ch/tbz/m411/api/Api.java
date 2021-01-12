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

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Connection to Api
 *
 * @author  Alessio Carcavallo
 * @version 1.0
 * @since   11.01.2021
 */
public class Api {
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

    public static void jsonToMap(String t) throws JSONException {

        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = new JSONObject(t);
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key);
            map.put(key, value);

        }

        System.out.println("json : "+jObject);
        System.out.println("map : "+map);
    }

    /**
     * Example of Call
     * @param args
     * @throws IOException
     * @throws JSONException
    */
    public static void main(String[] args) throws IOException, JSONException {
        String apiUrl = "http://api.brewerydb.com/v2/beers";
        String apiKey = "?key=1511d0db4a1d6841481c672455358cff";
        String styleId = "&styleId=5";

        System.out.println(apiUrl + apiKey + styleId);
        JSONObject json = readJsonFromUrl(apiUrl + apiKey + styleId);
        System.out.println(json.toString());
        jsonToMap("{\"test1\":\"1\",\"test2\":\"2\"}");
    }
}