package org.trello.entity;

import lombok.Data;

@Data
public class User {

    private String id;
    private String name;
    private String email;

    public User(String email) {
        generateId();
        this.email = email;
    }

    private void generateId() {
        this.id = "USER_" + System.currentTimeMillis();
    }

}
