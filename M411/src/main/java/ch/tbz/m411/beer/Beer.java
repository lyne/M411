package ch.tbz.m411.beer;

import lombok.Getter;
import org.json.JSONObject;

public class Beer {

    @Getter
    private final String id;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final int idStyle;

    public Beer(JSONObject obj) {
        this.id = obj.getString("id");
        this.name = obj.getString("name");
        this.description = obj.has("description") ? obj.getString("description") : null;
        this.idStyle = obj.has("styleId") ? obj.getInt("styleId") : -1;
    }

    public Beer(String id, String name, String description, int idStyle) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idStyle = idStyle;
    }

    @Override
    public String toString() {
        return id + ';' +
                name + ';' +
                description + ';' +
                idStyle;
    }
}
