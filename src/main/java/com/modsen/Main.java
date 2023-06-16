package com.modsen;

import com.modsen.service.ConverterService;
import com.modsen.service.impl.ConverterServiceImpl;

import java.io.StringReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConverterService converterService = new ConverterServiceImpl();
        Scanner scanner = new Scanner(System.in);
        //   while (true) {
            System.out.println("Input: ");
            //String request = scanner.nextLine();
           String request = "toRubles(toDollars(100р + 5р - toRubles($13,5 - $10)) + $5,2 + $2,1 + toDollars(100р + 5р))";
            // String request = "(($5,2 + 2,1)";
            //String request = "toRubles($5,2 + $2,1 + $5)";
            //String request = "($5,2 + $2,1 + $5)";
            // String request = "(5,2р + 2,1р + 5р)";
            // String request = "toRubles($2,34)";
            //String request = "5р+4р";
        //String request="$0 - $5 - $6";
          //  String request = scanner.nextLine();
            System.out.println("Result : " + converterService.convert(request));
        //}

    }
}
