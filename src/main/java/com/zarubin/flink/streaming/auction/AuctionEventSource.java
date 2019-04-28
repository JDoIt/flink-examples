package com.zarubin.flink.streaming.auction;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.List;

import static java.util.Arrays.asList;

public class AuctionEventSource extends RichParallelSourceFunction<AuctionEvent> {

    private static final long serialVersionUID = 6292493843385390024L;

    private volatile boolean running = true;


    @Override
    public void run(SourceFunction.SourceContext<AuctionEvent> sourceContext) {

        long count = 0L;
        long numElements = 1000L;

        while (running && count < numElements) {
            ++count;

            //TODO: implement Events generating
        }
    }

    @Override
    public void cancel() {
        running = false;
    }


    public static final List<AuctionEvent> AUCTION_EVENTS = asList(
            AuctionEvent.startAuctionEvent(1L, 1L),
            AuctionEvent.biddingAuctionEvent(1L, 1L, new Bidding(10L, 12L, 400)),
            AuctionEvent.biddingAuctionEvent(1L, 1L, new Bidding(11L, 13L, 450)),
            AuctionEvent.biddingAuctionEvent(1L, 2L, new Bidding(12L, 11L, 500)),
            AuctionEvent.biddingAuctionEvent(1L, 1L, new Bidding(13L, 13L, 550)),
            AuctionEvent.biddingAuctionEvent(1L, 1L, new Bidding(14L, 17L, 600)),
            AuctionEvent.endAuctionEvent(1L, 1L),
            AuctionEvent.endAuctionEvent(1L, 2L)
    );
}
