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
package org.chtijbug.drools.entity.history.session;

/**
 * Created by IntelliJ IDEA.
 * Date: 05/06/13
 * Time: 16:38
 * To change this template use File | Settings | File Templates.
 */
public class SessionFireAllRulesMaxNumberReachedEvent extends SessionEvent {
    private int numberOfRulesExecuted = 0;
    private int maxNumberOfRulesForSession = 0;

    public SessionFireAllRulesMaxNumberReachedEvent(Long eventID, int numberOfRulesExecuted, int maxNumberOfRulesForSession, Long ruleBaseId, Long sessionId) {
        super(eventID, ruleBaseId, sessionId);
        this.numberOfRulesExecuted = numberOfRulesExecuted;
        this.maxNumberOfRulesForSession = maxNumberOfRulesForSession;
    }

    public SessionFireAllRulesMaxNumberReachedEvent() {
    }

    public int getNumberOfRulesExecuted() {
        return numberOfRulesExecuted;
    }

    public void setNumberOfRulesExecuted(int numberOfRulesExecuted) {
        this.numberOfRulesExecuted = numberOfRulesExecuted;
    }

    public int getMaxNumberOfRulesForSession() {
        return maxNumberOfRulesForSession;
    }

    public void setMaxNumberOfRulesForSession(int maxNumberOfRulesForSession) {
        this.maxNumberOfRulesForSession = maxNumberOfRulesForSession;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("SessionFireAllRulesMaxNumberReachedEvent");
        sb.append("{numberOfRulesExecuted=").append(numberOfRulesExecuted);
        sb.append(", maxNumberOfRulesForSession=").append(maxNumberOfRulesForSession);
        sb.append('}');
        return sb.toString();
    }
}
