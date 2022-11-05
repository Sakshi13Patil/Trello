package org.trello.entity;

import lombok.Data;

@Data
public class Cards {

    private String id;
    private String name;
    private String description;
    private User assignedUser;

    public Cards() {
        generateId();
    }

    private void generateId() {
        this.id = "CARD_" + System.currentTimeMillis();
    }

}
