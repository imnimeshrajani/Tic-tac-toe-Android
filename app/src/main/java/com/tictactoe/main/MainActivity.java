package com.tictactoe.main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tictactoe.databinding.ActivityMainBinding;
import com.tictactoe.gamemodule.controller.GameController;
import com.tictactoe.gamemodule.exeption.InvalidMoveException;
import com.tictactoe.gamemodule.models.Cell;
import com.tictactoe.gamemodule.models.Game;
import com.tictactoe.gamemodule.models.Player;
import com.tictactoe.gamemodule.models.enums.GameState;
import com.tictactoe.gamemodule.strategies.winningstrategies.ColumnWinningStrategy;
import com.tictactoe.gamemodule.strategies.winningstrategies.DiagonalWinningStrategy;
import com.tictactoe.gamemodule.strategies.winningstrategies.RowWinningStrategy;
import com.tictactoe.gamemodule.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        try {
        // getting error
            List<Player> players = (List<Player>) getIntent().getSerializableExtra("players");
//        } catch (Exception e) {
//            Log.e("TAG", "onCreate: " + e.getMessage());
//        }

//        Log.e("TAG", "onCreate: " + players);

        List<List<Cell>> board = new ArrayList<>();
        for (int i = 0; i <= players.size(); i++) {
            board.add(new ArrayList<>());
        }
        ListAdapter listAdapter = new ListAdapter(board);
        game(players, board, listAdapter);
        binding.rvListBox.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListBox.setAdapter(listAdapter);

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    public void game(List<Player> players, List<List<Cell>> board, ListAdapter listAdapter) {
        GameController gameController = new GameController();


        //Take players information in the input.

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        Game game = gameController.startGame(board.size(), players, winningStrategies);

        //gameController.printBoard(game);
        //Let's play the game.

        while (gameController.gameState(game).equals(GameState.IN_PROGRESS)) {
            //1. Show the board.
            //2. make a move.
            listAdapter.updateAdapter(board);
//            gameController.printBoard(game);
            // update adapter

            System.out.println("Do you want to undo ? y/n");

//            if (isUndo.equalsIgnoreCase("y")) {
//                //make an undo operation
//                gameController.undo(game);
//                continue;
//            }

            try {
                gameController.makeMove(game);
            } catch (InvalidMoveException e) {
                throw new RuntimeException(e);
            }
        }

        gameController.printBoard(game);
        if (gameController.gameState(game).equals(GameState.ENDED)) {
            System.out.println(gameController.getWinner(game).getName() + " has won the game.");
        } else {
            System.out.println("GAME DRAW");
        }
    }
}