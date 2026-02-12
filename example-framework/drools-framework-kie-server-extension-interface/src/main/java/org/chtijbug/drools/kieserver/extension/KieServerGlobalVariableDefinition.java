package org.chtijbug.drools.kieserver.extension;

import java.util.Map;

public interface KieServerGlobalVariableDefinition {
    void OnCreateKieBase(Map<String, Object> globals);

    void OnDisposeKieBase();
}
