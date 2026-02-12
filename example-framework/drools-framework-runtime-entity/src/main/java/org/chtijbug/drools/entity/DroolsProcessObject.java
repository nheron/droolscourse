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
package org.chtijbug.drools.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nheron
 */
public class DroolsProcessObject implements Serializable {

    private static final long serialVersionUID = 4718079763911002405L;
    protected String id;
    protected String name;
    protected String packageName;
    protected String type;
    protected String version;
    protected Map<String, DroolsNodeObject> nodeLists;

    public DroolsProcessObject() {
    }

    public DroolsProcessObject(String id, String name, String packageName, String type, String version) {
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.type = type;
        this.version = version;
        this.nodeLists = new HashMap<String, DroolsNodeObject>();
    }

    public static DroolsProcessObject createDroolsProcessObject(String id, String processInstanceID, String packageName, String type, String version) {

        return new DroolsProcessObject(id, processInstanceID, packageName, type, version);
    }

    public void addDroolsNodeObject(DroolsNodeObject droolsNodeObject) {
        nodeLists.put(droolsNodeObject.getId(), droolsNodeObject);
    }

    public DroolsNodeObject getDroolsNodeObjet(String id) {
        return nodeLists.get(id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DroolsProcessObject other = (DroolsProcessObject) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 41 * hash + (this.packageName != null ? this.packageName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("DroolsProcessObject");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", packageName='").append(packageName).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", nodeLists=").append(nodeLists);
        sb.append('}');
        return sb.toString();
    }
}
