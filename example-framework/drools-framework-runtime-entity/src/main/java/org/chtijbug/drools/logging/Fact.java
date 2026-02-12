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
package org.chtijbug.drools.logging;

import java.util.Date;


public class Fact {


    private Object realFact;

    private Integer objectVersion;

    private String fullClassName;

    private Date modificationDate;


    private FactType factType;


    private Long eventid;

    public Fact() {
    }

    public Object getRealFact() {
        return realFact;
    }

    public void setRealFact(Object realFact) {
        this.realFact = realFact;
    }

    public Integer getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(Integer objectVersion) {
        this.objectVersion = objectVersion;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public FactType getFactType() {
        return factType;
    }

    public void setFactType(FactType factType) {
        this.factType = factType;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Fact{");
        sb.append(", jsonFact='").append(realFact).append('\'');
        sb.append(", objectVersion=").append(objectVersion);
        sb.append(", fullClassName='").append(fullClassName).append('\'');
        sb.append(", modificationDate=").append(modificationDate);
        sb.append(", factType=").append(factType);

        sb.append(", eventid=").append(eventid);
        sb.append('}');
        return sb.toString();
    }
}
