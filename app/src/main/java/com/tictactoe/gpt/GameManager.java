package com.tictactoe.gpt;

public class GameManager {
    private final Player player1;
    private final Player player2;
    private final Board board;
    private final GameRules gameRules;
    private Player currentPlayer;

    public GameManager(Player player1, Player player2, Board board, GameRules gameRules) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.gameRules = gameRules;
        this.currentPlayer = player1;
    }

    public boolean playMove(int row, int col) {
        return board.markCell(row, col, currentPlayer.getSymbol());
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
