package com.tictactoe.gpt.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tictactoe.R;
import com.tictactoe.databinding.ItemPlayerBinding;
import com.tictactoe.gamemodule.models.Bot;
import com.tictactoe.gamemodule.models.Player;
import com.tictactoe.gamemodule.models.enums.PlayerType;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewData> {
    Context context;
    List<Player> players;
    ItemPlayerBinding binding;

    public PlayersAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewData(ItemPlayerBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewData holder, int position) {
        if(players.get(position).getPlayerType().equals(PlayerType.BOT)) {
            binding.txtBotDifficulty.setVisibility(View.VISIBLE);
            Bot bot = (Bot) players.get(position);
            binding.txtBotDifficulty.setText(bot.getBotDifficultyLevel().toString());
        }

        binding.txtPlayerName.setText(players.get(position).getName());
        binding.txtPlayerSymbol.setText("" + players.get(position).getSymbol().getChar());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    class ViewData extends RecyclerView.ViewHolder {
        public ViewData(@NonNull ItemPlayerBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
