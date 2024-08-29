package com.tictactoe.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tictactoe.databinding.ItemViewThreeBoxBinding;
import com.tictactoe.gamemodule.models.Cell;
import com.tictactoe.gamemodule.models.Player;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context context;
    ItemViewThreeBoxBinding boxBinding;
    List<List<Cell>> board;

    public ListAdapter(List<List<Cell>> list) {
        board = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(ItemViewThreeBoxBinding.inflate(LayoutInflater.from(context)));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        boxBinding.rvHorizontalBox.setLayoutManager(new LinearLayoutManager(context));
        boxBinding.rvHorizontalBox.setAdapter(new BoxAdapter(board.get(position)));
    }

    @Override
    public int getItemCount() {
        return board.size();
    }

    public void updateAdapter(List<List<Cell>> board) {
        this.board = board;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ItemViewThreeBoxBinding itemView) {
            super(itemView.getRoot());
            boxBinding = itemView;
        }
    }
}
