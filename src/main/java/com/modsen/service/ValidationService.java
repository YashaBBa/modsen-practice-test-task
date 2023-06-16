package com.modsen.service;

import com.modsen.service.impl.ServiceException;

public interface ValidationService {
    boolean checkBrackestValid(String request) throws ServiceException;

    boolean checkValueValid(String value, String s);

    boolean checkIsItDollarValue(String value) throws ServiceException;

    boolean checkIsItRubValue(String value) throws ServiceException;

    boolean checkIsValuteSameInAllOperation(String operation) throws ServiceException;
    boolean checkIsValuteSameInAllOperation(String operation, String valute) throws ServiceException;

    String getDefoltValidValute(String request);
}
