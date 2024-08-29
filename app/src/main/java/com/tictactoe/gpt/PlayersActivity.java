package com.tictactoe.gpt;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.textfield.TextInputEditText;
import com.tictactoe.R;
import com.tictactoe.databinding.ActivityPlayersBinding;
import com.tictactoe.gamemodule.models.Symbol;
import com.tictactoe.gamemodule.models.enums.BotDifficultyLevel;
import com.tictactoe.gamemodule.models.enums.PlayerType;
import com.tictactoe.gpt.adapters.PlayersAdapter;
import com.tictactoe.main.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlayersActivity extends AppCompatActivity {

    ActivityPlayersBinding binding;
    HashSet<Character> set = new HashSet<>();
    List<com.tictactoe.gamemodule.models.Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityPlayersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PlayersAdapter playersAdapter = new PlayersAdapter(players);
        binding.rvPlayers.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPlayers.setAdapter(playersAdapter);

        binding.btnAddPlayer.setOnClickListener(view -> {
            Dialog dialog = new Dialog(PlayersActivity.this);
            dialog.setContentView(R.layout.dialog_add_data);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            LinearLayout btn_yes = dialog.findViewById(R.id.btnOk);
            LinearLayout btn_no = dialog.findViewById(R.id.btnCancel);
            TextInputEditText name = dialog.findViewById(R.id.edtName);
            TextInputEditText symbol = dialog.findViewById(R.id.edtSymbol);
            RadioButton human = dialog.findViewById(R.id.radioHuman);
            RadioButton bot = dialog.findViewById(R.id.radioBot);
            RadioGroup playerType = dialog.findViewById(R.id.radioPlayerTypes);
            final PlayerType[] pt = {PlayerType.HUMAN};
            playerType.setOnCheckedChangeListener((radioGroup, i) -> pt[0] = (bot.isChecked()) ? PlayerType.BOT : PlayerType.HUMAN);
            btn_yes.setOnClickListener(view1 -> {
                String userName = name.getText().toString();
                char ch = symbol.getText().charAt(0);

                if (!userName.isEmpty() || !(ch == ' ')) {
                    if (!set.contains(userName)) {
                        set.add(ch);
                        if (pt[0].equals(PlayerType.HUMAN)) {
                            players.add(new com.tictactoe.gamemodule.models.Player(userName, new Symbol(ch), pt[0]));
                        } else {
                            players.add(new com.tictactoe.gamemodule.models.Bot(userName, new Symbol(ch), pt[0], BotDifficultyLevel.EASY));
                        }
                        playersAdapter.updateList(players);
                        dialog.dismiss();
                    } else {
                        symbol.setError("Symbol is already used");
                    }
                } else name.setError("Data Incorrect");
            });
            btn_no.setOnClickListener(view12 -> dialog.dismiss());
            dialog.show();
        });

        binding.btnStartGame.setOnClickListener(view -> {
            if(players.size() < 2)
                Toast.makeText(this, "Players Count Is Not Efficient", Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("players", (Serializable) players);
                startActivity(intent);
            }
        });
    }
}