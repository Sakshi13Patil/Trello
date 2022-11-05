package org.trello.entity;

import lombok.Data;
import org.trello.constants.PrivacyEnum;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {

    private String id;
    private String name;
    private PrivacyEnum privacy = PrivacyEnum.PUBLIC;
    private String url;
    private List<User> members = new ArrayList<>();
    private List<Lists> lists=new ArrayList<>();

    public Board(String name) {
        generateIdAndURL();
        this.name = name;
    }

    private void generateIdAndURL() {
        this.id = "BOARD_" + System.currentTimeMillis();
        this.url = "http://localhost:8080/" + id.toLowerCase();
    }


}
