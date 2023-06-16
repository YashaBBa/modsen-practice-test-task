package com.modsen.service.impl;

import com.modsen.enums.ValuteValueEnum;
import com.modsen.service.RewriteRequestService;

public class RewriteRequestServiceImpl implements RewriteRequestService {
    @Override
    public String rewriteRequestForOnlyArithmetic(String request) {
        String newRequest = request.replaceAll("\\s", "")
                .replaceAll("\\(", " (")
                .replaceAll("\\)", ") ")
                .replaceAll("\\+t", "+ t")
                .replaceAll("\\-t", "- t");


        return newRequest;
    }

    @Override
    public String backToReadForm(String request, String valute) {
        request = request.replaceAll("\\.", ",");
        if (valute.equals(ValuteValueEnum.toDollars.getValute())) {
            request = ValuteValueEnum.toDollars.getValute() + request;
        } else if (valute.equals(ValuteValueEnum.toRubles.getValute())) {
            request = request + ValuteValueEnum.toRubles.getValute();
        }
        if (request.contains("-")) {
            if (valute.equals(ValuteValueEnum.toDollars.getValute()))
                request = request.replace("-", " - " + ValuteValueEnum.toDollars.getValute());
            else if (valute.equals(ValuteValueEnum.toRubles.getValute())) {
                request = request.replace("-", ValuteValueEnum.toRubles.getValute() + " - ");

            }

        }
        return request;
    }
}
