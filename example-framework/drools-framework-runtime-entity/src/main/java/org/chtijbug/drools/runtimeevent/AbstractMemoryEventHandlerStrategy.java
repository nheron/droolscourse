package org.chtijbug.drools.runtimeevent;

import com.rits.cloning.Cloner;
import org.chtijbug.drools.SessionContext;
import org.chtijbug.drools.entity.history.HistoryEvent;

/**
 * Created by nheron on 11/06/15.
 */
public interface AbstractMemoryEventHandlerStrategy {

    public abstract void handleMessageInternally(HistoryEvent historyEvent, SessionContext sessionContext, Cloner cloner);

    public abstract boolean isEventSupported(HistoryEvent historyEvent);



}
