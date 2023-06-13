package service.impl;

import service.CalculatingService;
import service.ValidationService;

public class CalculatingServiceImpl implements CalculatingService {
    ValidationService validationService = new ValidationServiceImpl();

    @Override
    public String calculateBrackestOnly(String request) {
        String[] calculateString = request.split(" ");
        String value = "$";
        boolean valid = false;
        for (int i = 0; i < calculateString.length; i++) {

            if (calculateString[i].equals("toRubles")) {
                value = "$";
                continue;
            } else if (calculateString[i].equals("toDollars")) {
                value = "p";
                continue;
            }
            valid = validationService.checkValueValid(value, calculateString[i]);
            System.out.println(valid);


        }
        return null;
    }
}
