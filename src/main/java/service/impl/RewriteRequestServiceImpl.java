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
}
