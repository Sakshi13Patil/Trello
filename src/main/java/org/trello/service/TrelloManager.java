package org.trello.service;

import org.trello.exceptions.TrelloException;
import org.trello.repository.TrelloRepository;

import java.util.Collections;
import java.util.List;

public class TrelloManager {

    private final TrelloRepository trelloRepository = new TrelloRepository();
    private final BoardService boardService = new BoardService(trelloRepository);
    private final ListsService listsService = new ListsService(trelloRepository);
    private final CardsService cardsService = new CardsService(trelloRepository);

    private final static List<String> boardInitiators = Collections.singletonList("BOARD");
    private final static List<String> cardInitiators = Collections.singletonList("CARD");
    private final static List<String> listsInitiators = Collections.singletonList("LIST");

    public void performOperation(String input) {
        String[] inputParameters = input.split(" ");
        if (boardInitiators.contains(inputParameters[0]) || input.equals("SHOW") || boardInitiators.contains(inputParameters[1]))
            boardActivity(inputParameters);
        else if (cardInitiators.contains(inputParameters[0]) || cardInitiators.contains(inputParameters[1]))
            cardActivity(inputParameters);
        else if (listsInitiators.contains(inputParameters[0]) || listsInitiators.contains(inputParameters[1]))
            listsActivity(inputParameters);
    }

    public void boardActivity(String[] input) {
        if (input.length < 3)
            boardService.showAllBoards();
        else if (input[1].equals("CREATE"))
            boardService.createBoard(input[2]);
        else if (input[1].equals("DELETE"))
            boardService.deleteBoard(input[2]);
        else if (input.length == 3)
            boardService.showBoard(input[2]);
        else if (input[2].equals("NAME") || input[2].equals("PRIVACY"))
            boardService.update(input[1], input[2], input[3]);
        else if (input[2].equals("ADD_MEMBER"))
            boardService.addMember(input[1], input[3]);
        else if (input[2].equals("REMOVE_MEMBER"))
            boardService.removeMember(input[1], input[3]);
        else
            throw new TrelloException("Unidentified Command");
    }

    public void listsActivity(String[] input) {
        if (input[1].equals("CREATE"))
            listsService.create(input[2], input[3]);
        else if (input[1].equals("DELETE"))
            listsService.deleteLists(input[2]);
        else if (input[0].equals("SHOW"))
            listsService.showLists(input[2]);
        else
            throw new TrelloException("Unidentified Command");
    }

    public void cardActivity(String[] input) {
        if (input[1].equals("CREATE"))
            cardsService.create(input[2], input[3]);
        else if (input[1].equals("DELETE"))
            cardsService.deleteCards(input[2]);
        else if (input[1].equals("ASSIGN"))
            cardsService.assign(input[2], input[3]);
        else if (input[1].equals("UNASSIGN"))
            cardsService.unassign(input[2]);
        else if (input[1].equals("MOVE"))
            cardsService.move(input[2], input[3]);
        else if (input[0].equals("SHOW"))
            cardsService.showCards(input[1]);
        else
            throw new TrelloException("Unidentified Command");
    }
}
