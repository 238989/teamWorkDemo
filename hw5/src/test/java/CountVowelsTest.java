import junit.framework.TestCase;

public class CountVowelsTest extends TestCase {

    public void testCountVowel() {
        assertEquals(CountVowels.countVowel("apple"),2);
    }

    public void testCountConstants() {
        assertEquals(CountVowels.countConstants("apple"),3);
    }
}