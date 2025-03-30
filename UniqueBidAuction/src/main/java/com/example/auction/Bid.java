package com.auction;

public class Bid {
    private int bidderId;
    private int amount;

    public Bid(int bidderId, int amount) {
        this.bidderId = bidderId;
        this.amount = amount;
    }

    public int getBidderId() {
        return bidderId;
    }

    public int getAmount() {
        return amount;
    }
}
