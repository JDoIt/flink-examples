package com.zarubin.flink.streaming.auction;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.HashSet;
import java.util.Set;

import static java.util.Optional.ofNullable;

public class AuctionUniqueCustomerFunction implements WindowFunction<AuctionEvent, Tuple2<Long, Integer>, Long, TimeWindow> {

    private static final long serialVersionUID = 8706538006284153207L;

    @Override
    public void apply(Long key, TimeWindow window, Iterable<AuctionEvent> input, Collector<Tuple2<Long, Integer>> out) throws Exception {
        Set<Long> customerIds = new HashSet<>();

        input.forEach(auction -> ofNullable(auction.getBidding())
                    .ifPresent(bidding -> customerIds.add(bidding.getCustomerId())));

        out.collect(new Tuple2<>(key, customerIds.size()));
    }
}
