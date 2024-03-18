package com.example.theghinho;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theghinho.Models.Card;

public class CardViewHolder extends RecyclerView.ViewHolder {

    private TextView tvQuestion;
    private TextView tvAnswer;
    private void bindingView(){
        tvQuestion = itemView.findViewById(R.id.tvQuestion);
        tvAnswer = itemView.findViewById(R.id.tvAnswer);
    }
    private void bindingAction(){

    }
    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }
    public void setDataCard(Card c){
        tvQuestion.setText(c.getFontCard());
        tvAnswer.setText(c.getBackCard());
    }
}
