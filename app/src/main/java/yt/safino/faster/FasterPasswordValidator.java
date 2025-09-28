package yt.safino.faster;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class FasterPasswordValidator {

    private static final String DIGITS = "0123456789";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String SYMBOLS = "@#$%^&*()-_=+[]{}|;:,.<>?/";
    private static final String ALL_CHARS = DIGITS + UPPERCASE + LOWERCASE + SYMBOLS;

    private static final SecureRandom secureRandom = new SecureRandom();

    public static boolean validate(String password) {
        return minLength(password) &&
               digit(password) &&
               lowerCase(password) &&
               upperCase(password) &&
               symbol(password) &&
               uniqueChars(password) >= 6;
    }

    public static boolean symbol(String password) {
        for (char c : SYMBOLS.toCharArray()) {
            if (password.indexOf(c) != -1) return true;
        }
        return false;
    }

    public static boolean upperCase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) return true;
        }
        return false;
    }

    public static boolean lowerCase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) return true;
        }
        return false;
    }

    public static boolean digit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }
        return false;
    }

    public static boolean minLength(String password) {
        return password.length() >= 12;
    }

    public static int uniqueChars(String password) {
        Set<Character> unique = new HashSet<>();
        for (char c : password.toCharArray()) {
            unique.add(c);
        }
        return unique.size();
    }

    public static String generateStrongPassword(int length) {
        if (length < 12) {
            length = 12;
        }

        StringBuilder password = new StringBuilder();

        password.append(randomChar(DIGITS));
        password.append(randomChar(UPPERCASE));
        password.append(randomChar(LOWERCASE));
        password.append(randomChar(SYMBOLS));

        for (int i = 4; i < length; i++) {
            password.append(randomChar(ALL_CHARS));
        }

        return shuffleString(password.toString());
    }

    private static char randomChar(String chars) {
        return chars.charAt(secureRandom.nextInt(chars.length()));
    }

    private static String shuffleString(String input) {
        char[] array = input.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int j = secureRandom.nextInt(array.length);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return new String(array);
    }
}