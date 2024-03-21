package com.example.theghinho.ItemViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theghinho.Adapter.CardListAdapter;
import com.example.theghinho.DAO.CardDAO;
import com.example.theghinho.Model.Card;
import com.example.theghinho.R;

public class CardItemViewHolder  extends RecyclerView.ViewHolder {
    TextView txtFontCard, txtBackCard;
    Button btnChinhSua, btnXoa;
    Card card;
    Context context;
    CardListAdapter adapter;
    public CardItemViewHolder(@NonNull View itemView, Context context, CardListAdapter adapter) {
        super(itemView);
        this.context = context;
        this.adapter = adapter;
        bindingView();
        bindingAction();
    }

    public TextView getTxtFontCard() {
        return txtFontCard;
    }

    public void setTxtFontCard(TextView txtFontCard) {
        this.txtFontCard = txtFontCard;
    }

    public TextView getTxtBackCard() {
        return txtBackCard;
    }

    public void setTxtBackCard(TextView txtBackCard) {
        this.txtBackCard = txtBackCard;
    }

    public Button getBtnChinhSua() {
        return btnChinhSua;
    }

    public void setBtnChinhSua(Button btnChinhSua) {
        this.btnChinhSua = btnChinhSua;
    }

    public Button getBtnXoa() {
        return btnXoa;
    }

    public void setBtnXoa(Button btnXoa) {
        this.btnXoa = btnXoa;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    private void bindingAction() {
        btnXoa.setOnClickListener(this::onXoaClick);
        btnChinhSua.setOnClickListener(this::onSuaClick);
    }

    private void onSuaClick(View view) {
    }

    private void onXoaClick(View view) {
        CardDAO dao = new CardDAO(context);
        dao.deleteCard(card.getCardId());
        int position = getAdapterPosition();

        adapter.getCards().remove(card);

        adapter.notifyItemRemoved(position);

    }
    public void setData(){
        txtFontCard.setText(card.getFontCard());
        txtBackCard.setText(card.getBackCard());

    }

    private void bindingView() {
        txtFontCard = itemView.findViewById(R.id.txtFontCardR);
        txtBackCard = itemView.findViewById(R.id.txtBackCardR);
        btnChinhSua = itemView.findViewById(R.id.btnSuaR);
        btnXoa = itemView.findViewById(R.id.btnXoaR);


    }
}
