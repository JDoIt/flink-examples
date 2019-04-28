package com.zarubin.flink.streaming.auction;

public class AuctionEvent {

    private long id;
    private long productId;

    private Bidding bidding;

    private long timestamp;

    private AuctionEventType eventType;

    public AuctionEvent(long id, long productId, Bidding bidding, long timestamp, AuctionEventType eventType) {
        this.id = id;
        this.productId = productId;
        this.bidding = bidding;
        this.timestamp = timestamp;
        this.eventType = eventType;
    }

    public long getId() {
        return id;
    }

    public long getProductId() {
        return productId;
    }

    public Bidding getBidding() {
        return bidding;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public AuctionEventType getEventType() {
        return eventType;
    }

    public static AuctionEvent startAuctionEvent(long id, long productId) {
        return new AuctionEvent(id, productId, null, System.currentTimeMillis(), AuctionEventType.START);
    }

    public static AuctionEvent endAuctionEvent(long id, long productId) {
        return new AuctionEvent(id, productId, null, System.currentTimeMillis(), AuctionEventType.END);
    }

    public static AuctionEvent biddingAuctionEvent(long id, long productId, Bidding data) {
        return new AuctionEvent(id, productId, data, System.currentTimeMillis(), AuctionEventType.BIDDING);
    }

    @Override
    public String toString() {
        return "AuctionEvent{" +
                "id=" + id +
                ", productId=" + productId +
                ", bidding=" + bidding +
                ", timestamp=" + timestamp +
                ", eventType=" + eventType +
                '}';
    }
}
