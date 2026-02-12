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
package org.chtijbug.drools.entity.history.fact;

import org.chtijbug.drools.entity.DroolsFactObject;
import org.chtijbug.drools.entity.DroolsFactObjectAttribute;

import java.util.Date;

/**
 * @author nheron
 */
public class UpdatedFactHistoryEvent extends FactHistoryEvent {
    /**
     *
     */
    private static final long serialVersionUID = 2427277089998136875L;
    protected DroolsFactObject objectOldValue;
    protected DroolsFactObject objectNewValue;

    /**
     *
     */
    public UpdatedFactHistoryEvent() {
    }

    public UpdatedFactHistoryEvent(Long eventID, DroolsFactObject objectOldValue, DroolsFactObject objectNewValue, Long ruleBaseId, Long sessionId) {
        super(eventID, new Date(), ruleBaseId, sessionId);
        this.objectOldValue = objectOldValue;
        this.objectNewValue = objectNewValue;

    }

    public DroolsFactObject getObjectNewValue() {
        return objectNewValue;
    }

    public void setObjectNewValue(DroolsFactObject objectNewValue) {
        this.objectNewValue = objectNewValue;
    }

    public DroolsFactObject getObjectOldValue() {
        return objectOldValue;
    }

    public void setObjectOldValue(DroolsFactObject objectOldValue) {
        this.objectOldValue = objectOldValue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString() + "\n");
        sb.append("UpdatedFactHistoryEvent");
        sb.append("Update Object : " + objectNewValue.getFullClassName() + "\n");
        sb.append("{objectOldValue=").append("\n");
        sb.append("version Object : " + objectOldValue.getObjectVersion() + "\n");
        sb.append("attributes :\n");
        for (DroolsFactObjectAttribute foa : objectOldValue.getListfactObjectAttributes()) {
            sb.append("- " + foa.getAttributeType() + " " + foa.getAttributeName() + "=" + foa.getAttributeValue() + "\n");
        }
        sb.append(", objectNewValue=").append(objectNewValue);
        sb.append("version Object : " + objectNewValue.getObjectVersion() + "\n");
        sb.append("attributes :\n");
        for (DroolsFactObjectAttribute foa : objectNewValue.getListfactObjectAttributes()) {
            sb.append("- " + foa.getAttributeType() + " " + foa.getAttributeName() + "=" + foa.getAttributeValue() + "\n");
        }
        sb.append('}');
        return sb.toString();
    }
}
