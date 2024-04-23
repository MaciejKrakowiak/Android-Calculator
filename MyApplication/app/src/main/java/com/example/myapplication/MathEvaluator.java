package com.example.myapplication;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

public class MathEvaluator {

    public static double evaluate(String mathExpression) {
        try {
            Expression expression = new ExpressionBuilder(mathExpression).build();
            ValidationResult result = expression.validate(false);
            if (!result.isValid()) {
                throw new IllegalArgumentException("Invalid expression: " + result.getErrors().get(0));
            }
            return expression.evaluate();
        } catch (UnknownFunctionOrVariableException e) {
            throw new IllegalArgumentException("Unknown function or variable in expression: " + e.getToken());
        }
    }

    public static String formatNumber(double number) {
        if (number % 1 == 0) {
            return String.valueOf((int) number);
        } else {
            return String.valueOf(number).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }

    public static String changeSign(String expression) {
        int endIndex = expression.length() - 1;
        while (endIndex >= 0 && !Character.isDigit(expression.charAt(endIndex))) {
            endIndex--;
        }
        int startIndex = endIndex;
        while (startIndex >= 0 && (Character.isDigit(expression.charAt(startIndex)) || expression.charAt(startIndex) == '.')) {
            startIndex--;
        }
        startIndex++;

        String numberStr = expression.substring(startIndex, endIndex + 1);

        boolean isNegative = startIndex > 0 && expression.charAt(startIndex - 1) == '-';

        if (isNegative) {
            expression = expression.substring(0, startIndex - 2) + expression.substring(startIndex);
        } else {
            expression = expression.substring(0, startIndex) + "(-" + expression.substring(startIndex);
        }

        return expression;
    }

    public static String clearStringIfContainsEqual(String input) {
        if (input.contains("=")) {
            return "";
        } else {
            return input;
        }
    }

//    public static void main(String[] args) {
//        String mathExpression = "2 * (3 + 5)";
//        double result = evaluate(mathExpression);
//        System.out.println("Result: " + result);
//    }
}