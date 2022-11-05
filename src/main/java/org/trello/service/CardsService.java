package org.trello.service;

import org.trello.constants.PrivacyEnum;
import org.trello.entity.Board;
import org.trello.entity.Cards;
import org.trello.entity.Lists;
import org.trello.entity.User;
import org.trello.exceptions.TrelloException;
import org.trello.repository.TrelloRepository;

import java.util.Optional;

public class CardsService {

    private final TrelloRepository trelloRepository;

    public CardsService(TrelloRepository trelloRepository) {
        this.trelloRepository = trelloRepository;
    }

    public void showCards(String id) {
        Optional<Cards> cards = trelloRepository.getCardsById(id);
        System.out.println(cards.isPresent() ? cards : "Card " + id + " does not exist");
    }

    public void deleteCards(String id) {
        Optional<Cards> card = trelloRepository.getCardsById(id);
        if (card.isPresent()) {
            trelloRepository.remove(card.get());
        } else
            System.out.println("Card " + id + " does not exist");
    }

    public void create(String listId, String emailId) {
        Cards card = new Cards();
        if (emailId != null) {
            User user = new User(emailId);
            card.setAssignedUser(user);
        }
        trelloRepository.addCardToList(card,listId);
        System.out.println("Created Card: " + card.getId());
    }

    public void update(String id, String key, String value) {
        Optional<Cards> card = trelloRepository.getCardsById(id);
        if (card.isPresent()) {
            if (key.equals("NAME"))
                card.get().setName(value);
            else if (key.equals("DESCRIPTION"))
                card.get().setDescription(value);
            else
                throw new TrelloException("Cannot change attribute" + key);
            System.out.println(card);
        } else
            System.out.println("Card " + id + " does not exist");
    }

    public void assign(String cardId, String userId) {
        Optional<Cards> card = trelloRepository.getCardsById(cardId);
        if (card.isPresent()) {
            Optional<User> user = trelloRepository.getUserById(userId);
            if (user.isPresent())
                card.get().setAssignedUser(user.get());
            else
                throw new TrelloException("Cannot Assign as user doesnt exist " + userId);
            System.out.println(card);
        } else
            System.out.println("Card " + cardId + " does not exist");
    }

    public void unassign(String cardId) {
        Optional<Cards> card = trelloRepository.getCardsById(cardId);
        if (card.isPresent()) {
            card.get().setAssignedUser(null);
            System.out.println(card);
        } else
            System.out.println("Card " + cardId + " does not exist");
    }


    public void move(String listId , String cardId) {
        Optional<Cards> card = trelloRepository.getCardsById(cardId);
        if (card.isPresent()) {
            Optional<Lists> list = trelloRepository.getListByCard(card.get());
            list.ifPresent(lists -> lists.getCards().remove(card.get()));
            trelloRepository.addCardToList(card.get(),listId);
            System.out.println(card);
        } else
            System.out.println("Card " + cardId + " does not exist");
    }
}
