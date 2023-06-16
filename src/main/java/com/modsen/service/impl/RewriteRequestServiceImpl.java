package com.modsen.service.impl;

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
        if (valute.equals("$")) {
            request = "$" + request;
        } else if (valute.equals("р")) {
            request = request + "р";
        }
        if (request.contains("-")) {
            if(valute.equals("$"))
            request = request.replace("-"," - $" );
            else if(valute.equals("р")){
                request=request.replace("-","р - ");

            }

        }
        return request;
    }
}
