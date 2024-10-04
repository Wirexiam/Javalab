import java.util.Scanner;

public class LR1 {

    // Метод для проверки, является ли строка палиндромом
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Метод для проверки, является ли число палиндромом
    public static boolean isNumberPalindrome(int number) {
        String numStr = Integer.toString(number);
        return isPalindrome(numStr);
    }

    // Метод для проверки, является ли квадрат числа палиндромом
    public static boolean isSquarePalindrome(int number) {
        int square = number * number;
        return isNumberPalindrome(square);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строку или целое положительное число:");
        String input = scanner.nextLine();

        // Проверяем, является ли введенная строка числом
        try {
            int number = Integer.parseInt(input);
            if (isNumberPalindrome(number)) {
                System.out.println("Введенное число является палиндромом.");
                if (isSquarePalindrome(number)) {
                    System.out.println("Квадрат числа также является палиндромом.");
                } else {
                    System.out.println("Квадрат числа не является палиндромом.");
                }
            } else {
                System.out.println("Введенное число не является палиндромом.");
            }
        } catch (NumberFormatException e) {
            // Если это не число, проверяем строку
            if (isPalindrome(input)) {
                System.out.println("Введенная строка является палиндромом.");
            } else {
                System.out.println("Введенная строка не является палиндромом.");
            }
        }
    }
}
