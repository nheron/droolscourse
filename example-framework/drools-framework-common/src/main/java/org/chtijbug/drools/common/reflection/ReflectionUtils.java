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

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: samuel
 * Date: 26/09/12
 * Time: 15:45
 */
public class ReflectionUtils {

    /**
     * Returns <code>true</code> if the method is a getter meaning :
     * <ul>
     * <li>method name start with 'get' </li>
     * <li>no parameters expected from the method</li>
     * <li>the return type of the method is not void</li>
     * </ul>
     * Otherwise, it will return <code>false</code>
     *
     * @param method Method
     * @return true if the method is a getter. false otherwise.
     */
    public static boolean IsGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }
}
