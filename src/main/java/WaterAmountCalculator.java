public class WaterAmountCalculator {
    public long calculate(int[] landscape) {
        if (landscape.length > 32000) {
            throw new IllegalArgumentException("The landscape array must have size between 0 and 32000 elements");
        }
        int previousDeltaSign = 0;
        int currentDeltaSign = 0;
        int pitWidth = 0;
        int leftHillHeight = 0;
        int rightHillHeight = 0;
        int pitWaterAmount = 0;
        long totalWaterAmount = 0;
        boolean isHill = false;
        for (int i = 0; i < landscape.length; i++) {
            if (landscape[i] < 0 || landscape[i] > 32000) {
                throw new IllegalArgumentException("An element of landscape array must have value between 0 and 32000 but found value " + landscape[i]);
            }
            if (i > 0 && landscape[i] != landscape[i - 1]) {
                previousDeltaSign = currentDeltaSign;
                currentDeltaSign = landscape[i] > landscape[i - 1] ? 1 : -1;
            }
            if (previousDeltaSign > 0 && currentDeltaSign < 0) {
                if (rightHillHeight > 0) {
                    leftHillHeight = rightHillHeight;
                }
                rightHillHeight = landscape[i - 1];
                isHill = true;
            }
            if (i == landscape.length - 1 && currentDeltaSign > 0) {
                if (rightHillHeight > 0) {
                    leftHillHeight = rightHillHeight;
                }
                rightHillHeight = landscape[i];
                isHill = true;
            }
            if (previousDeltaSign == 0 && currentDeltaSign < 0) {
                leftHillHeight = landscape[i - 1];
                isHill = true;
            }
            if (isHill && leftHillHeight > 0 && rightHillHeight > 0) {
                if (leftHillHeight > rightHillHeight) {
                    pitWaterAmount -= pitWidth * (leftHillHeight - rightHillHeight);
                }
                totalWaterAmount += pitWaterAmount;
                pitWaterAmount = 0;
                pitWidth = 0;
                isHill = false;
            }
            if (landscape[i] < rightHillHeight) {
                pitWaterAmount += rightHillHeight - landscape[i];
                pitWidth++;
            }
            if (landscape[i] < leftHillHeight && rightHillHeight == 0) {
                pitWaterAmount += leftHillHeight - landscape[i];
                pitWidth++;
            }
        }
        return totalWaterAmount;
    }
}
