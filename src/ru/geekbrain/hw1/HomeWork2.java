package ru.geekbrain.hw1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HomeWork2 {
    public static void main(String[] args) {
        replacing1And0();
        fillMassive();
        valueLess6Multiply2();
        twoDimensionalMassive();
        minAndMaxValueInArray();
        int[] mas = {10, 1, 2, 2, 2, 1, 2, 2};
        int[] mas2 = {1, 1, 1, 2, 1};
        boolean task6 = isSumToHalfArray(mas);
        System.out.println(task6);
        System.out.println("--------------------------");
        int[] massive = {0, 1, 2, 3, 4, 5, 6, 7};
        //int[] emptyArr = new int[0];
        shiftValuesMassive(massive, 2);
        shiftValuesMassive(massive, -2);
        shiftValuesMassiveVer2(massive, -3);

    }

    /*
     * Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0
     */
    private static void replacing1And0() {
        int[] mas = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] == 0) {
                mas[i] = 1;
            } else {
                mas[i] = 0;
            }
        }
        printArray(mas);
    }

    /*
     * Задать пустой целочисленный массив размером 8.
     * С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21
     */
    private static void fillMassive() {
        int[] massive = new int[8];
        for (int i = 0; i < massive.length; i++) {
            massive[i] = i * 3;
        }
        printArray(massive);
    }

    public static void printArray(int[] mas) {
        System.out.println(Arrays.toString(mas));
        System.out.println("--------------------------");
    }

    /*
     * Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
     * пройти по нему циклом, и числа меньшие 6 умножить на 2
     */
    private static void valueLess6Multiply2() {
        int[] massive = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < massive.length; i++) {
            if (massive[i] < 6) {
                massive[i] = massive[i] * 2;
            }
        }
        printArray(massive);
    }


    /*
     * Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
     * и с помощью цикла(-ов) заполнить его диагональные элементы единицами
     */
    private static void twoDimensionalMassive() {
        int[][] massive = new int[6][6];
        for (int i = 0; i < massive.length; i++) {
            massive[i][i] = 1;
            massive[i][(massive.length - 1) - i] = 1;
        }
        for (int[] inMas : massive) {
            System.out.println(Arrays.toString(inMas));
        }
        System.out.println("--------------------------");
    }

    /*
     * Задать одномерный массив и найти в нем минимальный и максимальный элементы
     */
    // Сразу хочу сказать, что определённые знания у меня есть, поэтому тут и интернет не нужен
    private static void minAndMaxValueInArray() {
        int[] massive = {1, 5, 3, 2, 11, -4, 5, 2, 4, 8, 9, 1};
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int element : massive) {
            if (minValue > element) minValue = element;
            if (maxValue < element) maxValue = element;
        }
        System.out.printf("Max value: %d, Min value : %d\n", maxValue, minValue);
        System.out.println("--------------------------");
        // Более правильный вариант для Java 8
        int min = IntStream.of(massive).min().getAsInt();
        int max = IntStream.of(massive).max().getAsInt();
    }

    /*
     * Написать метод, в который передается не пустой одномерный целочисленный массив,
     * метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
     * Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
     * граница показана символами ||, эти символы в массив не входят.
     */
    private static boolean isSumToHalfArray(int[] massive) {
        if (massive.length < 2) {
            errorIncorrectArray();
            return false;
        }
        int leftSide = 0;
        int rightSide = 0;
        int exValue = massive.length - 1;
        for (int i = 0; i < massive.length; ) {
            if (exValue + 1 != i) {
                if (i == 0) {
                    leftSide += massive[i];
                    rightSide += massive[exValue];
                    exValue--;
                    i++;
                    continue;
                }
                if (leftSide == rightSide) {
                    return true;
                }
                if (leftSide < rightSide) {
                    leftSide += massive[i];
                    i++;
                    continue;
                }
                if (leftSide > rightSide) {
                    rightSide += massive[exValue];
                    exValue--;
                    continue;
                }
                i++;
            } else {
                return leftSide == rightSide;
            }
        }
        return false;
    }

    /*
     * Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
     * при этом метод должен сместить все элементы массива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами
     */
    // - - влево
    // + - вправо
    // int[] massive = {0,1,2,3,4,5,6,7};
    // int offs = 4;
    private static void errorIncorrectArray() {
        System.out.println("Incorrect array");
        System.out.println("--------------------------");
    }

    private static void shiftValuesMassive(int[] primMassive, int offset) {
        if (primMassive.length == 0) {
            errorIncorrectArray();
            return;
        }
        int[] massive = primMassive.clone(); // Сделанно для того, чтобы не изменялся изначальный массив
        int intermediateValue = 0;
        int tempValue = 0;
        if (offset > 0) {
            for (int i = 0; i < offset; i++) {
                for (int j = 0; j < massive.length; j++) {
                    if (j == 0) {
                        intermediateValue = massive[j];
                        continue;
                    }
                    if (j == massive.length - 1) {
                        tempValue = massive[j];
                        massive[j] = intermediateValue;
                        massive[0] = tempValue;
                        continue;
                    }
                    tempValue = massive[j];
                    massive[j] = intermediateValue;
                    intermediateValue = tempValue;
                }
            }
        } else {
            for (int i = 0; i > offset; i--) {
                for (int j = massive.length - 1; j >= 0; j--) {
                    if (j == massive.length - 1) {
                        intermediateValue = massive[j];
                        continue;
                    }
                    if (j == 0) {
                        tempValue = massive[j];
                        massive[j] = intermediateValue;
                        massive[massive.length - 1] = tempValue;
                        continue;
                    }
                    tempValue = massive[j];
                    massive[j] = intermediateValue;
                    intermediateValue = tempValue;
                }
            }
        }
        printArray(massive);
//        for (int v : massive) {
//            System.out.println(v);
//        }
    }

    // Вариант 2, не совсем понятно что должно делать при отрицательном сдвиге, первый вариант начинает свдиг в обратную сторону,
    // второй вариант запасной, если знак должен игнорироваться
    private static void shiftValuesMassiveVer2(int[] primMassive, int offset) {
        if (primMassive.length == 0) {
            errorIncorrectArray();
            return;
        }
        int[] massive = primMassive.clone(); // Сделанно для того, чтобы не изменялся изначальный массив
        int intermediateValue = 0;
        int tempValue = 0;
        for (int i = 0; i < Math.abs(offset); i++) {
            for (int j = 0; j < massive.length; j++) {
                if (j == 0) {
                    intermediateValue = massive[j];
                    continue;
                }
                if (j == massive.length - 1) {
                    tempValue = massive[j];
                    massive[j] = intermediateValue;
                    massive[0] = tempValue;
                    continue;
                }
                tempValue = massive[j];
                massive[j] = intermediateValue;
                intermediateValue = tempValue;
            }
        }
        printArray(massive);
    }
}
