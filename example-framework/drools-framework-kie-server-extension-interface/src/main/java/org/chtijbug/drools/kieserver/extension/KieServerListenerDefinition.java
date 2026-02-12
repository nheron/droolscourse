package org.chtijbug.drools.kieserver.extension;

import org.kie.api.runtime.KieSession;

public interface KieServerListenerDefinition {

    void OnCreateKieBase();

    void OnCreateKieSession(KieSession kieSession);

    void OnDisposeKieBase();

}
