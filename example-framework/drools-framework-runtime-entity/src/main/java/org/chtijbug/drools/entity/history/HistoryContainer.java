/*
 * Copyright 2014 Pymma Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chtijbug.drools.entity.history;

import org.chtijbug.drools.runtime.DroolsChtijbugException;
import org.chtijbug.drools.runtime.listener.HistoryListener;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author nheron
 */
public class HistoryContainer implements Serializable {
    private static final long serialVersionUID = 5645452451089006572L;
    protected List<HistoryEvent> listHistoryEvent = new LinkedList<HistoryEvent>();
    private Long sessionID;
    private HistoryListener historylistener = null;

    public HistoryContainer() {
    }

    /**
     *
     */

    public HistoryContainer(Long sessionID, HistoryListener historylistener) {
        this.sessionID = sessionID;
        this.historylistener = historylistener;
    }

    public Long getSessionID() {
        return sessionID;
    }

    public void setSessionID(Long sessionID) {
        this.sessionID = sessionID;
    }

    public List<HistoryEvent> getListHistoryEvent() {
        return listHistoryEvent;
    }

    public void addHistoryElement(Long ruleBaseID, Long sessionID, HistoryEvent newHistoryElement) {
        DroolsChtijbugException error = null;
        newHistoryElement.setRuleBaseID(ruleBaseID);
        newHistoryElement.setSessionId(sessionID);
        try {
            if (historylistener != null) {
                historylistener.fireEvent(newHistoryElement);
            }
        } catch (DroolsChtijbugException e) {
            error = e;
        } finally {
            if (error != null) {
                newHistoryElement.setDroolsChtijbugException(error);
            }
            this.listHistoryEvent.add(newHistoryElement);
        }
    }
}
