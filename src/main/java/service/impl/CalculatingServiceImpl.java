package service.impl;

import service.CalculatingService;
import service.ValidationService;

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

            if (calculateString[i].equals("toRubles")) {
                value = "$";
                continue;
            } else if (calculateString[i].equals("toDollars")) {
                value = "р";
                continue;
            } else if (calculateString[i].isEmpty()) continue;
            valid = validationService.checkValueValid(value, calculateString[i]);
            sameValuteValid = validationService.checkIsValuteSameInAllOperation(calculateString[i], value);
            System.out.println(valid);
            if (valid && sameValuteValid) {
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
        for (int i = 1; i < arrayOfNumbers.length; i++) {
            if (arithmeticSymbols[arifmaticSymbolPositionCounter].equals("+")) {
                result += arrayOfNumbers[i];
            } else if (arithmeticSymbols[arifmaticSymbolPositionCounter].equals("-")) {
                result -= arrayOfNumbers[i];
            }
        }

        return Double.toString(result);

    }
}
