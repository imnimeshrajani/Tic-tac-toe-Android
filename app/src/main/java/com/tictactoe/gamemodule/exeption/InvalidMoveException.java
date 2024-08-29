package com.tictactoe.gamemodule.exeption;

public class InvalidMoveException extends Exception{
    public InvalidMoveException(String message) {
        super(message);
    }
}
