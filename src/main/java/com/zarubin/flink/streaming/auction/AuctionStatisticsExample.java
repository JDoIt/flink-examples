package com.zarubin.flink.streaming.auction;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class AuctionStatisticsExample {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setParallelism(4);

       // DataStream<AuctionEvent> stream = env.addSource(new AuctionEventSource());

        DataStream<AuctionEvent> stream = env.fromCollection(AuctionEventSource.AUCTION_EVENTS);
        stream.assignTimestampsAndWatermarks(new AuctionTimestampExtractor())
                .keyBy(AuctionEvent::getProductId)
                .window(TumblingEventTimeWindows.of(Time.minutes(20)))
                .trigger(new AuctionTrigger())
                .aggregate(new UniqueCustomerAggregateFunction())
                .print();

        env.execute("Run Auction Stream");
    }
}
