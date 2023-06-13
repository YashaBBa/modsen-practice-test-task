package service;

public interface ValidationService {
    boolean checkBrackestValid(String request);

    boolean checkValueValid(String value, String s);

    boolean checkIsItDollarValue(String value);

    boolean checkIsItRubValue(String value);
}
