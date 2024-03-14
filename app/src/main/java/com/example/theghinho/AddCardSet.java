package com.example.theghinho;

public class AddCardSet {
    private int cardSetId;
    private String cardSetName;
    private int userId; // Người dùng tạo ra tập thẻ này

    public AddCardSet(int cardSetId, String cardSetName, int userId) {
        this.cardSetId = cardSetId;
        this.cardSetName = cardSetName;
        this.userId = userId;
    }

    public int getCardSetId() {
        return cardSetId;
    }

    public void setCardSetId(int cardSetId) {
        this.cardSetId = cardSetId;
    }

    public String getCardSetName() {
        return cardSetName;
    }

    public void setCardSetName(String cardSetName) {
        this.cardSetName = cardSetName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}