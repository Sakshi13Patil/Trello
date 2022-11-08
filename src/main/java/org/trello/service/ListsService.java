package org.trello.service;

import org.trello.constants.PrivacyEnum;
import org.trello.entity.Board;
import org.trello.entity.Lists;
import org.trello.entity.User;
import org.trello.exceptions.TrelloException;
import org.trello.repository.TrelloRepository;

import java.util.Optional;
import java.util.stream.Collectors;

public class ListsService {

    private final TrelloRepository trelloRepository;
    public ListsService(TrelloRepository trelloRepository) {
        this.trelloRepository = trelloRepository;
    }

    public void showLists(String id) {
        Optional<Lists> lists = trelloRepository.getListsById(id);
        System.out.println(lists.isPresent() ? lists.get() : "List " + id + " does not exist");
    }

    public void deleteLists(String id) {
        Optional<Lists> lists = trelloRepository.getListsById(id);
        if (lists.isPresent()) {
            trelloRepository.remove(lists.get());
        } else
            System.out.println("List " + id + " does not exist");
    }

    public void create(String boardId, String listName) {
        Lists listObj = new Lists(listName);
        trelloRepository.addList(boardId,listObj);
        System.out.println("Created list: " + listObj.getId());
    }
}
