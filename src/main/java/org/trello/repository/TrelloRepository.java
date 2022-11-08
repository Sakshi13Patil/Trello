package org.trello.repository;

import org.trello.entity.Board;
import org.trello.entity.Cards;
import org.trello.entity.Lists;
import org.trello.entity.User;
import org.trello.exceptions.TrelloException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TrelloRepository {
    List<Board> boardList = new ArrayList<>();
    List<Lists> lists = new ArrayList<>();
    List<Cards> cards = new ArrayList<>();
    List<User> users = new ArrayList<>();

    public void addBoard(Board b) {
        boardList.add(b);
    }

    public Optional<Board> getBoardById(String id) {
        return boardList.stream().filter(obj -> obj.getId().equals(id)).findFirst();
    }

    public Optional<User> getUserById(String id) {
        return users.stream().filter(obj -> obj.getId().equals(id)).findFirst();
    }

    public Optional<User> getUserByEmail(String email) {
        return users.stream().filter(obj -> obj.getEmail().equals(email)).findFirst();
    }
    public List<Board> getAllBoards() {
        return boardList;
    }

    public void remove(Board board) {
        boardList.remove(board);
        List<Lists> listsToBeDeleted = board.getLists();
        List<List<Cards>> cardsToBeDeleted = listsToBeDeleted.stream().map(Lists::getCards).toList();
        lists.removeAll(listsToBeDeleted);
        cardsToBeDeleted.forEach(a -> cards.removeAll(a));
    }

    public void remove(Lists listObj) {
        lists.remove(listObj);
        cards.removeAll(listObj.getCards());
    }

    public void remove(Cards card) {
        cards.remove(card);
    }


    public Optional<Lists> getListsById(String id) {
        return lists.stream().filter(obj -> obj.getId().equals(id)).findFirst();
    }

    public Optional<Cards> getCardsById(String id) {
        return cards.stream().filter(obj -> obj.getId().equals(id)).findFirst();
    }

    public void addList(String boardId, Lists listObj) {
        Optional<Board> board = getBoardById(boardId);
        if (board.isPresent()) {
            lists.add(listObj);
            board.get().getLists().add(listObj);
        } else
            throw new TrelloException("No Board present to create list for id " + boardId);
    }

    private void addCard(Cards card) {
        cards.add(card);
        if (card.getAssignedUser() != null)
            users.add(card.getAssignedUser());
    }

    public Board addUserToBoard(String id, String email) {
        Optional<Board> board = getBoardById(id);
        if (board.isPresent()) {
            Optional<User> user = getUserByEmail(email);
            if (user.isPresent())
                board.get().getMembers().add(user.get());
            else {
                User newUser =new User(email);
                users.add(newUser);
                board.get().getMembers().add(newUser);
            }
            return board.get();
        }
        else
            throw new TrelloException("Board "+id+" is not present");
    }

    public Optional<Lists> getListByCard(Cards card) {
        return lists.stream().filter(obj -> obj.getCards().contains(card)).findFirst();
    }

    public void addCardToList(Cards card, String listId) {
        Optional<Lists> listObj = getListsById(listId);
        if (listObj.isPresent()) {
            listObj.get().getCards().add(card);
            if (getCardsById(card.getId()).isEmpty())
                addCard(card);
        }
    }
}
