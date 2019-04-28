package com.zarubin.flink.streaming.auction;

import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

public class AuctionTrigger extends Trigger<AuctionEvent, TimeWindow> {

    private static final long serialVersionUID = 6083523382567780230L;

    @Override
    public TriggerResult onElement(AuctionEvent element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
        if (element.getEventType() == AuctionEventType.END) {
           return TriggerResult.FIRE_AND_PURGE;
        } else {
            return TriggerResult.CONTINUE;
        }
    }

    @Override
    public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.CONTINUE;
    }

    @Override
    public void clear(TimeWindow window, TriggerContext ctx) throws Exception {
    }
}
