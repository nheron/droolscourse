package org.chtijbug.drools.kieserver.extension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KieServerAddOnElement {

    private List<KieServerGlobalVariableDefinition> kieServerGlobalVariableDefinitions = null;
    private List<KieServerLoggingDefinition> kieServerLoggingDefinitions = null;
    private List<KieServerListenerDefinition> kieServerListenerDefinitions = null;
    private Map<String, Object> globals = new HashMap<>();

    public KieServerAddOnElement(List<KieServerGlobalVariableDefinition> kieServerGlobalVariableDefinitions, List<KieServerLoggingDefinition> kieServerLoggingDefinitions, List<KieServerListenerDefinition> kieServerListenerDefinitions) {
        this.kieServerGlobalVariableDefinitions = kieServerGlobalVariableDefinitions;
        this.kieServerLoggingDefinitions = kieServerLoggingDefinitions;
        this.kieServerListenerDefinitions = kieServerListenerDefinitions;
    }

    public List<KieServerGlobalVariableDefinition> getKieServerGlobalVariableDefinitions() {
        return kieServerGlobalVariableDefinitions;
    }

    public List<KieServerLoggingDefinition> getKieServerLoggingDefinitions() {
        return kieServerLoggingDefinitions;
    }

    public List<KieServerListenerDefinition> getKieServerListenerDefinitions() {
        return kieServerListenerDefinitions;
    }


    public Map<String, Object> getGlobals() {
        return globals;
    }
}
