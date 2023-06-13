package service.impl;

import service.CalculatingService;
import service.ConverterService;
import service.RewriteRequestService;
import service.ValidationService;

public class ConverterServiceImpl implements ConverterService {

    ValidationService validationService = new ValidationServiceImpl();
    RewriteRequestService rewriteRequestService = new RewriteRequestServiceImpl();
    CalculatingService calculateBrackest = new CalculatingServiceImpl();

    @Override
    public String convert(String request) {
        System.out.println(validationService.checkBrackestValid(request));
        String requestFirstStep = rewriteRequestService.rewriteRequestForOnlyArithmetic(request);
        String requestWithCalculatedBrackest = calculateBrackest.calculateBrackestOnly(requestFirstStep);

        return "";
    }
}
