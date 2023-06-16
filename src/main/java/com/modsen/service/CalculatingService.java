package com.modsen.service;

public interface CalculatingService {
    String calculateBrackestOnly(String request);

    String calculateArifmetic(String request);

    int findIndexOfCloseBracket(String onlyOperation);
}
