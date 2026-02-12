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
package org.chtijbug.drools.entity.history.process;

import org.chtijbug.drools.entity.DroolsNodeInstanceObject;

/**
 * @author nheron
 */
public class AfterNodeLeftHistoryEvent extends ProcessHistoryEvent {

    private static final long serialVersionUID = 1117121703139545755L;
    protected DroolsNodeInstanceObject nodeInstance;

    /**
     *
     */
    public AfterNodeLeftHistoryEvent() {
    }

    public AfterNodeLeftHistoryEvent(Long eventID, DroolsNodeInstanceObject nodeInstance, Long ruleBaseId, Long sessionId) {
        super(eventID, ruleBaseId, sessionId);
        this.nodeInstance = nodeInstance;
    }

    public DroolsNodeInstanceObject getNodeInstance() {
        return nodeInstance;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString() + "\n");
        sb.append("NodeInstanceAfterHistoryEvent");
        sb.append("{nodeInstance=").append(nodeInstance);
        sb.append('}');
        return sb.toString();
    }
}
