package com.zarubin.flink.streaming.auction;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;

import java.util.HashSet;
import java.util.Set;

import static java.util.Optional.ofNullable;

public class UniqueCustomerAggregateFunction implements AggregateFunction<AuctionEvent, Tuple2<Long, Set<Long>>, Tuple2<Long, Integer>> {

    private static final long serialVersionUID = 2128362829096760557L;

    @Override
    public Tuple2<Long, Set<Long>> createAccumulator() {
        return new Tuple2<>(0L, new HashSet<>());
    }

    @Override
    public Tuple2<Long, Set<Long>> add(AuctionEvent value, Tuple2<Long, Set<Long>> accumulator) {
        accumulator.setField(value.getProductId(), 0);
        ofNullable(value.getBidding())
                .ifPresent(bidding -> accumulator.f1.add(bidding.getCustomerId()));
        return accumulator;
    }

    @Override
    public Tuple2<Long, Integer> getResult(Tuple2<Long, Set<Long>> accumulator) {
        return new Tuple2(accumulator.f0, accumulator.f1.size());
    }

    @Override
    public Tuple2<Long, Set<Long>> merge(Tuple2<Long, Set<Long>> a, Tuple2<Long, Set<Long>> b) {
        a.f1.addAll(b.f1);
        return a;
    }
}
