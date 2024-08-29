package com.tictactoe.gamemodule.models;


import com.tictactoe.gamemodule.models.enums.CellState;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size, dimension;
    private List<List<Cell>> board = new ArrayList<>();


    public Board(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i < dimension; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++)
                board.get(i).add(new Cell(i, j));
        }
    }

    public void display() {
        for(List<Cell> row : board) {
            for(Cell cell : row) {
                System.out.print("| " + ((cell.getCellState().equals(CellState.EMPTY)) ? "_" : cell.getPlayer().getSymbol().getChar()) + " | ");
            }
            System.out.println();
        }
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
}
