package com.zarubin.flink.streaming.auction;

public class Bidding {

    private long id;
    private long customerId;
    private double price;

    public Bidding(long id, long customerId, double price) {
        this.id = id;
        this.customerId = customerId;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Bidding{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", price=" + price +
                '}';
    }
}
