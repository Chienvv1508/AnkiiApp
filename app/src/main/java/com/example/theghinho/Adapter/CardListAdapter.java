package com.example.theghinho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theghinho.ItemViewHolder.CardItemViewHolder;
import com.example.theghinho.ItemViewHolder.FolderItemViewHolder;
import com.example.theghinho.Model.Card;
import com.example.theghinho.Model.Folder;
import com.example.theghinho.R;

import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardItemViewHolder> {
    List<Card> cards;
    Context context;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public CardListAdapter(List<Card> cards, Context context) {
        this.cards = cards;
        this.context = context;
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.item_the, parent, false);
        CardItemViewHolder holder = new CardItemViewHolder(itemView,context, this);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.setCard(card);
        holder.setData();

    }

    @Override
    public int getItemCount() {
       return cards.size();
    }
}
