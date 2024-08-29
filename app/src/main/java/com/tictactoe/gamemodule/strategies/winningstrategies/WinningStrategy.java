package com.tictactoe.gamemodule.strategies.winningstrategies;

import com.tictactoe.gamemodule.models.Board;
import com.tictactoe.gamemodule.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
