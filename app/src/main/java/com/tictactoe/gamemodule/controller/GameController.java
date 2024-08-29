package com.tictactoe.gamemodule.controller;


import com.tictactoe.gamemodule.exeption.InvalidMoveException;
import com.tictactoe.gamemodule.models.Game;
import com.tictactoe.gamemodule.models.Player;
import com.tictactoe.gamemodule.models.enums.GameState;
import com.tictactoe.gamemodule.strategies.winningstrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        return Game.getBuilder().setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState gameState(Game game) {
        return game.getGameState();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public void undo(Game game) {

    }
}
