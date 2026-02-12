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
package org.chtijbug.drools.runtime;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Date: 24/04/12
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 */
public class DroolsChtijbugException extends Exception implements Serializable {
    public static String insertByReflection = "insertByReflection";
    public static String MaxNumberRuleExecutionReached = "MaxNumberRuleExecutionReached";
    public static String fireAllRules = "fireAllRules";
    public static String KbaseAcquire = "KbaseAcquire";
    public static String KbaseNotInitialised = "KbaseNotInitialised";
    public static String ErrorToLoadAgent = "ErrorToLoadAgent";
    public static String UnknowFileExtension = "UnknowFileExtension";
    public static String ErrorRegisteringMBeans = "";
    public static String RessourceAlreadyAdded = "RessourceAlreadyAdded";
    public static String RessourceDoesNotExist = "RessourceNotExisting";
    private String key;
    private String Description;
    private Exception originException;

    public DroolsChtijbugException() {

    }

    public DroolsChtijbugException(String key, String description, Exception originException) {
        super(key + description, originException);
        this.key = key;
        Description = description;
        this.originException = originException;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return Description;
    }

    public Exception getOriginException() {
        return originException;
    }
}
