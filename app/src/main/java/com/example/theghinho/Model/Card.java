package com.example.theghinho.Model;

public class Card {
    private int cardId;
    private String fontCard, backCard;

    private int folderId;

    public Card() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getFontCard() {
        return fontCard;
    }

    public void setFontCard(String fontCard) {
        this.fontCard = fontCard;
    }

    public String getBackCard() {
        return backCard;
    }

    public void setBackCard(String backCard) {
        this.backCard = backCard;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }
}
