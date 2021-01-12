package ch.tbz.m411.beer;

import lombok.Getter;

public class Beer {

    @Getter
    private final String id;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final int idStyle;

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
