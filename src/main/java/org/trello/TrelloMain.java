package org.trello;

import org.trello.service.TrelloManager;

import java.util.Scanner;

public class TrelloMain {
    static final TrelloManager trelloManager = new TrelloManager();

    public static void main(String[] args) {
        System.out.println("Welcome to Trello!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("EXIT"))
                break;
            trelloManager.performOperation(input);
        }
    }
}