package com.modsen.service.impl;

import com.modsen.enums.ValuteValueEnum;
import com.modsen.service.CalculatingService;
import com.modsen.service.ValidationService;

import java.util.Stack;

public class CalculatingServiceImpl implements CalculatingService {
    private final String ALL_EXCEPT_PLUS_AND_MINUS = "[^+\\\\-]";
    ValidationService validationService = new ValidationServiceImpl();

    @Override
    public String calculateBrackestOnly(String request) {
        String[] calculateString = request.split(" ");
        String value = validationService.getDefoltValidValute(request);
        boolean valid = false;
        boolean sameValuteValid = false;
        for (int i = 0; i < calculateString.length; i++) {

            if (calculateString[i].equals(ValuteValueEnum.toRubles.getCommand())) {
                value = "$";
                continue;
            } else if (calculateString[i].equals(ValuteValueEnum.toDollars.getCommand())) {
                value = "р";
                continue;
            } else if (calculateString[i].isEmpty()) continue;
            valid = validationService.checkValueValid(value, calculateString[i]);
            try {
                sameValuteValid = validationService.checkIsValuteSameInAllOperation(calculateString[i], value);
            } catch (ServiceException e) {
                System.err.println(e.getMessage());
                // e.printStackTrace();
            }

            if (valid && sameValuteValid) {
                String arifmeticResult = calculateArifmetic(calculateString[i]);
                if (arifmeticResult.contains("-")) {
                    calculateString[i] = value + " 0 - " + value + " " + arifmeticResult.replace("-","");
                } else
                    calculateString[i] = value + " " + calculateArifmetic(calculateString[i]);
            }


        }
        return String.join(" ", calculateString);
    }

    public String calculateArifmetic(String arifmetic) {
        if (arifmetic.startsWith("(") && arifmetic.endsWith(")")) {
            arifmetic = arifmetic.substring(1, arifmetic.length() - 1);
        }
        String[] stringOfNumbers = arifmetic.replaceAll("[$р]", "")
                .replaceAll(",", ".")
                .split("[+-]");

        double[] arrayOfNumbers = new double[stringOfNumbers.length];
        for (int i = 0; i < stringOfNumbers.length; i++) {
            arrayOfNumbers[i] = Double.parseDouble(stringOfNumbers[i]);
        }
        String[] arithmeticSymbols = arifmetic.replaceAll(ALL_EXCEPT_PLUS_AND_MINUS, "").split("");
        int arifmaticSymbolPositionCounter = 0;
        double result = arrayOfNumbers[0];
        for (int i = 1; i < arrayOfNumbers.length; i++, arifmaticSymbolPositionCounter++) {
            if (arithmeticSymbols[arifmaticSymbolPositionCounter].equals("+")) {
                result += arrayOfNumbers[i];
            } else if (arithmeticSymbols[arifmaticSymbolPositionCounter].equals("-")) {
                result -= arrayOfNumbers[i];
            }
        }
      /*  if (result < 0) {
            return "0 - " + Double.toString(result * (-1));
        }*/

        return Double.toString(result);

    }

    @Override
    public int findIndexOfCloseBracket(String onlyOperation) {
        Stack<String> stack = new Stack<>();
        String[] splitedRequest = onlyOperation.split("");
        for (int i = onlyOperation.indexOf("("); i < splitedRequest.length; i++) {
            if (splitedRequest[i].equals("(")) {
                stack.push("(");
            } else if (splitedRequest[i].equals(")")) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                return i;
            }
        }
        return 0;
    }
}
