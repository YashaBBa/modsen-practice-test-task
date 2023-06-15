package service;

public interface ValidationService {
    boolean checkBrackestValid(String request);

    boolean checkValueValid(String value, String s);

    boolean checkIsItDollarValue(String value);

    boolean checkIsItRubValue(String value);

    boolean checkIsValuteSameInAllOperation(String operation);
    boolean checkIsValuteSameInAllOperation(String operation, String valute);

    String getDefoltValidValute(String request);
}
