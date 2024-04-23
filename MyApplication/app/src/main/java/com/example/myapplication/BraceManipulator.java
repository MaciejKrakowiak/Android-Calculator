package com.example.myapplication;
public class BraceManipulator {

    public static String toggleBraces(String statement) {
        int openBracesCount = countOccurrences(statement, '(');
        int closeBracesCount = countOccurrences(statement, ')');

        // Check if there are equal numbers of opening and closing braces
        if (openBracesCount == closeBracesCount) {
            // Add an opening brace without immediately adding a closing brace
            statement += "(";
        } else {
            // Check if there are more closing braces than opening braces
            if (closeBracesCount > openBracesCount) {
                // Close the previous unmatched opening brace
                statement = closeUnmatchedOpeningBrace(statement);
            } else {
                // Check if the last character is a digit or a closing brace
                char lastChar = statement.isEmpty() ? ' ' : statement.charAt(statement.length() - 1);
                if (Character.isDigit(lastChar) || lastChar == ')') {
                    // If the last character is a digit or a closing brace, add a closing brace
                    statement += ")";
                } else {
                    // If the last character is not a digit or a closing brace, add an opening brace
                    statement += "(";
                }
            }
        }
        return statement;
    }

    private static String closeUnmatchedOpeningBrace(String statement) {
        int openBraceIndex = statement.lastIndexOf('(');
        if (openBraceIndex >= 0) {
            return statement.substring(0, openBraceIndex) + ")";
        }
        return statement;
    }

    private static int countOccurrences(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

}
