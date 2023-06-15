import service.ConverterService;
import service.impl.ConverterServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConverterService converterService = new ConverterServiceImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input: ");
        //String request = scanner.nextLine();
      //  String request = "toRubles($5,2 + $2,1)+toRubles($5,2 + $2,1)";
        // String request = "(($5,2 + 2,1)";
        //String request = "toRubles($5,2 + $2,1 + $5)";
        //String request = "($5,2 + $2,1 + $5)";
        // String request = "(5,2р + 2,1р + 5р)";
        // String request = "toRubles($2,34)";
        String request = "5р+4р";
        System.out.println(converterService.convert(request));


    }
}
