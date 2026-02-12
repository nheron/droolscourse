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
package org.chtijbug.drools.common.jaxb;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class JAXBContextUtilsTest {

    @Test
    public void testMarshallObjectAsString() throws Exception {
        ToTestClass toTestClass = new ToTestClass();
        toTestClass.setAttr1("attr1");
        toTestClass.setAttr2("attr2");

        String toEvaluate = JAXBContextUtils.marshallObjectAsString(ToTestClass.class, toTestClass);

        assertThat(toEvaluate).isNotNull();
        assertThat(toEvaluate).isEqualTo("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<toTestClass>\n" +
                "    <attr1>attr1</attr1>\n" +
                "    <attr2>attr2</attr2>\n" +
                "</toTestClass>\n");
    }

}
