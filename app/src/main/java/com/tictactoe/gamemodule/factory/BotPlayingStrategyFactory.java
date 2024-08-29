package com.tictactoe.gamemodule.factory;

import com.tictactoe.gamemodule.models.enums.BotDifficultyLevel;
import com.tictactoe.gamemodule.strategies.botplayingstrategies.BotPlayingStrategy;
import com.tictactoe.gamemodule.strategies.botplayingstrategies.EasyBotPlayingStrategy;
import com.tictactoe.gamemodule.strategies.botplayingstrategies.HardBotPlayingStrategy;
import com.tictactoe.gamemodule.strategies.botplayingstrategies.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyFactory(BotDifficultyLevel botDifficultyLevel) {
        switch (botDifficultyLevel) {
            case EASY:
                return new EasyBotPlayingStrategy();
            case MEDIUM:
                return new MediumBotPlayingStrategy();
            case HARD:
                return new HardBotPlayingStrategy();
        }
        return null;
    }
}
