package com.example.theghinho.Models;

public class Card {
    private int CardId;
    private String FontCard;
    private String BackCard;

    public Card(int cardId, String fontCard, String backCard) {
        CardId = cardId;
        FontCard = fontCard;
        BackCard = backCard;
    }

    public int getCardId() {
        return CardId;
    }

    public void setCardId(int cardId) {
        CardId = cardId;
    }

    public String getFontCard() {
        return FontCard;
    }

    public void setFontCard(String fontCard) {
        FontCard = fontCard;
    }

    public String getBackCard() {
        return BackCard;
    }

    public void setBackCard(String backCard) {
        BackCard = backCard;
    }
}
