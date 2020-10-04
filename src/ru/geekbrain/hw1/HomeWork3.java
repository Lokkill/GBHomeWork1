package ru.geekbrain.hw1;

import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class HomeWork3 {
    public static void main(String[] args) {
        /*
         * Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
         * При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
         * После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
         */
        guessTheNumber(9);

        /*
        * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
        * сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
        * Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
        */
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        guessTheWord(words);
    }


    public static void guessTheNumber(int bound) {
        Scanner scanner = new Scanner(System.in);
        Random r = new Random();
        int playNumber = r.nextInt(bound);
        int i = 0;
        System.out.println("Игра 'Угадай число'! У Вас 3 попытки!");
        while (i < 3) {
            stepIdent(i);
            if (scanner.hasNextInt()) {
                int res = scanner.nextInt();
                if (playNumber == res) {
                    System.out.println("Вы угадали число! Поздравляем!");
                    if (oneMoreGame(scanner)) guessTheNumber(bound);
                    //oneMoreGame(scanner);
                } else if (i == 2) {
                    System.out.println("Вы не угадали число, повезёт в другой раз!");
                    if (oneMoreGame(scanner)) guessTheNumber(bound);
                } else {
                    if (playNumber > res) {
                        System.out.println("Ваше число меньше, чем загаданное число!");
                    } else {
                        System.out.println("Ваше число больше, чем загаданное число!");
                    }
                    i++;
                }
            } else {
                System.out.println("Вы ввели некорректную строку! Необходимо ввести число, попробуйте снова");
                scanner = new Scanner(System.in); // не совсем понятно как в данном случае поступать, так как встроенные методы не помогали с этой проблемой,
                // если это не написать, то при следующем цикле не запрашивается ввод строки
            }
        }
    }

    private static boolean oneMoreGame(Scanner scanner) {
        System.out.println("Повторить игру ещё раз? 1 - да / 0 - нет");
        if (scanner.hasNextInt()) {
            switch (scanner.nextInt()) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    return true;
                default:
                    System.out.println("Вы ввели некорректное число, попробуйте ещё раз");
                    oneMoreGame(scanner);
            }
        } else {
            System.out.println("Необходимо ввести 0 или 1");
            oneMoreGame(new Scanner(System.in));
        }
        return false;
    }

    private static void stepIdent(int step) {
        switch (step) {
            case 0:
                System.out.println("Первая попытка, введите число: ");
                break;
            case 1:
                System.out.println("Вторая попытка, введите число: ");
                break;
            case 2:
                System.out.println("Последняя попытка, введите число: ");
                break;
        }
    }

    public static void guessTheWord(String[] arrStrings){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String mask = "###############";
        String hiddenWord = arrStrings[random.nextInt(arrStrings.length)];
        hiddenWord = hiddenWord.concat(mask.substring(0, hiddenWord.length()));
        System.out.println("Игра 'Угадай слово!'. Было загадано слово, которое Вам необходимо отгадать. Тема - Фрукты и Овощи. Удачи!");
        char[] guessWord = mask.toCharArray();
        while (true){
            System.out.println("Введите предполагаемое слово: ");
            String word = scanner.nextLine().toLowerCase();
            for (int i = 0; i < word.length(); i++){
                if (guessWord[i] == '#' && hiddenWord.charAt(i) == word.charAt(i)){
                    guessWord[i] = word.charAt(i);
                }
            }
            if (String.valueOf(guessWord).replaceAll("#", "").equals(hiddenWord.replaceAll("#", ""))){
                if (oneMoreGame(scanner)) guessTheWord(arrStrings);
            } else {
                System.out.println("Совпавшие буквы: " + String.valueOf(guessWord));
            }
        }
    }
}
