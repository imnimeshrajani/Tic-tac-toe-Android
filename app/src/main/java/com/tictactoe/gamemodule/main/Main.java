package com.tictactoe.gamemodule.main;


import com.tictactoe.gamemodule.controller.GameController;
import com.tictactoe.gamemodule.exeption.InvalidMoveException;
import com.tictactoe.gamemodule.models.Bot;
import com.tictactoe.gamemodule.models.Game;
import com.tictactoe.gamemodule.models.Player;
import com.tictactoe.gamemodule.models.Symbol;
import com.tictactoe.gamemodule.models.enums.BotDifficultyLevel;
import com.tictactoe.gamemodule.models.enums.GameState;
import com.tictactoe.gamemodule.models.enums.PlayerType;
import com.tictactoe.gamemodule.strategies.winningstrategies.ColumnWinningStrategy;
import com.tictactoe.gamemodule.strategies.winningstrategies.DiagonalWinningStrategy;
import com.tictactoe.gamemodule.strategies.winningstrategies.RowWinningStrategy;
import com.tictactoe.gamemodule.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("GAME STARTS");
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        //int dimension = scanner.nextInt();
        int dimension = 3;

        //Take players information in the input.
        List<Player> players = new ArrayList<>();
        players.add(
                new Player("Nimesh" , new Symbol('X'), PlayerType.HUMAN)
        );

        players.add(
                new Bot("Brijesh", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        Game game = gameController.startGame(dimension, players, winningStrategies);

        //gameController.printBoard(game);
        //Let's play the game.

        while (gameController.gameState(game).equals(GameState.IN_PROGRESS)) {
            //1. Show the board.
            //2. make a move.

            gameController.printBoard(game);

            System.out.println("Do you want to undo ? y/n");
            String isUndo = scanner.next();

            if (isUndo.equalsIgnoreCase("y")) {
                //make an undo operation
                gameController.undo(game);
                continue;
            }

            try {
                gameController.makeMove(game);
            } catch (InvalidMoveException e) {
                throw new RuntimeException(e);
            }
        }

        gameController.printBoard(game);
        if (gameController.gameState(game).equals(GameState.ENDED)) {
            System.out.println(gameController.getWinner(game).getName() + " has won the game.");
        } else {
            System.out.println("GAME DRAW");
        }
    }
}
