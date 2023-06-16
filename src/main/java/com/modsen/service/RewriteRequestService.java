package com.modsen.service;

public interface RewriteRequestService {
    String rewriteRequestForOnlyArithmetic(String request);
    String backToReadForm(String request,String valute);
}
