package com.tictactoe.gamemodule.strategies.botplayingstrategies;

import com.tictactoe.gamemodule.models.Board;
import com.tictactoe.gamemodule.models.Cell;
import com.tictactoe.gamemodule.models.Move;
import com.tictactoe.gamemodule.models.enums.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(
                            null,
                            cell
                    );
                }
            }
        }
        return null;
    }
}
