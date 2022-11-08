package org.trello.service;

import org.trello.constants.PrivacyEnum;
import org.trello.entity.Board;
import org.trello.entity.User;
import org.trello.exceptions.TrelloException;
import org.trello.repository.TrelloRepository;

import java.util.Optional;
import java.util.stream.Collectors;

public class BoardService {

    private final TrelloRepository trelloRepository;

    public BoardService(TrelloRepository trelloRepository) {
        this.trelloRepository = trelloRepository;
    }


    public void createBoard(String name) {
        Board board = new Board(name);
        trelloRepository.addBoard(board);
        System.out.println("Created board: " + board.getId());
    }

    public void update(String id, String key, String value) {
        Optional<Board> board = trelloRepository.getBoardById(id);
        if (board.isPresent()) {
            if (key.equals("NAME"))
                board.get().setName(value);
            else if (key.equals("PRIVACY"))
                board.get().setPrivacy(PrivacyEnum.valueOf(value.toUpperCase()));
            else
                throw new TrelloException("Cannot change attribute" + key);
            System.out.println(board.get());
        } else
            System.out.println("Board " + id + " does not exist");
    }

    public void addMember(String id, String email) {
        Board board = trelloRepository.addUserToBoard(id, email);
        System.out.println(board);
    }


    public void removeMember(String id, String email) {
        Optional<Board> board = trelloRepository.getBoardById(id);
        if (board.isPresent()) {
            board.get().setMembers(board.get().getMembers().stream().filter(obj -> !obj.getEmail().equals(email)).collect(Collectors.toList()));
            System.out.println(board);
        } else
            System.out.println("Board " + id + " does not exist");
    }

    public void deleteBoard(String id) {
        Optional<Board> board = trelloRepository.getBoardById(id);
        if (board.isPresent()) {
            trelloRepository.remove(board.get());
        } else
            System.out.println("Board " + id + " does not exist");
    }

    public void showBoard(String id) {
        Optional<Board> board = trelloRepository.getBoardById(id);
        System.out.println(board.isPresent() ? board.get() : "Board " + id + " does not exist");
    }

    public void showAllBoards() {
        System.out.println(trelloRepository.getAllBoards());
    }
}
