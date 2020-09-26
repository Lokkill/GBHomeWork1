package ru.geekbrain.hw1;

public class FirstClass {
    // Task #1
    public static void main(String[] args) {
        // Task #2
        byte b = 127;
        short s = 32767;
        int i = 214748367;
        long l = 4611686018427387903L;
        float f = 1.2F;
        double d = 1.2D;
        char c = 'a';
        boolean bl = true;
        // End Task #2

        int task3 = calculateResult(5, 4,3,2);
        boolean task4 = correctValue1(6,7);
        boolean task4v2 = correctValue2( 8,6);
        positiveOrNegativeValue(0);
        positiveOrNegativeValue(-10);
        boolean task6 = negativeValue(-10);
        greetingUser("Андрей");
        leapYear(2000);
        leapYear(2006);
        leapYear(1900);
        leapYear(1916);
    }
    // Task #3
    static int calculateResult(int a, int b, int c, int d){
        return a * (b + (c / d));
    }

    // Task #4
    static boolean correctValue1(int a, int b){
        int c = a + b;
        if (c >= 10 && c <= 20) {
            return true;
        } else {
            return false;
        }
    }
    // Task #4 v2
    static boolean correctValue2(int a, int b){
        int c = a + b;
        return c >= 10 && c <= 20;
    }

    // Task #5
    static void positiveOrNegativeValue(int value){
        if (value >= 0 ){
            System.out.println("It's a positive value");
        } else if (value < 0) { // Это использовал просто для демонстрации ветвления 'else if', хотя здесь подойдёт и просто 'else'
            System.out.println("It's a negative value");
        }
    }

    // Task #6
    static boolean negativeValue(int value){
        return value < 0;
    }

    // Task #7
    static void greetingUser(String name){
        System.out.println("Привет, " + name);
    }
    // Task #8
    static void leapYear(int year){
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)){
            System.out.println("It's a leap year!");
        } else {
            System.out.println("It's a not leap year :(");
        }
    }
}
