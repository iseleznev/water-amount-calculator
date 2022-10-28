import java.util.Arrays;

public class MainClass {

    public static void main(String... args) {
        if (args.length == 0 || args[0].isBlank() || args[0].isEmpty()) {
            System.out.println("Need to specify argument as numbers divided by commas");
            return;
        }
        final String[] lines = args[0].split(",");
        final int[] landscape = Arrays.stream(lines).map(
                element -> Integer.parseInt(element.trim())
        ).mapToInt(Integer::intValue).toArray();
        final long waterAmount = new WaterAmountCalculator().calculate(landscape);
        System.out.println(waterAmount);
    }
}
