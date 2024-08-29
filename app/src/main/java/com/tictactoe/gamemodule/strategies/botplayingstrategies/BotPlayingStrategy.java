package com.tictactoe.gamemodule.strategies.botplayingstrategies;


import com.tictactoe.gamemodule.models.Board;
import com.tictactoe.gamemodule.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
