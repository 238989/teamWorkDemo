import junit.framework.TestCase;

public class FibonacciTest extends TestCase {

    public void testFetFib() {
        assertEquals(5,Fibonacci.getFib(5));
    }
}