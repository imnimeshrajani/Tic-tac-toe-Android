package com.tictactoe.gpt;

public class GameRules {

    private final int size;

    public GameRules(int size) {
        this.size = size;
    }

    public boolean checkWin(Board board, char symbol) {
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (checkRow(board, i, symbol) || checkColumn(board, i, symbol)) {
                return true;
            }
        }
        // Check diagonals
        return checkMainDiagonal(board, symbol) || checkAntiDiagonal(board, symbol);
    }

    private boolean checkRow(Board board, int row, char symbol) {
        for (int i = 0; i < size; i++) {
            if (board.getCell(row, i) != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(Board board, int col, char symbol) {
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, col) != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkMainDiagonal(Board board, char symbol) {
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, i) != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiagonal(Board board, char symbol) {
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, size - i - 1) != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDraw(Board board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
