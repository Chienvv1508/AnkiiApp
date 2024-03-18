package com.example.theghinho;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theghinho.Models.Card;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private List<Card> cards;

    public CardAdapter(List<Card> cards) {
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.card_item, parent, false);
        CardViewHolder ret = new CardViewHolder(itemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card c = cards.get(position);
        holder.setDataCard(c);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
