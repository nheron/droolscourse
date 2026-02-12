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

import junit.framework.Assert;
import org.apache.commons.io.IOUtils;
import org.chtijbug.drools.common.date.DateHelper;
import org.chtijbug.drools.entity.DroolsFactObject;
import org.chtijbug.drools.runtime.pojotest.User;
import org.chtijbug.drools.runtime.pojotest.UserName;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * Date: 24/02/14
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class JSONdroolsObjecttest {
    @Test
    public void testJson() throws Exception {

        UserName userName = new UserName();
        userName.setFirstname("Katamreddy");
        userName.setMiddlename("Siva");
        userName.setLastname("PrasadReddy");

        User user = new User();
        user.setUserId("1");
        user.setUserName(userName);
        user.setDob(DateHelper.getDate("2013-12-31"));
        DroolsFactObject droolsFactObject = new DroolsFactObject(user, 1);
        InputStream stream = JSONdroolsObjecttest.class.getResourceAsStream("/user.json");
        String toto = IOUtils.toString(stream, "utf-8");
        Assert.assertTrue(toto.equals(droolsFactObject.getRealObject_JSON()));
    }
}
