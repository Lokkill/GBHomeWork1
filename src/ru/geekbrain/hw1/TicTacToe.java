package ru.geekbrain.hw1;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    static char[][] map;
    static int SIZE = 3;

    static final char DOT_EMPTY = '.';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static int chipsToWin = 3;
    static char choicePlayer;
    static char choiceAI;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 0. Выбор размера поля
        choiceSizeMap(scanner);
        // 1. Определение кто каким символом играет
        choiceSymbol(scanner);
        // 2. Инициализация поля
        initMap();
        // 3. Вывод поля
        while (true) {
            printMap();
            // 4. Ход игрока
            actionPlayers(scanner);
            // 5. Ход ИИ
            actionAI();
            // 6 .Проверка на победу
            if (checkVictory(choicePlayer) || checkVictory(choiceAI) || checkDraw()) break;

        }

    }

    static boolean checkDraw() {
        int draw = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[j][i] != DOT_EMPTY) {
                    draw++;
                }
            }
        }
        if (draw == map.length * map.length) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    static void choiceSizeMap(Scanner scanner) {
        System.out.println("Выберите размер поля, значение должно быть не меньше 3. При создании поля больше 3х3 - число фишек для победы будет равно 4.\n" +
                "Введите размер поля одной цифрой, итоговое поле будет сгенерированно пропорционально (макс поле 10х10):");
        SIZE = checkCorrectInt(scanner, 3, 10);
        if (SIZE > 3) chipsToWin = 4;
    }

    static boolean endGame(char player) {
        if (player == choicePlayer) {
            System.out.println("Вы победили!");
            return true;
        }
        if (player == choiceAI) {
            System.out.println("Вы проиграли!");
            return true;
        }
        return false;
    }

    static boolean checkVictory(char player) {
        for (int i = 0; i < map.length; i++) {
            int resY = 0;
            for (char[] chars : map) {
                if (chars[i] == player) {
                    resY++;
                } else if (resY > 0) {
                    resY = 0;
                }
            }
            if (resY == chipsToWin) return endGame(player);
        }
        for (char[] chars : map) {
            int resX = 0;
            for (int j = 0; j < map.length; j++) {
                if (chars[j] == player) {
                    resX++;
                } else if (resX > 0) {
                    resX = 0;
                }
            }
            if (resX == chipsToWin) return endGame(player);
        }
        int diagonalX;
        for (int i = map.length - 1; i > -map.length; i--) {
            int diagonalY = 0;
            int resDiagonal = 0;
            diagonalX = Math.abs(i);
            for (int j = 0; j <= map.length - Math.abs(i) - 1; j++) {
                if (i >= 0) {
                    if (map[diagonalX][diagonalY] == player) {
                        resDiagonal++;
                    } else if (resDiagonal > 0) {
                        resDiagonal = 0;
                    }
                } else {
                    if (map[diagonalY][Math.abs(diagonalX)] == player) {
                        resDiagonal++;
                    } else if (resDiagonal > 0) {
                        resDiagonal = 0;
                    }
                }
                diagonalX++;
                diagonalY++;
            }
            if (resDiagonal == chipsToWin) return endGame(player);
        }

        int diagonalSideX;
        for (int i = map.length - 1; i > -map.length; i--) {
            int diagonalSideY = map.length - 1;
            int sideStartValue = 0;
            int resDiagonal = 0;
            diagonalSideX = Math.abs(i);
            for (int j = map.length; j - Math.abs(i) - 1 >= 0; j--) {
                if (i >= 0) {
                    if (map[diagonalSideX][diagonalSideY] == player) {
                        resDiagonal++;
                    } else if (resDiagonal > 0) {
                        resDiagonal = 0;
                    }
                } else {
                    if (map[sideStartValue][diagonalSideY - Math.abs(i)] == player) {
                        resDiagonal++;
                    } else if (resDiagonal > 0) {
                        resDiagonal = 0;
                    }
                }
                sideStartValue++;
                diagonalSideX++;
                diagonalSideY--;
            }
            if (resDiagonal == chipsToWin) return endGame(player);
        }

        return false;
    }

    static void initMap() {
        map = new char[SIZE][SIZE];
        for (char[] chars : map) {
            Arrays.fill(chars, DOT_EMPTY);
        }
    }

    static void printMap() {
        for (int i = 0; i <= map.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[j][i] + " ");
            }
            System.out.println();
        }
    }

    static void choiceSymbol(Scanner scanner) {
        System.out.println("Каким символом Вы хотите играть? 1 - Х, 2 - О");
        int res = checkCorrectInt(scanner, 1, 2);
        choicePlayer = res == 1 ? DOT_X : DOT_O;
        choiceAI = choicePlayer == DOT_X ? DOT_O : DOT_X;
    }

    public static int checkCorrectInt(Scanner scanner, int leftSide, int rightSide) {
        while (!scanner.hasNextInt()) {
            System.out.println("Необходимо ввести корректное число");
            scanner.nextLine();
        }
        int result = scanner.nextInt();
        if (leftSide <= result && result <= rightSide) {
            return result;
        } else {
            System.out.println("Введите число в диапозоне: " + leftSide + " - " + rightSide);
            scanner.nextLine();
            return checkCorrectInt(new Scanner(System.in), leftSide, rightSide);
        }
    }

    static void actionPlayers(Scanner scanner) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                System.out.println("Введите координату X: ");
                x = checkCorrectInt(scanner, 1, map.length);
            } else {
                System.out.println("Введите координату Y: ");
                y = checkCorrectInt(scanner, 1, map.length);
            }
        }
        setCoordinate(x, y);
    }

    static void setCoordinate(int x, int y) {
        if (map[y - 1][x - 1] == DOT_EMPTY) {
            map[y - 1][x - 1] = choicePlayer;
        } else {
            System.out.println("Координата занята, попробуйте снова");
            actionPlayers(new Scanner(System.in));
        }
    }

    // В общем идея данного алгоритма искать приоритетную строку/столбец/диагональ, то есть высчитывается та, в которой у игрока больше шансов победить
    // Таким образом алгоритм ищет самую близкую к победе комбинацию игрока и делает затычку
    static void actionAI() {

        int[][] interestArr = new int[4][2];
        int interestRowX = 0;
        int interestRowY = 0;
        for (int x = 0; x < map.length; x++) {
            int tempRow = 0;
            int countAI = 0;
            int column = 0;
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == choicePlayer) {
                    tempRow++;
                }
                if (map[x][y] == choiceAI) {
                    countAI++;
                }
                column = x;
            }
            if (tempRow + countAI == SIZE) {
                continue;
            } else {
                if (tempRow > interestRowY) {
                    interestArr[0][0] = column;
                    interestArr[0][1] = tempRow;
                    interestRowY = tempRow;
                }
            }
        }

        for (int x = 0; x < map.length; x++) {
            int tempRow = 0;
            int countAI = 0;
            int row = 0;
            for (int y = 0; y < map[x].length; y++) {
                if (map[y][x] == choicePlayer) {
                    tempRow++;
                }
                if (map[y][x] == choiceAI) {
                    countAI++;
                }
                row = x;
            }
            if (tempRow + countAI == SIZE) {
                continue;
            } else {
                if (tempRow > interestRowX) {
                    interestArr[1][0] = row;
                    interestArr[1][1] = tempRow;
                    interestRowX = tempRow;
                }
            }
        }

        for (int r = 0; r < 1; r++) {
            int tempRow = 0;
            int countAI = 0;
            for (int i = 0; i < map.length; i++) {

                //int diagonal = 0;
                if (map[i][i] == choicePlayer) {
                    tempRow++;
                }
                if (map[i][i] == choiceAI) {
                    countAI++;
                }
            }
            if (tempRow + countAI < SIZE) {
                interestArr[2][1] = tempRow;
            }
        }

        for (int r = 0; r < 1; r++) {
            int tempRow = 0;
            int countAI = 0;
            for (int i = 0; i < map.length; i++) {

                if (map[i][map.length - 1 - i] == choicePlayer) {
                    tempRow++;
                }
                if (map[i][map.length - 1 - i] == choiceAI) {
                    countAI++;
                }

            }
            if (tempRow + countAI < SIZE) {
                interestArr[3][1] = tempRow;
            }
        }

        int numberColumnOrRow;
        char typeColumnOrRow;
        // first value - column or row, second value - it's column or row
        if (interestArr[0][1] >= interestArr[1][1] && interestArr[0][1] >= interestArr[2][1] && interestArr[0][1] >= interestArr[3][1]) {
            numberColumnOrRow = interestArr[0][0];
            typeColumnOrRow = 'y';
        } else if (interestArr[1][1] >= interestArr[2][1] && interestArr[1][1] >= interestArr[3][1]) {
            numberColumnOrRow = interestArr[1][0];
            typeColumnOrRow = 'x';
        } else if (interestArr[2][1] >= interestArr[3][1]) {
            numberColumnOrRow = interestArr[2][0];
            typeColumnOrRow = 'd';
        } else {
            numberColumnOrRow = interestArr[3][0];
            typeColumnOrRow = 's';
        }

        if (typeColumnOrRow == 'x') {
            for (int x = 0; x < map.length; x++) {
                if (map[x][numberColumnOrRow] == DOT_EMPTY) {
                    map[x][numberColumnOrRow] = choiceAI;
                    System.out.println("Компьютер сделал ход по координатам: " + x + "-" + numberColumnOrRow);
                    break;
                }
            }
        } else if (typeColumnOrRow == 'y') {
            for (int y = 0; y < map.length; y++) {
                if (map[numberColumnOrRow][y] == DOT_EMPTY) {
                    map[numberColumnOrRow][y] = choiceAI;
                    System.out.println("Компьютер сделал ход по координатам: " + numberColumnOrRow + "-" + y);
                    break;
                }
            }
        } else if (typeColumnOrRow == 'd') {
            for (int i = 0; i < map.length; i++) {
                if (map[i][i] == DOT_EMPTY) {
                    map[i][i] = choiceAI;
                    System.out.println("Компьютер сделал ход по координатам: " + i + "-" + i);
                    break;
                }
            }
        } else {
            for (int i = 0; i < map.length; i++) {
                if (map[i][map.length - 1 - i] == DOT_EMPTY) {
                    map[i][map.length - 1 - i] = choiceAI;
                    System.out.println("Компьютер сделал ход по координатам: " + i + "-" + (map.length - 1 - i));
                    break;
                }
            }
        }

    }

}
