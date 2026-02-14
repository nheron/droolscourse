package serialization;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

/**
 * Serializes the WorkingMemory and stores it to be reloaded by another engine.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public interface KieSessionLoader {

	public abstract void save(KieSession kieSession);
	
	public abstract KieSession load();
	
	public abstract KieSession load(KieBase kieBase);

}
