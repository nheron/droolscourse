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

import org.chtijbug.drools.entity.DroolsFactObject;

/**
 * Created by IntelliJ IDEA.
 * Date: 03/07/14
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
public class SessionFireAllRulesAndStartProcess extends SessionEvent {


    protected DroolsFactObject inputObject;
    protected DroolsFactObject outputObject;

    public SessionFireAllRulesAndStartProcess(Long eventID, Long ruleBaseId, Long sessionId, DroolsFactObject inputObject, DroolsFactObject outputObject) {
        super(eventID, ruleBaseId, sessionId);
        this.inputObject = inputObject;
        this.outputObject = outputObject;
    }

    public SessionFireAllRulesAndStartProcess() {
    }

    public DroolsFactObject getInputObject() {
        return inputObject;
    }

    public DroolsFactObject getOutputObject() {
        return outputObject;
    }
}
