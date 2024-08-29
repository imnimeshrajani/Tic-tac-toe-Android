package com.tictactoe.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tictactoe.databinding.ItemViewBoxBinding;
import com.tictactoe.gamemodule.models.Cell;
import com.tictactoe.gamemodule.models.Player;
import com.tictactoe.gamemodule.models.enums.CellState;

import java.util.List;

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder> {
    Context context;
    ItemViewBoxBinding binding;
    List<Cell> cells;

    public BoxAdapter(List<Cell> cells) {
        this.cells = cells;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(ItemViewBoxBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(cells.get(position).getCellState().equals(CellState.FILLED)) {
            binding.showTextSymbol.setText("" + cells.get(position).getPlayer().getSymbol().getChar());
        } else {
            binding.showTextSymbol.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return cells.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemViewBoxBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
