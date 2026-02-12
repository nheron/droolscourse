package org.chtijbug.drools.kieserver.extension;

import org.chtijbug.drools.SessionContext;

public interface KieServerLoggingDefinition {

    void OnCreateKieBase();

    void OnFireAllrulesStart(String groupID, String artifactId, String version, Object inputDataObject);

    void OnFireAllrulesEnd(String groupID, String artifactId, String version, Object outputDataObject, SessionContext sessionLogging);

    void OnDisposeKieBase();
}
