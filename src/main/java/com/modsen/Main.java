package com.modsen;

import com.modsen.service.ConverterService;
import com.modsen.service.impl.ConverterServiceImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConverterService converterService = new ConverterServiceImpl();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input: ");
            //String request = scanner.nextLine();
            String request = scanner.nextLine();
            System.out.println("Result : " + converterService.convert(request));
        }

    }
}
