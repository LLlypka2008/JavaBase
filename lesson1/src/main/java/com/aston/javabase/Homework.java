package com.aston.javabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homework {

    // Перевернуть строку и вывести на консоль
    //  String string = "I love Java";
    public static void turnString(String string) {
        System.out.println(reverseString(string));
    }

    // int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
    // Удалить дубликаты из массива и вывести в консоль

    public static void getDistinctNumbers(int[] ints) {
        int arrLength = ints.length;
        List<Integer> counter = new ArrayList<>();
        for (int i = 0; i < arrLength; i++) {
            if (counter.contains(ints[i])) {
                System.out.println(ints[i]);
            } else counter.add(ints[i]);
        }
        Integer[] copyInts = counter.toArray(new Integer[0]);
    }

    // Дан массив, заполненный уникальными значениями типа int.
    // int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
    // Необходимо найти элемент, который меньше максимума, но больше всех остальных.
    public static Integer findSecondMaxElement(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 2];
    }

    // Найти длину последнего слова в строке. В строке только буквы и пробелы.
    // "Hello world" - 5
    // "    fly me    to the moon    " - 4
    public static Integer lengthOfLastWord(String string) {
        String stripString = string.strip();
        String[] arr = stripString.split("\\s");
        return arr[arr.length - 1].length();
    }

    // Определить, что строка является палиндромом
    // Сложность по памяти O(1), не создавать новые String, StringBuilder
    // Примеры:
    // abc - false
    // 112233 - false
    // aba - true
    // 112211 - true

    public static boolean isPalindrome(String string) {
        boolean answer = true;
        char[] charArray = string.toCharArray();
        int[] compare = new int[charArray.length / 2];
        for (int i = 0; i < charArray.length / 2; i++) {
            compare[i] = String
                    .valueOf(charArray[i])
                    .compareTo(String.valueOf(charArray[charArray.length - i - 1]));
        }
        for (int i : compare) {
            if (i != 0) return false;
        }
        return true;
    }

    public static boolean isPalindrome2(String string) {
        return reverseString(string).equals(string);
    }

    private static String reverseString(String str) {
        char[] chars = str.toCharArray();
        int charsLength = chars.length;
        for (int i = 0; i <= charsLength / 2; i++) {
            char c = chars[i];
            chars[i] = chars[charsLength - i - 1];
            chars[charsLength - i - 1] = c;
        }
        return String.valueOf(chars);
    }
}
