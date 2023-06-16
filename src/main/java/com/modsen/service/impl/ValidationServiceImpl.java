package com.modsen.service.impl;

import com.modsen.service.ValidationService;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {

    private final String INCORRECT_CURRENCY_ENTRY = "Incorrect currency entry: ";
    private final String CURRENCY_TYPE_SHOULD_BE_SAME = "Currency type should be same: ";
    private final String INCORRECT_USING_OF_BRACKETS = "Incorrect using of brackets";

    private final Pattern dollarPattern = Pattern.compile("\\$-?\\d+(,\\d+)?");
    private final Pattern rubPattern = Pattern.compile("-?\\d+(,\\d+)?р");


    @Override
    public boolean checkBrackestValid(String request) throws ServiceException {
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
            throw new ServiceException(INCORRECT_USING_OF_BRACKETS);
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
                try {
                    result = checkIsItDollarValue(specialString[i]);
                } catch (ServiceException e) {
                    System.out.println(e.getMessage());
                    //e.printStackTrace();
                }
            } else if (value.equals("р")) {
                try {
                    result = checkIsItRubValue(specialString[i]);
                } catch (ServiceException e) {
                    System.out.println(e.getMessage());
                  //  e.printStackTrace();
                }
            }
            if (!result) {
                return result;
            }

        }


        return true;
    }

    @Override
    public boolean checkIsItDollarValue(String value) throws ServiceException {
        Matcher matcher = dollarPattern.matcher(value);
        if (matcher.matches())
            return true;
        else
            throw new ServiceException(INCORRECT_CURRENCY_ENTRY + value);
    }

    @Override
    public boolean checkIsItRubValue(String value) throws ServiceException {
        Matcher matcher = rubPattern.matcher(value);
        if (matcher.matches())
            return true;
        else
            throw new ServiceException(INCORRECT_CURRENCY_ENTRY + value);

    }

    @Override
    public boolean checkIsValuteSameInAllOperation(String operation) throws ServiceException {
        String onlyValuteString = operation.replaceAll("[^р$]", "");
        if (onlyValuteString.contains("р") && onlyValuteString.contains("$")) {
            throw new ServiceException(CURRENCY_TYPE_SHOULD_BE_SAME + operation);
        } else {
            return true;
        }

    }

    @Override
    public boolean checkIsValuteSameInAllOperation(String operation, String valute) throws ServiceException {
        String onlyValuteString = operation.replaceAll("[^р$]", "");
        onlyValuteString = onlyValuteString.replace(valute, "");
        if (!onlyValuteString.isEmpty()) {
            throw new ServiceException(CURRENCY_TYPE_SHOULD_BE_SAME + operation);
        } else {
            return true;
        }


    }

    @Override
    public String getDefoltValidValute(String request) {
        return request.replaceAll("[^р$]", "").split("")[0];


    }


}
