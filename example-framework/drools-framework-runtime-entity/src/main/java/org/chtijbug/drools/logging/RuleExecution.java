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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RuleExecution {


    private String ruleName;
    private String packageName;
    private Date startDate;
    private Date endDate;

    private Long startEventID;

    private Long stopEventID;


    private List<Fact> whenFacts = new ArrayList<Fact>();


    private List<Fact> thenFacts = new ArrayList<Fact>();


    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Long getStartEventID() {
        return startEventID;
    }

    public void setStartEventID(Long startEventID) {
        this.startEventID = startEventID;
    }

    public Long getStopEventID() {
        return stopEventID;
    }

    public void setStopEventID(Long stopEventID) {
        this.stopEventID = stopEventID;
    }

    public List<Fact> getWhenFacts() {
        return whenFacts;
    }

    public void setWhenFacts(List<Fact> whenFacts) {
        this.whenFacts = whenFacts;
    }


    public List<Fact> getThenFacts() {
        return thenFacts;
    }

    public void setThenFacts(List<Fact> thenFacts) {
        this.thenFacts = thenFacts;
    }


}
