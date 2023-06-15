package service.impl;

import service.ValidationService;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {
    private final Pattern dollarPattern = Pattern.compile("\\$-?\\d+(,\\d+)?");
    private final Pattern rubPattern = Pattern.compile("-?\\d+(,\\d+)?р");


    @Override
    public boolean checkBrackestValid(String request) {
        String[] onlyBrackestString = request.replaceAll("[^()]", "").split("");

        Stack<String> brackestStack = new Stack<>();

        for (int i = 0; i < onlyBrackestString.length; i++) {
            if (onlyBrackestString[i].equals("(")) {
                brackestStack.push(onlyBrackestString[i]);
            } else if (onlyBrackestString[i].equals(")")) {
                brackestStack.pop();
            }
        }
        if (brackestStack.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean checkValueValid(String value, String s) {
        String[] specialString = s.replaceAll("[-+()]", " ")
                .split(" ");
        boolean result = false;
        for (int i = 0; i < specialString.length; i++) {
            if (specialString[i].isEmpty())
                continue;
            if (value.equals("$")) {
                result = checkIsItDollarValue(specialString[i]);
            } else if (value.equals("р")) {
                result = checkIsItRubValue(specialString[i]);
            }
            if (!result) {
                return result;
            }

        }


        return true;
    }

    @Override
    public boolean checkIsItDollarValue(String value) {
        Matcher matcher = dollarPattern.matcher(value);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    @Override
    public boolean checkIsItRubValue(String value) {
        Matcher matcher = rubPattern.matcher(value);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    @Override
    public boolean checkIsValuteSameInAllOperation(String operation) {
        String onlyValuteString = operation.replaceAll("[^р$]", "");
        if (onlyValuteString.contains("р") && onlyValuteString.contains("$")) {
            //НАПИСАТЬ ИСКЛЮЧЕНИЕ
            System.err.println("Нельзя работать с разнаыми валютами: " + operation);
            return false;
        } else {
            return true;
        }

    }

    @Override
    public boolean checkIsValuteSameInAllOperation(String operation, String valute) {
        String onlyValuteString = operation.replaceAll("[^р$]", "");
        onlyValuteString = onlyValuteString.replace(valute, "");
        if (!onlyValuteString.isEmpty()) {
            //НАПИСАТЬ ИСКЛЮЧЕНИЕ
            System.err.println("Нельзя работать с разнаыми валютами: " + operation);
            return false;
        } else {
            return true;
        }

    }

    @Override
    public String getDefoltValidValute(String request) {
        return request.replaceAll("[^р$]", "").split("")[0];


    }


}
