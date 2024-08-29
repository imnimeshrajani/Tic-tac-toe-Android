package com.tictactoe.gpt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.tictactoe.R;
import com.tictactoe.databinding.ActivityGptMainBinding;

public class GptMainActivity extends AppCompatActivity {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;
    private GameRules gameRules;
    private GameManager gameManager;

    ActivityGptMainBinding binding;
    private Button[][] buttons;

    private int boardSize = 3;// Set this to your desired board size

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGptMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        player1 = new Player("Player X", 'X');
        player2 = new Player("Player O", 'O');
        currentPlayer = player1;

        board = new Board(boardSize);
        gameRules = new GameRules(boardSize);
        gameManager = new GameManager(player1, player2, board, gameRules);

        createBoard(binding.boardGridLayout);
        updateStatus();

        binding.resetButton.setOnClickListener(v -> resetGame());
    }

    private void createBoard(GridLayout boardGridLayout) {
        boardGridLayout.setRowCount(boardSize);
        boardGridLayout.setColumnCount(boardSize);

        buttons = new Button[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setTextSize(24);
                final int row = i;
                final int col = j;
                buttons[i][j].setOnClickListener(v -> onCellClicked(row, col));

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(i, 1f);
                params.columnSpec = GridLayout.spec(j, 1f);
                params.width = 0;
                params.height = 0;

                boardGridLayout.addView(buttons[i][j], params);
            }
        }
    }

    private void onCellClicked(int row, int col) {
        if (gameManager.playMove(row, col)) {
            buttons[row][col].setText(String.valueOf(currentPlayer.getSymbol()));
            if (gameRules.checkWin(board, currentPlayer.getSymbol())) {
                binding.statusTextView.setText(currentPlayer.getName() + " wins!");
                disableButtons();
            } else if (gameRules.checkDraw(board)) {
                binding.statusTextView.setText("It's a draw!");
            } else {
                gameManager.switchPlayer();
                currentPlayer = gameManager.getCurrentPlayer();
                updateStatus();
            }
        }
    }

    private void resetGame() {
        board.resetBoard();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        currentPlayer = player1;
        updateStatus();
    }

    private void updateStatus() {
        binding.statusTextView.setText(currentPlayer.getName() + "'s turn");
    }

    private void disableButtons() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}