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
public class InsertedFactHistoryEvent extends FactHistoryEvent {
    /**
     *
     */
    private static final long serialVersionUID = -4530420814276942059L;
    protected DroolsFactObject insertedObject;

    public InsertedFactHistoryEvent() {
    }

    public InsertedFactHistoryEvent(Long eventID, DroolsFactObject insertedObject, Long ruleBaseId, Long sessionId) {
        super(eventID, new Date(), ruleBaseId, sessionId);
        this.insertedObject = insertedObject;
    }

    public DroolsFactObject getInsertedObject() {
        return insertedObject;
    }

    public void setInsertedObject(DroolsFactObject insertedObject) {
        this.insertedObject = insertedObject;
    }

    /*
      * (non-Javadoc)
      *
      * @see org.chtijbug.drools.entity.history.HistoryEvent#toString()
      */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString() + "\n");

        str.append("inserted Object : " + insertedObject.getFullClassName() + "\n");
        str.append("version Object : " + insertedObject.getObjectVersion() + "\n");
        str.append("attributes :\n");
        for (DroolsFactObjectAttribute foa : insertedObject.getListfactObjectAttributes()) {
            str.append("- " + foa.getAttributeType() + " " + foa.getAttributeName() + "=" + foa.getAttributeValue() + "\n");
        }

        return str.toString();
    }

}
