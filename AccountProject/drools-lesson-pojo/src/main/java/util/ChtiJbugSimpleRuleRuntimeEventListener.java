package util;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

public class ChtiJbugSimpleRuleRuntimeEventListener implements RuleRuntimeEventListener {
    public void objectInserted(ObjectInsertedEvent event) {
        System.out.println("Object inserted \n"
                + event.getObject().toString());
    }

    public void objectUpdated(ObjectUpdatedEvent event) {
        System.out.println("Object was updated \n"
                + "new Content \n" + event.getObject().toString());
    }

    public void objectDeleted(ObjectDeletedEvent event) {
        System.out.println("Object retracted \n"
                + event.getOldObject().toString());
    }
}
