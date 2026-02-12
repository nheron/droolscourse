/**
 *
 */
package org.chtijbug.drools.runtime.test;

/**
 * @author Bertrand Gressier
 * @date 12 déc. 2011
 * <p/>
 * <p/>
 * Source: http://legacy.drools.codehaus.org/Fibonacci+Example
 */
public class Fibonacci {
    private  int sequence;
    private long value;

    public Fibonacci(int sequence) {
        this.sequence = sequence;
        this.value = -1;
    }

    public Fibonacci(int sequence, long value) {
        this.sequence = sequence;
        this.value = value;
    }

    public Fibonacci() {
    }

    public int getSequence() {
        return this.sequence;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Fibonacci(" + this.sequence + "/" + this.value + ")";
    }
}
