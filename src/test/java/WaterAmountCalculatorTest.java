import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class WaterAmountCalculatorTest {

    @Test
    public void testCalculateWithStartWithAscentFinishWithAscent() {
        final Random random = new Random();
        int startElement = random.nextInt(31999);
        int hillElement = startElement + 1;
        int pitElement = hillElement - 1;

        final int[] landscape = new int[]{startElement, hillElement, pitElement, hillElement};
        final int expected = hillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateWithFinishWithDescent() {
        final Random random = new Random();
        int borderElement = random.nextInt(31999);
        int hillElement = borderElement + 1;
        int pitElement = hillElement - 1;

        final int[] landscape = new int[]{hillElement, pitElement, hillElement, borderElement};
        final int expected = hillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateWithStartWithEqualElements() {
        final Random random = new Random();
        int borderElement = random.nextInt(31999);
        int hillElement = borderElement + 1;
        int pitElement = hillElement - 1;

        final int[] landscape = new int[]{borderElement, borderElement, hillElement, pitElement, hillElement, borderElement};
        final int expected = hillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateWithFinishWithEqualElements() {
        final Random random = new Random();
        int borderElement = random.nextInt(31999);
        int hillElement = borderElement + 1;
        int pitElement = hillElement - 1;

        final int[] landscape = new int[]{hillElement, pitElement, hillElement, borderElement, borderElement};
        final int expected = hillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateWithStartWithEqualHillElements() {
        final Random random = new Random();
        int borderElement = random.nextInt(31999);
        int hillElement = borderElement + 1;
        int pitElement = hillElement - 1;

        final int[] landscape = new int[]{hillElement, hillElement, pitElement, hillElement, borderElement};
        final int expected = hillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateWithStartWithDescent() {
        final Random random = new Random();
        int hillElement = random.nextInt(32000);
        int pitElement = hillElement - 1;

        final int[] landscape = new int[]{hillElement, pitElement, hillElement};
        final int expected = hillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateWithEqualPitElements() {
        final Random random = new Random();
        int hillElement = random.nextInt(32000);
        int pitElement = hillElement - 1;

        final int[] landscape = new int[]{hillElement, pitElement, pitElement, hillElement};
        final int expected = hillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateWithLeftHillIsHigherThanRight() {
        final Random random = new Random();
        int higherHillElement = random.nextInt(31999) + 2;
        int lowerHillElement = higherHillElement - 1;
        int pitElement = lowerHillElement - 1;

        final int[] landscape = new int[]{higherHillElement, pitElement, lowerHillElement};
        final int expected = lowerHillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateWithLeftHillIsLowerThanRight() {
        final Random random = new Random();
        int higherHillElement = random.nextInt(31999) + 2;
        int lowerHillElement = higherHillElement - 1;
        int pitElement = lowerHillElement - 1;

        final int[] landscape = new int[]{lowerHillElement, pitElement, higherHillElement};
        final int expected = lowerHillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateWithElementValueLessThanRangeBottom() {
        int incorrectElementValue = -1;
        final int[] landscape = new int[]{incorrectElementValue};
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    new WaterAmountCalculator().calculate(landscape);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("An element of landscape array must have value between 0 and 32000 but found value " + incorrectElementValue);
    }

    @Test
    public void testCalculateWithZeroLandscapeArrayLength() {
        final int[] landscape = new int[0];
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void testCalculateWithElementValueGreaterThanRangeTop() {
        int incorrectElementValue = new Random().nextInt() + 32001;
        final int[] landscape = new int[]{incorrectElementValue};
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    new WaterAmountCalculator().calculate(landscape);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("An element of landscape array must have value between 0 and 32000 but found value " + incorrectElementValue);
    }

    @Test
    public void testCalculateWithElementsCountIsMoreThanAllowCount() {
        final int[] landscape = new int[32001];
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    new WaterAmountCalculator().calculate(landscape);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("The landscape array must have size between 0 and 32000 elements");
    }
}