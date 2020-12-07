package tariff.XMLparsers.sax;

public enum TariffEnum {
    TARIFFS("tariffs"),
    NAME("name"),
    OPERATOR_NAME("operatorName"),
    PAYROLL("payroll"),
    CALLPRICE("callPrice"),
    SMSPRICE("smsPrice"),
    FAVNUMBER("favNumber"),
    TARIFFICATION("tariffication"),
    JOINPRICE("joinPrice"),
    TARIFFPARAMETERS("tariffParameters");

    private final String value;

    TariffEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
