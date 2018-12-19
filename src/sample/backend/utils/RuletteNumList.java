package sample.backend.utils;


import sample.backend.algorithm.Color;

public class RuletteNumList {

    private static NumberClass[] numbers;

    static {
        numbers = new NumberClass[37];
        numbers[0] = new NumberClass(0, Color.GREEN, roundNumber(360.0 / 37.0 * 36.5) + 0.1, roundNumber(360.0 / 37.0 * 0.5) - 0.0001);
        numbers[1] = new NumberClass(1, Color.RED, roundNumber(360.0 / 37.0 * 22.5) + 0.1, roundNumber(360.0 / 37.0 * 23.5) - 0.0001);
        numbers[2] = new NumberClass(2, Color.BLACK, roundNumber(360.0 / 37.0 * 5.5) + 0.1, roundNumber(360.0 / 37.0 * 6.5) - 0.0001);
        numbers[3] = new NumberClass(3, Color.RED, roundNumber(360.0 / 37.0 * 34.5) + 0.1, roundNumber(360.0 / 37.0 * 35.5) - 0.0001);
        numbers[4] = new NumberClass(4, Color.BLACK, roundNumber(360.0 / 37.0 * 3.5) + 0.1, roundNumber(360.0 / 37.0 * 4.5) - 0.0001);
        numbers[5] = new NumberClass(5, Color.RED, roundNumber(360.0 / 37.0 * 18.5) + 0.1, roundNumber(360.0 / 37.0 * 19.5) - 0.0001);
        numbers[6] = new NumberClass(6, Color.BLACK, roundNumber(360.0 / 37.0 * 9.5) + 0.1, roundNumber(360.0 / 37.0 * 10.5) - 0.0001);
        numbers[7] = new NumberClass(7, Color.RED, roundNumber(360.0 / 37.0 * 30.5) + 0.1, roundNumber(360.0 / 37.0 * 31.5) - 0.0001);
        numbers[8] = new NumberClass(8, Color.BLACK, roundNumber(360.0 / 37.0 * 15.5) + 0.1, roundNumber(360.0 / 37.0 * 16.5) - 0.0001);
        numbers[9] = new NumberClass(9, Color.RED, roundNumber(360.0 / 37.0 * 26.5) + 0.1, roundNumber(360.0 / 37.0 * 27.5) - 0.0001);
        numbers[10] = new NumberClass(10, Color.BLACK, roundNumber(360.0 / 37.0 * 17.5) + 0.1, roundNumber(360.0 / 37.0 * 18.5) - 0.0001);
        numbers[11] = new NumberClass(11, Color.BLACK, roundNumber(360.0 / 37.0 * 13.5) + 0.1, roundNumber(360.0 / 37.0 * 14.5) - 0.0001);
        numbers[12] = new NumberClass(12, Color.RED, roundNumber(360.0 / 37.0 * 32.5) + 0.1, roundNumber(360.0 / 37.0 * 33.5) - 0.0001);
        numbers[13] = new NumberClass(13, Color.BLACK, roundNumber(360.0 / 37.0 * 11.5) + 0.1, roundNumber(360.0 / 37.0 * 12.5) - 0.0001);
        numbers[14] = new NumberClass(14, Color.RED, roundNumber(360.0 / 37.0 * 24.5) + 0.1, roundNumber(360.0 / 37.0 * 25.5) - 0.0001);
        numbers[15] = new NumberClass(15, Color.BLACK, roundNumber(360.0 / 37.0 * 1.5) + 0.1, roundNumber(360.0 / 37.0 * 2.5) - 0.0001);
        numbers[16] = new NumberClass(16, Color.RED, roundNumber(360.0 / 37.0 * 20.5) + 0.1, roundNumber(360.0 / 37.0 * 21.5) - 0.0001);
        numbers[17] = new NumberClass(17, Color.BLACK, roundNumber(360.0 / 37.0 * 7.5) + 0.1, roundNumber(360.0 / 37.0 * 8.5) - 0.0001);
        numbers[18] = new NumberClass(18, Color.RED, roundNumber(360.0 / 37.0 * 28.5) + 0.1, roundNumber(360.0 / 37.0 * 29.5) - 0.0001);
        numbers[19] = new NumberClass(19, Color.RED, roundNumber(360.0 / 37.0 * 2.5) + 0.1, roundNumber(360.0 / 37.0 * 3.5) - 0.0001);
        numbers[20] = new NumberClass(20, Color.BLACK, roundNumber(360.0 / 37.0 * 23.5) + 0.1, roundNumber(360.0 / 37.0 * 24.5) - 0.0001);
        numbers[21] = new NumberClass(21, Color.RED, roundNumber(360.0 / 37.0 * 4.5) + 0.1, roundNumber(360.0 / 37.0 * 5.5) - 0.0001);
        numbers[22] = new NumberClass(22, Color.BLACK, roundNumber(360.0 / 37.0 * 27.5) + 0.1, roundNumber(360.0 / 37.0 * 28.5) - 0.0001);
        numbers[23] = new NumberClass(23, Color.RED, roundNumber(360.0 / 37.0 * 16.5) + 0.1, roundNumber(360.0 / 37.0 * 17.5) - 0.0001);
        numbers[24] = new NumberClass(24, Color.BLACK, roundNumber(360.0 / 37.0 * 19.5) + 0.1, roundNumber(360.0 / 37.0 * 20.5) - 0.0001);
        numbers[25] = new NumberClass(25, Color.RED, roundNumber(360.0 / 37.0 * 6.5) + 0.1, roundNumber(360.0 / 37.0 * 7.5) - 0.0001);
        numbers[26] = new NumberClass(26, Color.BLACK, roundNumber(360.0 / 37.0 * 35.5) + 0.1, roundNumber(360.0 / 37.0 * 36.5) - 0.0001);
        numbers[27] = new NumberClass(27, Color.RED, roundNumber(360.0 / 37.0 * 10.5) + 0.1, roundNumber(360.0 / 37.0 * 11.5) - 0.0001);
        numbers[28] = new NumberClass(28, Color.BLACK, roundNumber(360.0 / 37.0 * 31.5) + 0.1, roundNumber(360.0 / 37.0 * 32.5) - 0.0001);
        numbers[29] = new NumberClass(29, Color.BLACK, roundNumber(360.0 / 37.0 * 29.5) + 0.1, roundNumber(360.0 / 37.0 * 30.5) - 0.0001);
        numbers[30] = new NumberClass(30, Color.RED, roundNumber(360.0 / 37.0 * 14.5) + 0.1, roundNumber(360.0 / 37.0 * 15.5) - 0.0001);
        numbers[31] = new NumberClass(31, Color.BLACK, roundNumber(360.0 / 37.0 * 25.5) + 0.1, roundNumber(360.0 / 37.0 * 26.5) - 0.0001);
        numbers[32] = new NumberClass(32, Color.RED, roundNumber(360.0 / 37.0 * 0.5) + 0.1, roundNumber(360.0 / 37.0 * 1.5) - 0.0001);
        numbers[33] = new NumberClass(33, Color.BLACK, roundNumber(360.0 / 37.0 * 21.5) + 0.1, roundNumber(360.0 / 37.0 * 22.5) - 0.0001);
        numbers[34] = new NumberClass(34, Color.RED, roundNumber(360.0 / 37.0 * 8.5) + 0.1, roundNumber(360.0 / 37.0 * 9.5) - 0.0001);
        numbers[35] = new NumberClass(35, Color.BLACK, roundNumber(360.0 / 37.0 * 33.5) + 0.1, roundNumber(360.0 / 37.0 * 34.5) - 0.0001);
        numbers[36] = new NumberClass(36, Color.RED, roundNumber(360.0 / 37.0 * 12.5) + 0.1, roundNumber(360.0 / 37.0 * 13.5) - 0.0001);
    }

    private static double roundNumber(double num){
        return Math.round(num * 10000.0) / 10000.0;
    }

    public static NumberClass getNumber(int index){
        return numbers[index];
    }
}
