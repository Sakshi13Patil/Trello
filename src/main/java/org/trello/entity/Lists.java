package org.trello.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Lists {

    private String id;
    private String name;
    private List<Cards> cards = new ArrayList<>();

    public Lists(String name) {
        generateId();
        this.name = name;
    }

    private void generateId() {
        this.id= "LIST_" + System.currentTimeMillis();
    }

}
