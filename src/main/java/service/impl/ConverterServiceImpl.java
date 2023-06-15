package service.impl;

import enums.ValuteValueEnum;
import service.*;

import java.text.DecimalFormat;

public class ConverterServiceImpl implements ConverterService {

    ValidationService validationService = new ValidationServiceImpl();
    RewriteRequestService rewriteRequestService = new RewriteRequestServiceImpl();
    CalculatingService calculateBrackest = new CalculatingServiceImpl();
    ReadeFromFileService readeFromFileService = new ReadeFromFileServiceImpl();
    private final String PROPERTIES_FILE = "prop.properties";

    @Override
    public String convert(String request) {
        System.out.println(validationService.checkBrackestValid(request));
        String requestFirstStep = rewriteRequestService.rewriteRequestForOnlyArithmetic(request);
        String requestWithCalculatedBrackest = calculateBrackest.calculateBrackestOnly(requestFirstStep);
        String result = convertToValute(requestWithCalculatedBrackest);


        return result;
    }

    public String convertToValute(String requestWithCalculatedBrackest) {
        String[] calculateString = requestWithCalculatedBrackest.split(" ");

        double exchangeRate = 1;
        String valute = "?";
        boolean commandActive = false;


        for (int i = 0; i < calculateString.length; i++) {
            if (calculateString[i].isEmpty()) continue;

            if (calculateString[i].equals("toRubles") || calculateString[i].equals("toDollars")) {
                exchangeRate = readeFromFileService.getExchangeRate(calculateString[i]);
                valute = ValuteValueEnum.valueOf(calculateString[i]).getValute();
                calculateString[i] = "";
                commandActive = true;
                continue;
            } else if ((calculateString[i].equals("$") || calculateString[i].equals("р")) && commandActive == false) {
                valute = calculateString[i];
                calculateString[i] = "";

            } else if((calculateString[i].equals("$") || calculateString[i].equals("р")) && commandActive == true){
                calculateString[i] = "";
            }else {
                double result = Double.parseDouble(calculateString[i]) * exchangeRate;
                // calculateString[i] = Double.toString(Double.parseDouble(calculateString[i]) * exchangeRate);
                calculateString[i] = new DecimalFormat("0.00").format(result);
            }


        }
        return rewriteRequestService.backToReadForm(String.join("", calculateString), valute);
    }
}
