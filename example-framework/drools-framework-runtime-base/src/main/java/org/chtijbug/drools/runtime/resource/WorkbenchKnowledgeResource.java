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
package org.chtijbug.drools.runtime.resource;

import org.chtijbug.drools.entity.history.KnowledgeResource;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/01/14
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class WorkbenchKnowledgeResource implements Serializable, KnowledgeResource {


    private String guvnor_url;


    private String groupId;
    private String artifactID;
    private String version;
    private String userName;
    private String password;


    public WorkbenchKnowledgeResource(String guvnor_url, String groupId, String artifactID, String version) {
        this.guvnor_url = guvnor_url;
        this.groupId = groupId;
        this.artifactID = artifactID;
        this.version = version;
    }

    public WorkbenchKnowledgeResource(String guvnor_url, String groupId, String artifactId, String version, String userName, String password) {
        this(guvnor_url, groupId, artifactId, version);
        this.userName = userName;
        this.password = password;
    }


    public WorkbenchKnowledgeResource() {
    }

    public static WorkbenchKnowledgeResource createGuvnorRessource(String guvnor_url, String groupId, String artifactID, String version) {
        return new WorkbenchKnowledgeResource(guvnor_url, groupId, artifactID, version);
    }

    public static WorkbenchKnowledgeResource createGuvnorRessource(String guvnor_url, String groupId, String artifactID, String version, String userName, String password) {
        return new WorkbenchKnowledgeResource(guvnor_url, groupId, artifactID, version, userName, password);
    }

    public String getGuvnor_url() {
        return guvnor_url;
    }

    public void setGuvnor_url(String guvnor_url) {
        this.guvnor_url = guvnor_url;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactID() {
        return artifactID;
    }

    public void setArtifactID(String artifactID) {
        this.artifactID = artifactID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GuvnorRessourceFile{");
        sb.append("guvnor_url='").append(guvnor_url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
