import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class CountersTest {
    final long ITERATIONS = 10_000_000L;
    final int THREADS = 10;

    static long run(Thread[] threads) throws Exception {
        long t0 = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        return System.currentTimeMillis() - t0;
    }

    @Test
    public void atomicLongCounterTest() throws Exception {
        AtomicLong counter = new AtomicLong(0);

        Thread[] threads = new Thread[THREADS];
        for (int n = 0; n < THREADS; n++) {
            threads[n] = new Thread(() -> {
                for (long i = 0; i < ITERATIONS; i++) {
                    counter.incrementAndGet();
                }
            });
        }

        long timeMs = run(threads);

        System.out.printf("\nAtomicLong value = %d (expected = %d)", counter.get(), THREADS * ITERATIONS);
        System.out.printf("\nAtomicLong time = %dms", timeMs);
    }

    @Test
    public void longAdderTest() throws Exception {
        LongAdder adder = new LongAdder();

        Thread[] threads = new Thread[THREADS];
        for (int n = 0; n < THREADS; n++) {
            threads[n] = new Thread(() -> {
                for (long i = 0; i < ITERATIONS; i++) {
                    adder.increment();
                }
            });
        }

        long timeMs = run(threads);

        System.out.printf("\nLongAdder sum = %d (expected = %d)", adder.sum(), THREADS * ITERATIONS);
        System.out.printf("\nLongAdder time = %dms", timeMs);
    }
}
