package net.filipvanlaenen.iacaj.producer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import net.filipvanlaenen.iacaj.BooleanFunction;

/**
 * Unit tests on the <code>RotateProducer</code> class.
 */
public class RotateProducerTest {
    /**
     * The magic number 3.
     */
    private static final int THREE = 3;

    /**
     * Verifies that the producer can produce a Boolean function implementing ROTATE
     * +1 for words of length 3.
     */
    @Test
    public void shouldProduceRotateOneRightFunctionWithWordLength3() {
        Producer producer = new RotateProducer(Arrays.asList(new Integer[] {THREE, 1}));
        BooleanFunction booleanFunction = producer.produce();
        StringBuilder sb = new StringBuilder();
        sb.append("o1 = i3\n");
        sb.append("o2 = i1\n");
        sb.append("o3 = i2");
        String expected = sb.toString();
        assertEquals(expected, booleanFunction.toString());
    }
}
