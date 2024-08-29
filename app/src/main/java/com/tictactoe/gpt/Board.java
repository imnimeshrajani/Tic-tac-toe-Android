package com.tictactoe.gpt;

public class Board {
    private final char[][] grid;
    private final int size;

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public boolean markCell(int row, int col, char symbol) {
        if (grid[row][col] == ' ') {
            grid[row][col] = symbol;
            return true;
        }
        return false;
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }
}
