package org.chtijbug.drools.runtime.impl;

import org.kie.api.event.rule.AgendaEventListener;

public interface RuleHandlerListenerInterface extends AgendaEventListener {
    boolean isMaxNumerExecutedRulesReached();

    int getNbRuleFired();
}
