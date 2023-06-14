package enums;

public enum ValuteValueEnum {
    toDollar("$"),
    toRubles("p");

    private String valute;


    ValuteValueEnum(String valute) {
        this.valute = valute;
    }

    public String getValute() {
        return valute;
    }
}
