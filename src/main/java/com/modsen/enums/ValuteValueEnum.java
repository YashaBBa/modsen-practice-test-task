package com.modsen.enums;

public enum ValuteValueEnum {
    toDollars("$", "toDollars"),
    toRubles("р", "toRubles");

    private String valute;
    private String command;


    ValuteValueEnum(String valute, String command) {
        this.valute = valute;
        this.command = command;
    }

    public String getValute() {
        return valute;
    }

    public String getCommand() {
        return command;
    }
}
