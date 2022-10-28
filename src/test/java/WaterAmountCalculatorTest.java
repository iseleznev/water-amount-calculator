import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class WaterAmountCalculatorTest {

    @Test
    public void calculateWithStartWithAscentFinishWithAscent() {
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
    public void calculateWithFinishWithDescent2() {
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
    public void calculateWithStartWithEqualElements() {
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
    public void calculateWithFinishWithEqualElements() {
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
    public void calculateWithStartWithEqualHillElements() {
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
    public void calculateWithStartWithDescent() {
        final Random random = new Random();
        int hillElement = random.nextInt(32000);
        int pitElement = hillElement - 1;

        final int[] landscape = new int[]{hillElement, pitElement, hillElement};
        final int expected = hillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void calculateWithEqualPitElements() {
        final Random random = new Random();
        int hillElement = random.nextInt(32000);
        int pitElement = hillElement - 1;

        final int[] landscape = new int[]{hillElement, pitElement, pitElement, hillElement};
        final int expected = hillElement - pitElement;
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void calculateWithLeftHillIsHigherThanRight() {
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
    public void calculateWithLeftHillIsLowerThanRight() {
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
    public void calculateWithElementValueLessThanRangeBottom() {
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
    public void calculateWithZeroLandscapeArrayLength() {
        final int[] landscape = new int[0];
        final long actual = new WaterAmountCalculator().calculate(landscape);
        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void calculateWithElementValueGreaterThanRangeTop() {
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
    public void calculateWithElementsCountIsMoreThanAllowCount() {
        final int[] landscape = new int[32001];
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    new WaterAmountCalculator().calculate(landscape);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("The landscape array must have size between 0 and 32000 elements");
    }
}