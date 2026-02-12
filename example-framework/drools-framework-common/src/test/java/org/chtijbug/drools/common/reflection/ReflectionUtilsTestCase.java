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
package org.chtijbug.drools.common.reflection;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: samuel
 * Date: 01/10/12
 * Time: 09:42
 */
public class ReflectionUtilsTestCase {


    @Test
    public void runIsGetter() {

        try {
            Method methodToEval = TestClass.class.getMethod("getProperty");
            assertTrue(ReflectionUtils.IsGetter(methodToEval));

            methodToEval = TestClass.class.getMethod("execute");
            assertFalse(ReflectionUtils.IsGetter(methodToEval));


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail();
        }

    }


    private class TestClass {
        private String property;

        public String getProperty() {
            return property;
        }

        public void execute() {

        }
    }

}
