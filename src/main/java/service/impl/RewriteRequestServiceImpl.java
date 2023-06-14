package service.impl;

import service.RewriteRequestService;

public class RewriteRequestServiceImpl implements RewriteRequestService {
    @Override
    public String rewriteRequestForOnlyArithmetic(String request) {
        String newRequest = request.replaceAll("\\s", "")
                .replaceAll("\\(", " (")
                .replaceAll("\\)", ") ");
        System.out.println(newRequest);
        return newRequest;
    }

    @Override
    public String backToReadForm(String request, String valute) {
        request = request.replaceAll("\\.", ",");
        if (valute.equals("$")) {
            request = "$" + request;
        } else if (valute.equals("p")) {
            request = request + "p";
        }
        return request;
    }
}
