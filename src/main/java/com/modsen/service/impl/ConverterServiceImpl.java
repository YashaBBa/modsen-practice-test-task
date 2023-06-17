package com.modsen.service.impl;

import com.modsen.enums.ValuteValueEnum;
import com.modsen.service.*;

import java.text.DecimalFormat;

public class ConverterServiceImpl implements ConverterService {

    private final String ROUND = "0.00";

    ValidationService validationService = new ValidationServiceImpl();
    RewriteRequestService rewriteRequestService = new RewriteRequestServiceImpl();
    CalculatingService calculateService = new CalculatingServiceImpl();
    ReadeFromFileService readeFromFileService = new ReadeFromFileServiceImpl();


    @Override
    public String convert(String request) {

        try {
            validationService.checkBrackestValid(request);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
            return "";
        }
        request = startRecursionIfNeededAndGetNewRequest(request);
        String requestFirstStep = rewriteRequestService.rewriteRequestForOnlyArithmetic(request);
        String requestWithCalculatedBrackest = calculateService.calculateBrackestOnly(requestFirstStep);
        String result = convertToValute(requestWithCalculatedBrackest);


        return result;
    }


    public String convertToValute(String requestWithCalculatedBrackest) {
        String[] calculateString = requestWithCalculatedBrackest.split(" ");

        double exchangeRate = 1;
        String valute = "?";
        boolean commandActive = false;


        for (int i = 0; i < calculateString.length; i++) {
            if (calculateString[i].isEmpty() || calculateString[i].equals("-")) continue;

            if (calculateString[i].equals(ValuteValueEnum.toDollars.getCommand()) || calculateString[i].equals(ValuteValueEnum.toRubles.getCommand())) {
                exchangeRate = readeFromFileService.getExchangeRate(calculateString[i]);
                valute = ValuteValueEnum.valueOf(calculateString[i]).getValute();
                calculateString[i] = "";
                commandActive = true;
                continue;
            } else if ((calculateString[i].equals(ValuteValueEnum.toRubles.getValute()) || calculateString[i].equals(ValuteValueEnum.toDollars.getValute())) && commandActive == false) {
                valute = calculateString[i];
                calculateString[i] = "";

            } else if ((calculateString[i].equals(ValuteValueEnum.toRubles.getValute()) || calculateString[i].equals(ValuteValueEnum.toDollars.getValute())) && commandActive == true) {
                calculateString[i] = "";
            } else {
                double result = Double.parseDouble(calculateString[i]) * exchangeRate;

                calculateString[i] = new DecimalFormat(ROUND).format(result);
            }


        }
        return rewriteRequestService.backToReadForm(String.join("", calculateString), valute);
    }

    private String startRecursionIfNeededAndGetNewRequest(String request) {
        /* String[] splitedRequest = request.split(" ");
        String lastChar = "";
        for (int i = 0; i < splitedRequest.length; i++) {
            if (splitedRequest[i].startsWith("t") && !lastChar.isEmpty()) {
                if (splitedRequest[i].endsWith("))")) {
                    splitedRequest[i] = convert(splitedRequest[i].replace("))", ")")) + ")";
                    continue;
                }
                splitedRequest[i] = convert(splitedRequest[i]);

            }
            lastChar = splitedRequest[i];
        }*/
        while (true) {
            String[] splitedRequest = request.split("");
            String onlyOperation = "";
            String result = "";

            for (int i = 0; i < splitedRequest.length; i++) {
                if (splitedRequest[i].equals("t") && i != 0) {
                    onlyOperation = request.substring(i);
                    //определить индекс закрывающей скобки
                    int closeBlacksetIndex = calculateService.findIndexOfCloseBracket(onlyOperation);

                    onlyOperation = onlyOperation.substring(0, closeBlacksetIndex + 1);

                    result = convert(onlyOperation);
                    break;

                }


            }
            if (!onlyOperation.isEmpty()) {
                request = request.replace(onlyOperation, result);
            } else {
                break;
            }
        }


        return request;
    }

}
