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

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class DroolsChtijbugExceptionTest {
    @Test
    public void should_keep_root_cause() {
        IllegalArgumentException rootCause = new IllegalArgumentException();

        DroolsChtijbugException chtijbugException = new DroolsChtijbugException("key", "bla", rootCause);

        assertNotNull(chtijbugException.getCause());
    }
}
