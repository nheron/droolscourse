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


import com.google.common.base.Throwables;
import org.apache.commons.io.IOUtils;
import org.chtijbug.drools.entity.history.KnowledgeResource;
import org.kie.api.io.Resource;
import org.kie.internal.io.ResourceFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/01/14
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class FileKnowledgeResource implements Serializable, KnowledgeResource {
    private String content;
    private String path;
    private boolean bpmn2;
    private Resource resource;

    public FileKnowledgeResource(String content, String path, boolean bpmn2, Resource resource) {
        this.content = content;
        this.path = path;
        this.bpmn2 = bpmn2;
        this.resource = resource;
    }

    public FileKnowledgeResource(Resource resource, String path, String fileContent) {
        this.content = fileContent;
        this.path = path;
        this.resource = resource;
        this.bpmn2 = true;
    }

    public FileKnowledgeResource() {
    }

    public static FileKnowledgeResource createFileSystemPathResource(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            String fileContent = IOUtils.toString(inputStream);
            return new FileKnowledgeResource(ResourceFactory.newFileResource(path), path, fileContent);
        } catch (IOException ex) {
            Logger.getLogger(FileKnowledgeResource.class.getName()).log(Level.SEVERE, null, ex);
            throw Throwables.propagate(ex);
        }
    }

    public static FileKnowledgeResource createBPMN2ClassPathResource(String path) {
        FileKnowledgeResource bpmn2Resource = createDRLClassPathResource(path);
        bpmn2Resource.setBpmn2(true);
        return bpmn2Resource;
    }

    public static FileKnowledgeResource createDRLClassPathResource(String path) {
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        InputStream inputStream;
        if (classLoader == null) {
            inputStream = FileKnowledgeResource.class.getResourceAsStream("/" + path);
        } else {
            inputStream = classLoader.getResourceAsStream(path);
        }
        String fileContent = null;
        try {
            fileContent = IOUtils.toString(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(FileKnowledgeResource.class.getName()).log(Level.SEVERE, null, ex);
            throw Throwables.propagate(ex);
        }
        return new FileKnowledgeResource(ResourceFactory.newClassPathResource(path), path, fileContent);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isBpmn2() {
        return bpmn2;
    }

    public void setBpmn2(boolean bpmn2) {
        this.bpmn2 = bpmn2;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DrlRessourceFile{");
        sb.append("fileName='").append(path).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
