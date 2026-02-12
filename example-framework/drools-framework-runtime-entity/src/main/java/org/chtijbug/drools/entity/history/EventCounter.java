package org.chtijbug.drools.entity.history;

import java.util.concurrent.atomic.AtomicLong;

public final class EventCounter {
    private AtomicLong nextEventId = new AtomicLong(1L);

    private EventCounter() {
    }

    public static EventCounter newCounter() {
        return new EventCounter();
    }

    public Long next() {
        return nextEventId.getAndIncrement();
    }

    public Long current() {
        return nextEventId.get();
    }

}
