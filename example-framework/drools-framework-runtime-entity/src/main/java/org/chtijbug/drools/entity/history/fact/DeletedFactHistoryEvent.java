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
public class DeletedFactHistoryEvent extends FactHistoryEvent {

    /**
     *
     */
    private static final long serialVersionUID = -1924924006386653359L;
    protected DroolsFactObject deletedObject;

    /**
     *
     */
    public DeletedFactHistoryEvent() {
    }

    public DeletedFactHistoryEvent(Long eventID, DroolsFactObject deletedObject, Long ruleBaseId, Long sessionId) {
        super(eventID, new Date(), ruleBaseId, sessionId);
        this.deletedObject = deletedObject;
    }

    public DroolsFactObject getDeletedObject() {
        return deletedObject;
    }

    public void setDeletedObject(DroolsFactObject deletedObject) {
        this.deletedObject = deletedObject;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString() + "\n");
        str.append("retracted Object : " + deletedObject.getFullClassName() + "\n");
        str.append("version Object : " + deletedObject.getObjectVersion() + "\n");
        str.append("attributes :\n");
        for (DroolsFactObjectAttribute foa : deletedObject.getListfactObjectAttributes()) {
            str.append("- " + foa.getAttributeType() + " " + foa.getAttributeName() + "=" + foa.getAttributeValue() + "\n");
        }
        return str.toString();
    }
}
