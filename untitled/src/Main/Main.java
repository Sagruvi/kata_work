package Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        calc(expression);

    }

    static String calc(String expression) {
        char[] character = expression.replaceAll(" ", "").toCharArray();
        int operation = 0;
        for (char number : character) {
            switch (number) {
                case '+':
                    operation += 1;
                    break;
                case '-':
                    operation += 2;
                    break;
                case '*':
                    operation += 3;
                    break;
                case '/':
                    operation += 4;
                    break;
                default:
                    operation += 0;
            }
        }
        isOneOperator(character.toString());
        String substr = String.valueOf(character);
        String[] substr1 = substr.split("[+-/*]");
        String left_part = substr1[0];
        String right_part = substr1[1];
        boolean isRomanNumber = false;
        int left = 0;
        int right = 0;
         if (isRoman(left_part) && isRoman(right_part)) {
            left += convertToArabic(left_part);
            right += convertToArabic(right_part);
            isRomanNumber = true;
        } else {
            left = Integer.parseInt(left_part);
            right = Integer.parseInt(right_part);
        }
         if (left < 1 || left > 10 || right < 1 || right > 10) {
            throw new IllegalArgumentException("Недопустимое значение");
        }
        int result = 0;
        switch (operation) {
            case 1:
                result += left + right;
                break;
            case 2:
                result += left - right;
                break;
            case 3:
                result += left * right;
                break;
            case 4:
                try {
                    result += left / right;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    break;
                }
                break;
        }
        if (isRomanNumber) {
            String res = (convertToRoman(result));
            System.out.println(res);
        } else {
            System.out.println(result);
        }
        return character.toString();

    }

    static String convertToRoman(int num) {
        if (num < 1 || num > 100) {
            throw new IllegalArgumentException("Недопустимое значение для римских чисел");
        }

        String[] romanSymbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder roman = new StringBuilder();
        int index = 0;

        while (num > 0) {
            while (num >= arabicValues[index]) {
                roman.append(romanSymbols[index]);
                num -= arabicValues[index];
            }
            index++;
        }

        return roman.toString();
    }

    static int convertToArabic(String roman) {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Недопустимое значение для римского числа");
        }

        String[] romanSymbols = {"C", "L", "X", "V", "I"};
        int[] arabicValues = {100, 50, 10, 5, 1};

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char symbol = roman.charAt(i);
            int index = indexOf(romanSymbols, symbol);

            if (index == -1) {
                throw new IllegalArgumentException("Недопустимый символ в римском числе: " + symbol);
            }

            int value = arabicValues[index];

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }

            prevValue = value;
        }

        return result;
    }

    static int indexOf(String[] array, char target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].charAt(0) == target) {
                return i;
            }
        }
        return -1;
    }

    static boolean isRoman(String s) {
        char[] sub = s.toCharArray();
        if (sub[0] == 'I' || sub[0] == 'V' || sub[0] == 'X') {
            return true;
        }
        return false;
    }
    static boolean isOneOperator(String s){
        int counter = 0;
        char[] sub = s.toCharArray();
        if (sub[0] == '+' || sub[0] == '-' || sub[0] == '*' || sub[0] == '/') {
            counter += 1;
        }
        if (counter == 1){
            return true;
        }
        else {
            throw new IllegalArgumentException("Недопустимое количество операторов");
        }
    }
}
