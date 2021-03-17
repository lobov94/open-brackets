package com.company;

import java.io.*;
import java.util.regex.*;

public class Solution {

    static String string;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        string = reader.readLine();
        boolean stringValid = string.matches("^[a-zA-Z0-9\\[\\]]+$") && bracketBalance(); // проверяем
        // введенную строку на валидность с помощью regex и правильности введенных квадратных скобок

        if (stringValid) {
            while (string.contains("[")) {
                string = string.replaceFirst("\\d\\[\\w+]", increaseCharsInBrackets(string));
                // заменяем содержимое квадратных скобок и число на продублированное содержимое
                // квадратных скобок
            }
            System.out.println(string);
        } else {
            System.out.println("Invalid string. Please, try again.");
        }
    }

    public static boolean bracketBalance() {
        int leftBr = 0;
        int rightBr = 0;

        for (int i : string.getBytes()) {
            if (i == 91) leftBr++;
            if (i == 93) rightBr++;
        }
        if (string.indexOf('[') > string.indexOf(']')) return false;
        if (string.contains("[]")  || string.startsWith("[")) return false;
        if (string.matches(".*[a-zA-Z]\\[.*")) return false;
        return rightBr == leftBr;
    }

    public static String increaseCharsInBrackets(String string) {
        Matcher matcher = Pattern.compile("(\\d)\\[(\\w+)]").matcher(string);
        if (matcher.find()) {
            string = matcher.group(2).repeat(Integer.parseInt(matcher.group(1)));
            // открываем скобки начиная либо с первой по порядку, либо с вложенной
            // при этом дублируем содержимое
        }
        return string;
    }
}
