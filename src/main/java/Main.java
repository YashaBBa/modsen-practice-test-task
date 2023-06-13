import service.ConverterService;
import service.impl.ConverterServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConverterService converterService = new ConverterServiceImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input: ");
        //String request = scanner.nextLine();
        String request = "toRubles($5,2 + $2,1)+toRubles($5,2 + $2,1)";
        // String request = "(($5,2 + 2,1)";
        System.out.println(converterService.convert(request));


    }
}
