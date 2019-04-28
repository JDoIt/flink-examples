package com.zarubin.flink.streaming.auction;

import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;

public class AuctionTimestampExtractor extends AscendingTimestampExtractor<AuctionEvent> {

    @Override
    public long extractAscendingTimestamp(AuctionEvent auctionEvent) {
        return auctionEvent.getTimestamp();
    }
}
