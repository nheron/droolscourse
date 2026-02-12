package org.chtijbug.drools.runtime;

import org.chtijbug.drools.entity.history.HistoryEvent;
import org.chtijbug.drools.runtime.listener.HistoryListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nheron on 07/07/2016.
 */
public class TestHistoryListener implements HistoryListener {

    private boolean withDetails=true;

    private List<HistoryEvent> historyEventLinkedList = new ArrayList<>();

    public List<HistoryEvent> getHistoryEventLinkedList() {
        return historyEventLinkedList;
    }

    public void setWithDetails(boolean withDetails) {
        this.withDetails = withDetails;
    }

    @Override
    public void fireEvent(HistoryEvent newHistoryEvent) throws DroolsChtijbugException {
        historyEventLinkedList.add(newHistoryEvent);

    }

    @Override
    public boolean withDetails() {
        return withDetails;
    }
    @Override
    public void setDetails(Boolean details) {
        withDetails = details;
    }
}
